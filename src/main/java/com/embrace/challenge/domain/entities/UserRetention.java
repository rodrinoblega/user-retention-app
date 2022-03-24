package com.embrace.challenge.domain.entities;

import java.util.Objects;

public class UserRetention {
    private final String id;
    private ConnectionDate lastConnectionDate;
    private int consecutiveDaysConnected;

    public UserRetention(String id, ConnectionDate lastConnectionDate, int consecutiveDaysConnected) {
        this.id = id;
        this.lastConnectionDate = lastConnectionDate;
        this.consecutiveDaysConnected = consecutiveDaysConnected;
    }

    public UserRetention(Record record) {
        this.id = record.getUser();
        this.lastConnectionDate = record.getDate();
        this.consecutiveDaysConnected = 1;
    }

    public String getId() {
        return id;
    }

    public void update(Record record) {
        if (isAConsecutiveDayConnected(record)) {
            this.consecutiveDaysConnected = consecutiveDaysConnected + 1;
        } else {
            this.consecutiveDaysConnected = 1;
        }
        this.lastConnectionDate = record.getDate();
    }

    private boolean isAConsecutiveDayConnected(Record record) {
        return record.getDate().isOneDayAfterLastConnection(this.lastConnectionDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRetention that = (UserRetention) o;
        return Objects.equals(id, that.id) && Objects.equals(lastConnectionDate, that.lastConnectionDate) && Objects.equals(consecutiveDaysConnected, that.consecutiveDaysConnected);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastConnectionDate, consecutiveDaysConnected);
    }
}
