package com.embrace.challenge.adapters.controllers;

import com.embrace.challenge.adapters.interpreters.InputInterpreter;
import com.embrace.challenge.domain.entities.DateRange;
import com.embrace.challenge.domain.entities.Record;
import com.embrace.challenge.usecases.UserRetentionUseCase;
import com.embrace.challenge.usecases.output.UserRetentionUseCaseResponse;
import com.embrace.challenge.usecases.presenters.UserRetentionPresenter;

import java.util.List;

public class UserRetentionController {

    private final InputInterpreter inputInterpreter;
    private final UserRetentionUseCase userRetentionUseCase;
    private final UserRetentionPresenter userRetentionPresenter;

    public UserRetentionController(UserRetentionUseCase userRetentionUseCase, InputInterpreter inputInterpreter, UserRetentionPresenter userRetentionPresenter) {
        this.userRetentionUseCase = userRetentionUseCase;
        this.inputInterpreter = inputInterpreter;
        this.userRetentionPresenter = userRetentionPresenter;
    }

    public void process(String input, DateRange dateRange) {
        List<Record> records = inputInterpreter.run(input);

        UserRetentionUseCaseResponse userRetentionUseCaseResponse = userRetentionUseCase.process(records);

        userRetentionPresenter.present(userRetentionUseCaseResponse, dateRange);
    }
}
