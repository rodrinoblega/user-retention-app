package com.embrace.challenge.domain.entities;

import java.util.Objects;

public class ConnectionDate {
    private final int day;
    private final int month;
    private final int year;

    public ConnectionDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionDate that = (ConnectionDate) o;
        return day == that.day && month == that.month && year == that.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    public boolean isOneDayAfterLastConnection(ConnectionDate lastConnectionDate) {
        return lastConnectionDate.year == this.year &&
                lastConnectionDate.month == this.month &&
                lastConnectionDate.day == day + 1;
    }
}
