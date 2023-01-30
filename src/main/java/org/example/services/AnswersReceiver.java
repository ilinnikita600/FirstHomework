package org.example.services;

import org.example.models.Result;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

public interface AnswersReceiver {
    boolean askQuestion() throws IOException;
    default int getRemainingQuestions() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
    Result getResult(ResultBuilder resultBuilder) throws InvalidPropertiesFormatException;
}
