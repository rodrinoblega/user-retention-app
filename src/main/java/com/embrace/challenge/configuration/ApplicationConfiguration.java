package com.embrace.challenge.configuration;

import com.embrace.challenge.adapters.interpreters.DateRangeInterpreter;
import com.embrace.challenge.adapters.presenters.UserRetentionPresenterImpl;
import com.embrace.challenge.adapters.controllers.UserRetentionController;
import com.embrace.challenge.adapters.interpreters.InputInterpreter;
import com.embrace.challenge.frameworks.UserRetentionGateway;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.instrumentation.Log4jImpl;
import com.embrace.challenge.frameworks.interpreters.CSVInputInterpreter;
import com.embrace.challenge.frameworks.interpreters.DateRangeInterpreterImpl;
import com.embrace.challenge.frameworks.validations.InputValidation;
import com.embrace.challenge.usecases.UserRetentionUseCase;
import com.embrace.challenge.usecases.presenters.UserRetentionPresenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public UserRetentionGateway userRetention() {
        return new UserRetentionGateway(instrumentation(), userRetentionController(), inputValidation());
    }

    @Bean
    public Instrumentation instrumentation() {
        return new Instrumentation(new Log4jImpl());
    }

    @Bean
    public UserRetentionController userRetentionController() {
        return new UserRetentionController(userRetentionUseCase(), interpreter(), userRetentionPresenter());
    }

    @Bean
    public UserRetentionUseCase userRetentionUseCase() {
        return new UserRetentionUseCase();
    }

    @Bean
    public UserRetentionPresenter userRetentionPresenter() { return new UserRetentionPresenterImpl(); }

    @Bean
    public InputInterpreter interpreter() {
        return new CSVInputInterpreter(instrumentation());
    }

    @Bean
    public InputValidation inputValidation() { return new InputValidation(dateRangeInterpreter()); }

    @Bean
    public DateRangeInterpreter dateRangeInterpreter() { return new DateRangeInterpreterImpl(instrumentation()); }
}
