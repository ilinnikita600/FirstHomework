package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class QuestionReceiverImpl implements QuestionsReceiver {
    private final String answersPath;
    private final String questionsPath;
    private ArrayList<Question> questions;

    public QuestionReceiverImpl(String answersPath, String questionsPath) {
        this.answersPath = answersPath;
        this.questionsPath = questionsPath;
        this.questions = new ArrayList<>();
    }
    private void readQuestionsFromFiles() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        try(InputStream inputStreamAnswers = classLoader.getResourceAsStream(answersPath);
            InputStream inputStreamQuestions = classLoader.getResourceAsStream(questionsPath)) {
            if (inputStreamAnswers == null) {
                throw new FileNotFoundException("File " + answersPath + " not found.");
            }
            if (inputStreamQuestions == null) {
                throw new FileNotFoundException("File " + questionsPath + " not found.");
            }
            BufferedReader answersReader = new BufferedReader(new InputStreamReader(inputStreamAnswers));
            BufferedReader questionsReader = new BufferedReader(new InputStreamReader(inputStreamQuestions));

            while (answersReader.ready()) {
                Question question = new Question();
                String[] idAndAnswer = answersReader.readLine().split(" ", 2);

                question.setQuestionId(Integer.parseInt(idAndAnswer[0]));
                question.setAnswer(idAndAnswer[1]);

                String[] idAndAnswersAndQuestion = questionsReader.readLine().split(" ",3);

                question.setQuestion(idAndAnswersAndQuestion[2]);

                ArrayList<String> variationsOfAnswers = new ArrayList<>();
                for(int i = 0; i < Integer.parseInt(idAndAnswersAndQuestion[1]); i++) {
                    variationsOfAnswers.add(questionsReader.readLine());
                }
                question.setVariationsOfAnswers(variationsOfAnswers);
                questions.add(question);
            }
        }
    }
    public Iterator<Question> getQuestions() throws IOException {
        readQuestionsFromFiles();
        return questions.iterator();
    }
}
