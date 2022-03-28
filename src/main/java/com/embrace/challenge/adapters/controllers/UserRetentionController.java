package com.embrace.challenge.adapters.controllers;

import com.embrace.challenge.domain.entities.*;
import com.embrace.challenge.usecases.UserRetentionUseCase;
import com.embrace.challenge.usecases.presenters.UserRetentionPresenterV2;

import java.util.List;

public class UserRetentionController {

    private final UserRetentionUseCase userRetentionUseCase;
    private final UserRetentionPresenterV2 userRetentionPresenterV2;

    public UserRetentionController(UserRetentionUseCase userRetentionUseCase, UserRetentionPresenterV2 userRetentionPresenterV2) {
        this.userRetentionUseCase = userRetentionUseCase;
        this.userRetentionPresenterV2 = userRetentionPresenterV2;
    }

    public void process(String input, DateRange dateRange) {
        List<Day> day = userRetentionUseCase.process(input, dateRange);
        userRetentionPresenterV2.present(day, dateRange);
    }
}
