package com.embrace.challenge.frameworks.instrumentation;

import com.embrace.challenge.domain.Logging;

public class Instrumentation {
    private final Logging logging;

    public Instrumentation(Logging logging) {
        this.logging = logging;
    }

    public void logMessage(String error) {
        logging.logMsg(error);
    }
}
