package org.example.services;

import org.example.models.Question;

import java.io.IOException;
import java.util.Iterator;

public interface QuestionsReceiver {
    public Iterator<Question> getQuestions() throws IOException;
}
