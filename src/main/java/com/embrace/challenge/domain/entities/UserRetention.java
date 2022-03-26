package com.embrace.challenge.domain.entities;

import java.util.List;
import java.util.Objects;

public class UserRetention {
    private final String userId;
    private int consecutiveDaysConnected;
    private ConnectionDate initialStreakDate;
    private ConnectionDate lastConnectionDate;

    public UserRetention(String userId,
                         int consecutiveDaysConnected,
                         ConnectionDate initialStreakDate,
                         ConnectionDate lastConnectionDate
                         ) {
        this.userId = userId;
        this.consecutiveDaysConnected = consecutiveDaysConnected;
        this.initialStreakDate = initialStreakDate;
        this.lastConnectionDate = lastConnectionDate;
    }

    public UserRetention(Record record) {
        this.userId = record.getUser();
        this.consecutiveDaysConnected = 1;
        this.initialStreakDate = record.getDate();
        this.lastConnectionDate = record.getDate();
    }

    public void addOrUpdateIfApplies(List<UserRetention> userRetentions, Record record) {
        if(isTheSameDayAsTheLasConnection(record)) {
            return;
        }

        if (isAConsecutiveDayConnected(record)) {
            updateConsecutiveDaysConnectedOfUser(record);
        } else {
            UserRetention userRetention = createNewUserRetention(record);
            userRetentions.add(userRetention);
        }
    }

    private boolean isTheSameDayAsTheLasConnection(Record record) {
        return record.getDate().equals(this.lastConnectionDate);
    }

    private void updateConsecutiveDaysConnectedOfUser(Record record) {
        this.consecutiveDaysConnected = consecutiveDaysConnected + 1;
        this.lastConnectionDate = record.getDate();
    }

    private UserRetention createNewUserRetention(Record record) {
        return new UserRetention(
                record.getUser(),
                1,
                record.getDate(),
                record.getDate()
        );
    }

    public String getUserId() {
        return userId;
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
        return consecutiveDaysConnected == that.consecutiveDaysConnected && Objects.equals(userId, that.userId) && Objects.equals(initialStreakDate, that.initialStreakDate) && Objects.equals(lastConnectionDate, that.lastConnectionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, consecutiveDaysConnected, initialStreakDate, lastConnectionDate);
    }
}
