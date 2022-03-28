package com.embrace.challenge.configuration;

import com.embrace.challenge.adapters.controllers.UserRetentionController;
import com.embrace.challenge.adapters.presenters.UserRetentionPresenterImpl;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.instrumentation.Log4jImpl;
import com.embrace.challenge.frameworks.interpreters.DateRangeInterpreterImpl;
import com.embrace.challenge.frameworks.validations.InputValidation;
import com.embrace.challenge.usecases.UserRetentionUseCase;

public class ApplicationConfiguration {

    public UserRetentionController obtainUserRetentionController() {
        Instrumentation instrumentation = new Instrumentation(new Log4jImpl());
        return new UserRetentionController(
                new UserRetentionUseCase(instrumentation),
                new UserRetentionPresenterImpl(),
                new InputValidation(
                        new DateRangeInterpreterImpl(instrumentation)
                )
        );
    }
}
