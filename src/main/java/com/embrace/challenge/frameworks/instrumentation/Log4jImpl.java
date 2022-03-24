package com.embrace.challenge.frameworks.instrumentation;

import com.embrace.challenge.domain.Logging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jImpl implements Logging {

    private final Logger log;

    public Log4jImpl() {
        this.log = LogManager.getLogger(Log4jImpl.class);
    }

    public void logMsg(String msg) {
        log.warn(msg);
    }
}
