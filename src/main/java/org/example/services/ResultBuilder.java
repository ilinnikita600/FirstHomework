package org.example.services;

import org.example.models.Question;
import org.example.models.Result;

import java.util.InvalidPropertiesFormatException;
import java.util.List;

public interface ResultBuilder {
    Result getResult(List<Question> questions, List<String> answers) throws InvalidPropertiesFormatException;
}
