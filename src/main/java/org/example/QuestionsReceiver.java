package org.example;

import java.io.IOException;
import java.util.Iterator;

public interface QuestionsReceiver {
    public Iterator<Question> getQuestions() throws IOException;
}
