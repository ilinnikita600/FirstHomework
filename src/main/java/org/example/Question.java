package org.example;

import lombok.Data;
import java.util.ArrayList;

@Data
public class Question {
    private String question;
    private ArrayList<String> variationsOfAnswers;
    private String answer;
    private int questionId;

    @Override
    public String toString() {
        return question + " " + answer + " " + variationsOfAnswers;
    }
}
