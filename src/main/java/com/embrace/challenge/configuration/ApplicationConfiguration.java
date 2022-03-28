package com.embrace.challenge.configuration;

import com.embrace.challenge.adapters.interpreters.DateRangeInterpreter;
import com.embrace.challenge.adapters.controllers.UserRetentionController;
import com.embrace.challenge.adapters.presenters.UserRetentionPresenterImpl;
import com.embrace.challenge.frameworks.UserRetentionGateway;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.instrumentation.Log4jImpl;
import com.embrace.challenge.frameworks.interpreters.DateRangeInterpreterImpl;
import com.embrace.challenge.frameworks.validations.InputValidation;
import com.embrace.challenge.usecases.UserRetentionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public UserRetentionGateway userRetention() {
        return new UserRetentionGateway(userRetentionController(), inputValidation());
    }

    @Bean
    public Instrumentation instrumentation() {
        return new Instrumentation(new Log4jImpl());
    }

    @Bean
    public UserRetentionController userRetentionController() {
        return new UserRetentionController(userRetentionUseCaseV2(), userRetentionPresenterV2());
    }

    @Bean
    public UserRetentionUseCase userRetentionUseCaseV2() {
        return new UserRetentionUseCase(instrumentation());
    }

    @Bean
    public UserRetentionPresenterImpl userRetentionPresenterV2() { return new UserRetentionPresenterImpl(); }

    @Bean
    public InputValidation inputValidation() { return new InputValidation(dateRangeInterpreter()); }

    @Bean
    public DateRangeInterpreter dateRangeInterpreter() { return new DateRangeInterpreterImpl(instrumentation()); }
}
