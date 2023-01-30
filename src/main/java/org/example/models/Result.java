package org.example.models;

import java.util.*;
import java.util.function.Consumer;

public class Result {
    private final Map<Question, String> questionsResult;
    private final int correctAnswers;

    public Result(Map<Question, String> questionsResult) {
        this.questionsResult = questionsResult;
        correctAnswers = getCorrectAnswers1();
    }

    private int getCorrectAnswers1() {
        int correctAnswers = 0;
        Iterator<Map.Entry<Question, String>> questionsResultIterator = getQuestionResult();
        for (Map.Entry<Question, String> questionRes; questionsResultIterator.hasNext(); ) {
            questionRes = questionsResultIterator.next();
            Question question = questionRes.getKey();
            String answer = questionRes.getValue();
            if (question.getCorrectAnswer() == null || question.getCorrectAnswer().equals(answer)) {
                correctAnswers += 1;
            }
        }
        return correctAnswers;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public Iterator<Map.Entry<Question,String>> getQuestionResult() {
        return questionsResult.entrySet().iterator();
    }
}
