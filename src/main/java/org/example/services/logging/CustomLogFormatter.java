package org.example.services.logging;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

@Component
public class CustomLogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date recordDate = new Date(record.getMillis());

        sb.append(sdf.format(recordDate) + " | ");
        sb.append(record.getLevel() + " | ");
        sb.append("Initiator: " + record.getLoggerName() +  " | ");
        sb.append(record.getMessage());
        sb.append('\n');
        return sb.toString();
    }
}
