package com.embrace.challenge.frameworks.instrumentation;

import com.embrace.challenge.domain.Logging;

public class Log4jImpl implements Logging {

    public void logMsg(String msg) {
        System.out.println(msg);
    }
}