package org.example;

import org.example.models.Question;
import org.example.models.Result;
import org.example.services.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        QuestionsReceiver questionsReceiver = context.getBean(QuestionsReceiver.class);
        try {
            AnswersReceiver answersReceiver = new AnswersReceiverImpl(questionsReceiver.getQuestions(), System.in, System.out);
            while(answersReceiver.askQuestion());
            ResultBuilder resultBuilder = context.getBean(ResultBuilder.class);
            Result result = answersReceiver.getResult(resultBuilder);
            System.out.println(result.getCorrectAnswers());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}