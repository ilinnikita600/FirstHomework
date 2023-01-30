package org.example.models;

import lombok.Data;
import org.example.services.QuestionsReceiver;

import java.util.ArrayList;

@Data
public class Question {
    private String question;
    private ArrayList<String> variationsOfAnswers;
    private String correctAnswer;
    private int questionId;

    public Question() {
        variationsOfAnswers = new ArrayList<>();
    }

    public void addVariationOfAnswers(String variation) {
        variationsOfAnswers.add(variation);
    }
    @Override
    public String toString() {
        return question;
    }
}
