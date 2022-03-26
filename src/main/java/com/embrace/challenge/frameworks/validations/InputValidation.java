package com.embrace.challenge.frameworks.validations;

import com.embrace.challenge.adapters.interpreters.DateRangeInterpreter;
import com.embrace.challenge.domain.entities.DateRange;
import com.embrace.challenge.frameworks.exceptions.CSVException;

import java.util.Arrays;
import java.util.Optional;

public class InputValidation {
    private final DateRangeInterpreter dateRangeInterpreter;

    public InputValidation(DateRangeInterpreter dateRangeInterpreter) {
        this.dateRangeInterpreter = dateRangeInterpreter;
    }

    public ValidatedInput validateAndReturnInput(String... ars) {
        Optional<String> csvPath = Arrays.stream(ars).findFirst();
        String path = csvPath.orElseThrow(
                () -> new CSVException("Please provide a csv path")
        );

        DateRange dateRange = dateRangeInterpreter.obtainsDateRangeIfApply(ars);

        return new ValidatedInput(path,dateRange);
    }
}
