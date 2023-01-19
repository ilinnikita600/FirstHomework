package org.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class QuestionsReceiverTest {

    @org.junit.jupiter.api.Test
    void testGetQuestionsMethod() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionsReceiver questionsReceiver = context.getBean(QuestionsReceiver.class);
        try {
            Assertions.assertEquals(1,questionsReceiver.getQuestions().next().getQuestionId());
        } catch (IOException e) {
            System.out.println("Test failed");
            e.printStackTrace();
        }
    }
}
