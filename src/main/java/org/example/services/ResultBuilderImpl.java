package org.example.services;

import org.example.models.Question;
import org.example.models.Result;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@PropertySource("/props/generalConfig.properties")
public class ResultBuilderImpl implements ResultBuilder {
    @Override
    public Result getResult(List<Question> questions, List<String> answers) throws InvalidPropertiesFormatException {
        if (questions.size() != answers.size()) {
            throw new InvalidPropertiesFormatException("Size of questions and answers list is not equal");
        }

        HashMap<Question, String> questionsResult = new HashMap<>();
        Iterator<Question> questionsIterator = questions.iterator();
        Iterator<String> answersIterator = answers.iterator();
        while(questionsIterator.hasNext()) {
            questionsResult.put(questionsIterator.next(), answersIterator.next().trim());
        }
        return new Result(questionsResult);
    }
}
