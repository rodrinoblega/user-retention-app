package com.embrace.challenge.domain.entities;

import java.util.Objects;

public class DateRange {
    private final int initialDay;
    private final int finalDay;

    public DateRange(int initialDay, int finalDay) {
        this.initialDay = initialDay;
        this.finalDay = finalDay;
    }

    public int getInitialDay() {
        return initialDay;
    }

    public int getFinalDay() {
        return finalDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateRange dateRange = (DateRange) o;
        return initialDay == dateRange.initialDay && finalDay == dateRange.finalDay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(initialDay, finalDay);
    }
}
