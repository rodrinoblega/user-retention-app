package com.embrace.challenge.domain.entities;

import java.util.Objects;

public class DateRange {
    private final ConnectionDate initialDate;
    private final ConnectionDate finalDate;

    public DateRange(ConnectionDate initialDate, ConnectionDate finalDate) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public ConnectionDate getInitialDate() {
        return initialDate;
    }

    public ConnectionDate getFinalDate() {
        return finalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateRange dateRange = (DateRange) o;
        return Objects.equals(initialDate, dateRange.initialDate) && Objects.equals(finalDate, dateRange.finalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(initialDate, finalDate);
    }
}
