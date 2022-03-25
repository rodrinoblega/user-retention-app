package com.embrace.challenge.domain.entities;

import com.embrace.challenge.frameworks.interpreters.DateHelper;

import java.util.Objects;

public class ConnectionDate {
    private int day;
    private int month;
    private int year;

    public ConnectionDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public boolean isOneDayAfterLastConnection(ConnectionDate lastConnectionDate) {
        return this.year == lastConnectionDate.year &&
                this.month == lastConnectionDate.month &&
                this.day - 1 == lastConnectionDate.day;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
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

    public boolean isLessThan(ConnectionDate endRangeDate) {
        return DateHelper.isLessDateThan(this, endRangeDate);
    }

    public ConnectionDate addOneDay() {
         return DateHelper.addOneDay(this);
    }
}
