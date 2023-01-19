package org.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionsReceiver questionsReceiver = context.getBean(QuestionsReceiver.class);
        Iterator<Question> questions = questionsReceiver.getQuestions();
        while (questions.hasNext()) {
            Question question = questions.next();

            System.out.printf("%d. %s Student answer: %s%n", question.getQuestionId(), question.getQuestion(), question.getAnswer());

            ArrayList<String> variationsOfAnswers = question.getVariationsOfAnswers();
            if (variationsOfAnswers.size() > 0) {
                System.out.println("Suggested answers:");
                for (String variationsOfAnswer : variationsOfAnswers) {
                    System.out.println(variationsOfAnswer);
                }
            }
        }
    }
}