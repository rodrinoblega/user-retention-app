package com.embrace.challenge.adapters.interpreters;

import com.embrace.challenge.domain.entities.Record;

import java.util.List;

public interface Interpreter {
    List<Record> interpret(String input);
}

