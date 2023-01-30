package org.example;

import org.example.models.Result;
import org.example.services.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static int MIN_CORRECT_ANSWERS;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        QuestionsReceiver questionsReceiver = context.getBean(QuestionsReceiver.class);
        try {
            AnswersReceiver answersReceiver = new AnswersReceiverImpl(questionsReceiver.getQuestions(), System.in, System.out);
            while(answersReceiver.askQuestion());
            ResultBuilder resultBuilder = context.getBean(ResultBuilder.class);
            Result result = answersReceiver.getResult(resultBuilder);
            if (result.getCorrectAnswers() > MIN_CORRECT_ANSWERS) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}