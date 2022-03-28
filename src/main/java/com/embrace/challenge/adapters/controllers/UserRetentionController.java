package com.embrace.challenge.adapters.controllers;

import com.embrace.challenge.domain.entities.*;
import com.embrace.challenge.usecases.UserRetentionUseCase;
import com.embrace.challenge.usecases.presenters.UserRetentionPresenter;

import java.util.List;

public class UserRetentionController {

    private final UserRetentionUseCase userRetentionUseCase;
    private final UserRetentionPresenter userRetentionPresenter;

    public UserRetentionController(UserRetentionUseCase userRetentionUseCase, UserRetentionPresenter userRetentionPresenter) {
        this.userRetentionUseCase = userRetentionUseCase;
        this.userRetentionPresenter = userRetentionPresenter;
    }

    public void process(String input, DateRange dateRange) {
        List<Day> day = userRetentionUseCase.process(input, dateRange);
        userRetentionPresenter.present(day, dateRange);
    }
}
