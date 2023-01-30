package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@Configuration
@PropertySource("/props/generalConfig.properties")
public class MainConfig {
    @Value("${minCorrectAnswers}")
    public void setMinCorrectAnswers(int minCorrectAnswers) {
        Main.MIN_CORRECT_ANSWERS = minCorrectAnswers;
    }
}
