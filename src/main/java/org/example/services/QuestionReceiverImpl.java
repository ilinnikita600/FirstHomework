package org.example.services;

import org.example.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

@Service
@PropertySource("/props/generalConfig.properties")
public class QuestionReceiverImpl implements QuestionsReceiver {
    private final String questionsPath;
    private final String correctAnswersPath;

    public QuestionReceiverImpl(@Value("${questionsPath}") String questionsPath, @Value("${correctAnswersPath}") String correctAnswersPath) {
        this.questionsPath = questionsPath;
        this.correctAnswersPath = correctAnswersPath;
    }
    private ArrayList<Question> readQuestionsFromFile() throws IOException {
        ArrayList<Question> questions = new ArrayList<>();

        try(InputStream inputStreamQuestions = getClass().getClassLoader().getResourceAsStream(questionsPath);
        InputStream inputStreamCorrectAnswers = getClass().getClassLoader().getResourceAsStream(correctAnswersPath)) {
            if (inputStreamQuestions == null) {
                throw new FileNotFoundException("File " + questionsPath + " not found.");
            } else if (inputStreamCorrectAnswers == null) {
                throw new FileNotFoundException("File " + correctAnswersPath + " not found.");
            }

            BufferedReader questionsReader = new BufferedReader(new InputStreamReader(inputStreamQuestions));
            BufferedReader correctAnswersReader = new BufferedReader(new InputStreamReader(inputStreamCorrectAnswers));

            while(questionsReader.ready()) {
                questions.add(readQuestionFromReader(questionsReader, correctAnswersReader));
            }
        }

        return questions;
    }
    private Question readQuestionFromReader(BufferedReader questionsReader, BufferedReader correctAnswersReader) throws IOException {
        Question question = new Question();

        String[] questionInfo = questionsReader.readLine().split(" ",3);
        question.setQuestion(questionInfo[0]);
        question.setQuestion(questionInfo[2]);

        int countOfAnswersVariation = Integer.parseInt(questionInfo[1]);
        for(int i = 0; i < countOfAnswersVariation; i++) {
            question.addVariationOfAnswers(questionsReader.readLine());
        }

        if (countOfAnswersVariation > 0) {
            question.setCorrectAnswer(correctAnswersReader.readLine().split(" ")[1]);
        }

        return question;
    }
    public Iterator<Question> getQuestions() throws IOException {
        return readQuestionsFromFile().iterator();
    }
}
