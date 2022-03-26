package com.embrace.challenge.frameworks;

import com.embrace.challenge.adapters.controllers.UserRetentionController;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.validations.InputValidation;
import com.embrace.challenge.frameworks.validations.ValidatedInput;
import org.springframework.boot.CommandLineRunner;

public class UserRetentionGateway implements CommandLineRunner {

    private final UserRetentionController userRetentionController;
    private final InputValidation inputValidation;

    public UserRetentionGateway(UserRetentionController userRetentionController, InputValidation inputValidation) {
        this.userRetentionController = userRetentionController;
        this.inputValidation = inputValidation;
    }

    public void run(String... ars) {
        ValidatedInput validatedInput = inputValidation.validateAndReturnInput(ars);

        userRetentionController.process(validatedInput.getPath(), validatedInput.getDateRange());
    }
}
