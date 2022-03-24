package com.embrace.challenge.adapters.controllers;

import com.embrace.challenge.adapters.interpreters.InputInterpreter;
import com.embrace.challenge.domain.entities.Record;

import java.util.List;

public class UserRetentionController {

    private final InputInterpreter inputInterpreter;

    public UserRetentionController(InputInterpreter inputInterpreter) {
        this.inputInterpreter = inputInterpreter;
    }

    public Record process(String input) {
        List<Record> record = inputInterpreter.run(input);

        return new Record("123", "20211010");
    }
}
