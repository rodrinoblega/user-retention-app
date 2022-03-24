package com.embrace.challenge.adapters.controllers;

import com.embrace.challenge.adapters.interpreters.InputInterpreter;
import com.embrace.challenge.domain.entities.ConnectionDate;
import com.embrace.challenge.domain.entities.Record;
import com.embrace.challenge.usecases.UserRetentionUseCase;
import com.embrace.challenge.usecases.output.UserRetentionUseCaseResponse;

import java.util.List;

public class UserRetentionController {

    private final InputInterpreter inputInterpreter;
    private final UserRetentionUseCase userRetentionUseCase;

    public UserRetentionController(UserRetentionUseCase userRetentionUseCase, InputInterpreter inputInterpreter) {
        this.userRetentionUseCase = userRetentionUseCase;
        this.inputInterpreter = inputInterpreter;
    }

    public Record process(String input) {
        List<Record> records = inputInterpreter.run(input);

        UserRetentionUseCaseResponse userRetentionUseCaseResponse = userRetentionUseCase.process(records);

        return new Record("123", new ConnectionDate(10, 10, 2021));
    }
}
