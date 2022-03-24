package com.embrace.challenge.frameworks.interpreters;

import com.embrace.challenge.adapters.interpreters.InputInterpreter;
import com.embrace.challenge.domain.entities.Record;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVInputInterpreter implements InputInterpreter {

    private static final String ERRROR_MESSAGE = "There was a problem reading CSV";
    private final Instrumentation instrumentation;

    public CSVInputInterpreter(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    @Override
    public List<Record> run(String input) {
        List<Record> records = new ArrayList<>();

        iterateCSVAndAddRecordToList(input, records);

        return records;
    }

    private void iterateCSVAndAddRecordToList(String input, List<Record> records) {
        try (CSVReader csv = new CSVReader(new FileReader(input))) {
            String[] lineInArray;
            while ((lineInArray = csv.readNext()) != null) {
                records.add(new Record(lineInArray[0], lineInArray[1]));
            }
        } catch (Exception e) {
            instrumentation.logMessage(ERRROR_MESSAGE);
            throw new RuntimeException(ERRROR_MESSAGE);
        }
    }
}
