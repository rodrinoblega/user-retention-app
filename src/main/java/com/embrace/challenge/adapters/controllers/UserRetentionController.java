package com.embrace.challenge.adapters.controllers;

import com.embrace.challenge.frameworks.validations.InputValidation;
import com.embrace.challenge.frameworks.validations.ValidatedInput;
import com.embrace.challenge.usecases.UserRetentionUseCase;
import com.embrace.challenge.usecases.output.UserRetentionUseCaseResponse;
import com.embrace.challenge.usecases.presenters.UserRetentionPresenter;

public class UserRetentionController {

    private final InputValidation inputValidation;
    private final UserRetentionUseCase userRetentionUseCase;
    private final UserRetentionPresenter userRetentionPresenter;

    public UserRetentionController(UserRetentionUseCase userRetentionUseCase, UserRetentionPresenter userRetentionPresenter, InputValidation inputValidation) {
        this.inputValidation = inputValidation;
        this.userRetentionUseCase = userRetentionUseCase;
        this.userRetentionPresenter = userRetentionPresenter;
    }

    public void process(String[] args) {
        ValidatedInput validatedInput = inputValidation.validateAndReturnInput(args);
        UserRetentionUseCaseResponse userRetentionUseCaseResponse = userRetentionUseCase.process(validatedInput.getPath(), validatedInput.getDateRange());
        userRetentionPresenter.present(userRetentionUseCaseResponse, validatedInput.getDateRange());
    }
}
