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

    public QuestionReceiverImpl(@Value("${questionsPath}") String questionsPath) {
        this.questionsPath = questionsPath;
    }
    private ArrayList<Question> readQuestionsFromFile() throws IOException {
        ArrayList<Question> questions = new ArrayList<>();

        try(InputStream inputStreamQuestions = getClass().getClassLoader().getResourceAsStream(questionsPath)) {
            if (inputStreamQuestions == null) {
                throw new FileNotFoundException("File " + questionsPath + " not found.");
            }

            BufferedReader questionsReader = new BufferedReader(new InputStreamReader(inputStreamQuestions));

            while(questionsReader.ready()) {
                questions.add(readQuestionFromReader(questionsReader));
            }
        }

        return questions;
    }
    private Question readQuestionFromReader(BufferedReader bufferedReader) throws IOException {
        Question question = new Question();

        String[] questionInfo = bufferedReader.readLine().split(" ",3);
        question.setQuestion(questionInfo[0]);
        question.setQuestion(questionInfo[2]);
        for(int i = 0; i < Integer.parseInt(questionInfo[1]); i++) {
            question.addVariationOfAnswers(bufferedReader.readLine());
        }

        return question;
    }
    public Iterator<Question> getQuestions() throws IOException {
        return readQuestionsFromFile().iterator();
    }
}
