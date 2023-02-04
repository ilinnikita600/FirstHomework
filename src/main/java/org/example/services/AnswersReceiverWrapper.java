package org.example.services;

import org.example.annotations.Loggable;
import org.example.annotations.Wrapable;
import org.example.models.Result;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
@Component
@Loggable
public class AnswersReceiverWrapper implements AnswersReceiver, Wrapable {
    private AnswersReceiver answersReceiver;

    @Override
    public void setWrapObject(Object object) {
        answersReceiver = (AnswersReceiver) object;
    }

    @Override
    public boolean askQuestion() throws IOException {
        return answersReceiver.askQuestion();
    }
    @Override
    public Result getResult(ResultBuilder resultBuilder) throws InvalidPropertiesFormatException {
        return answersReceiver.getResult(resultBuilder);
    }
}
