package org.example.services.logging;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;

@PropertySource("/props/loggingConfig.properties")
@Component
public class SessionFileLogger extends SessionLogger {

    SessionFileLogger(@Value("${loggerName}") String name, @Value("${logFilePath}") String logFilePath, @Qualifier("customLogFormatter") Formatter formatter) throws IOException, SecurityException {
        super(name, null);
        Handler fileHandler = new FileHandler(logFilePath,true);
        fileHandler.setFormatter(formatter);
        addHandler(fileHandler);
    }
}
