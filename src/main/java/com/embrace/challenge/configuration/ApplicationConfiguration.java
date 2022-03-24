package com.embrace.challenge.configuration;

import com.embrace.challenge.adapters.controllers.UserRetentionController;
import com.embrace.challenge.adapters.interpreters.InputInterpreter;
import com.embrace.challenge.frameworks.UserRetentionGateway;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.instrumentation.Log4jImpl;
import com.embrace.challenge.frameworks.interpreters.CSVInputInterpreter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public UserRetentionGateway userRetention() {
        return new UserRetentionGateway(instrumentation(), userRetentionController());
    }

    @Bean
    public Instrumentation instrumentation() {
        return new Instrumentation(new Log4jImpl());
    }

    @Bean
    public UserRetentionController userRetentionController() {
        return new UserRetentionController(interpreter());
    }

    @Bean
    public InputInterpreter interpreter() {
        return new CSVInputInterpreter(instrumentation());
    }
}
