package com.embrace.challenge.frameworks.validations;

import com.embrace.challenge.domain.entities.DateRange;

public class ValidatedInput {
    private final String path;
    private final DateRange dateRange;

    public ValidatedInput(String path, DateRange dateRange) {
        this.path = path;
        this.dateRange = dateRange;
    }

    public String getPath() {
        return path;
    }

    public DateRange getDateRange() {
        return dateRange;
    }
}
