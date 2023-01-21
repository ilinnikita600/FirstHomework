package org.example;

import org.example.models.Question;
import org.example.services.QuestionsReceiver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        QuestionsReceiver questionsReceiver = context.getBean(QuestionsReceiver.class);
        try {
            Iterator<Question> questions = questionsReceiver.getQuestions();
            while(questions.hasNext()){
                System.out.println(questions.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}