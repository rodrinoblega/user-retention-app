package com.embrace.challenge.adapters.controllers;

import com.embrace.challenge.domain.entities.*;
import com.embrace.challenge.frameworks.validations.InputValidation;
import com.embrace.challenge.frameworks.validations.ValidatedInput;
import com.embrace.challenge.usecases.UserRetentionUseCase;
import com.embrace.challenge.usecases.presenters.UserRetentionPresenter;

import java.util.List;

public class UserRetentionController {

    private final UserRetentionUseCase userRetentionUseCase;
    private final UserRetentionPresenter userRetentionPresenter;
    private final InputValidation inputValidation;

    public UserRetentionController(UserRetentionUseCase userRetentionUseCase, UserRetentionPresenter userRetentionPresenter, InputValidation inputValidation) {
        this.userRetentionUseCase = userRetentionUseCase;
        this.userRetentionPresenter = userRetentionPresenter;
        this.inputValidation = inputValidation;
    }

    public void process(String[] args) {
        ValidatedInput validatedInput = inputValidation.validateAndReturnInput(args);
        List<Day> day = userRetentionUseCase.process(validatedInput.getPath(), validatedInput.getDateRange());
        userRetentionPresenter.present(day, validatedInput.getDateRange());
    }
}
