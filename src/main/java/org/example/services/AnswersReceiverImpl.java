package org.example.services;

import org.example.annotations.Loggable;
import org.example.models.Question;
import org.example.models.Result;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;

public class AnswersReceiverImpl implements AnswersReceiver {
    private final ArrayList<Question> questionsAsked;
    private final ArrayList<String> answers;
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;
    private final Iterator<Question> questionsIterator;

    public AnswersReceiverImpl(Iterator<Question> questionsIterator, InputStream is, OutputStream os) {
        bufferedReader = new BufferedReader(new InputStreamReader(is));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(os));
        this.questionsIterator = questionsIterator;
        this.questionsAsked = new ArrayList<>();
        this.answers = new ArrayList<>();
    }
    @Override
    public boolean askQuestion() throws IOException {
        if (!questionsIterator.hasNext()) return false;
        Question current = questionsIterator.next();
        bufferedWriter.write("Question:" + current.getQuestion() + "\n");

        ArrayList<String> variationsOfAnswers = current.getVariationsOfAnswers();
        if (variationsOfAnswers.size() > 0) {
            bufferedWriter.write("Variations of answer:\n");
            for(String variationOfAnswer: variationsOfAnswers) {
                bufferedWriter.write(variationOfAnswer + "\n");
            }
        }
        bufferedWriter.write("Enter answer:");
        bufferedWriter.flush();
        answers.add(bufferedReader.readLine());
        questionsAsked.add(current);
        return true;
    }
    @Override
    public Result getResult(ResultBuilder resultBuilder) throws InvalidPropertiesFormatException {
        return resultBuilder.getResult(questionsAsked, answers);
    }
}
