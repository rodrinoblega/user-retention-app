package com.embrace.challenge.frameworks.interpreters;

import com.embrace.challenge.adapters.interpreters.Interpreter;
import com.embrace.challenge.domain.entities.Record;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVInterpreter implements Interpreter {

    private static final String ERRROR_MESSAGE = "There was a problem reading CSV";
    private final Instrumentation instrumentation;
    public CSVInterpreter(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    @Override
    public List<Record> interpret(String input) {
        List<Record> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(input))) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                records.add(new Record(lineInArray[0], lineInArray[1]));
            }
        } catch (Exception e) {
            instrumentation.logMessage(ERRROR_MESSAGE);
            throw new RuntimeException(ERRROR_MESSAGE);
        }
        return records;
    }
}
