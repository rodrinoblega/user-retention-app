package com.embrace.challenge.domain.entities;

import java.util.List;
import java.util.Objects;

public class UserRetention {
    private final String id;
    private int consecutiveDaysConnected;
    private ConnectionDate initialStreakDate;
    private ConnectionDate lastConnectionDate;



    public UserRetention(String id,
                         int consecutiveDaysConnected,
                         ConnectionDate initialStreakDate,
                         ConnectionDate lastConnectionDate
                         ) {
        this.id = id;
        this.consecutiveDaysConnected = consecutiveDaysConnected;
        this.initialStreakDate = initialStreakDate;
        this.lastConnectionDate = lastConnectionDate;
    }

    public UserRetention(Record record) {
        this.id = record.getUser();
        this.consecutiveDaysConnected = 1;
        this.initialStreakDate = record.getDate();
        this.lastConnectionDate = record.getDate();
    }

    public void update(List<UserRetention> userRetentions, Record record) {
        if(record.getDate().equals(this.lastConnectionDate)) {
            return;
        }

        if (isAConsecutiveDayConnected(record)) {
            this.consecutiveDaysConnected = consecutiveDaysConnected + 1;
            this.lastConnectionDate = record.getDate();
        } else {
            UserRetention userRetention = new UserRetention(
                    record.getUser(),
                    1,
                    record.getDate(),
                    record.getDate()
            );
            userRetentions.add(userRetention);
        }
    }

    public String getId() {
        return id;
    }

    public ConnectionDate getInitialStreakDate() {
        return initialStreakDate;
    }

    public int getConsecutiveDaysConnected() {
        return consecutiveDaysConnected;
    }

    private boolean isAConsecutiveDayConnected(Record record) {
        return record.getDate().isOneDayAfterLastConnection(this.lastConnectionDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRetention that = (UserRetention) o;
        return consecutiveDaysConnected == that.consecutiveDaysConnected && Objects.equals(id, that.id) && Objects.equals(initialStreakDate, that.initialStreakDate) && Objects.equals(lastConnectionDate, that.lastConnectionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, consecutiveDaysConnected, initialStreakDate, lastConnectionDate);
    }
}
