package org.example.services.logging;


import java.util.logging.Logger;

public abstract class SessionLogger extends Logger {
    protected SessionLogger(String name, String resourceBundleName) {
        super(name, resourceBundleName);
    }
}
