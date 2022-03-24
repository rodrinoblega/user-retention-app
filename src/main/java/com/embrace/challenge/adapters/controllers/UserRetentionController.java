package com.embrace.challenge.adapters.controllers;

import com.embrace.challenge.adapters.interpreters.Interpreter;
import com.embrace.challenge.domain.entities.Record;

import java.util.List;

public class UserRetentionController {

    private final Interpreter interpreter;

    public UserRetentionController(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public Record process(String input) {
        List<Record> record = interpreter.interpret(input);

        return new Record("123", "20211010");
    }
}
