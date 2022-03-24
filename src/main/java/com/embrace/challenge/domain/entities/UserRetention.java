package com.embrace.challenge.domain.entities;

import java.util.Objects;

public class UserRetention {
    private final String id;
    private int actualConsecutiveDaysConnected;
    private ConnectionDate lastConnectionDate;

    private int mostDaysConnectedConsecutively;


    public UserRetention(String id,
                         ConnectionDate lastConnectionDate,
                         int mostDaysConnectedConsecutively,
                         int actualConsecutiveDaysConnected) {
        this.id = id;
        this.lastConnectionDate = lastConnectionDate;
        this.mostDaysConnectedConsecutively = mostDaysConnectedConsecutively;
        this.actualConsecutiveDaysConnected = actualConsecutiveDaysConnected;
    }

    public UserRetention(Record record) {
        this.id = record.getUser();
        this.lastConnectionDate = record.getDate();
        this.mostDaysConnectedConsecutively = 1;
        this.actualConsecutiveDaysConnected = 1;
    }

    public String getId() {
        return id;
    }

    public void update(Record record) {
        if (isAConsecutiveDayConnected(record)) {
            this.actualConsecutiveDaysConnected = actualConsecutiveDaysConnected + 1;
            if (this.mostDaysConnectedConsecutively < actualConsecutiveDaysConnected) {
                this.mostDaysConnectedConsecutively = actualConsecutiveDaysConnected;
            }
        } else {
            this.actualConsecutiveDaysConnected = 1;
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
        return actualConsecutiveDaysConnected == that.actualConsecutiveDaysConnected && mostDaysConnectedConsecutively == that.mostDaysConnectedConsecutively && Objects.equals(id, that.id) && Objects.equals(lastConnectionDate, that.lastConnectionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actualConsecutiveDaysConnected, lastConnectionDate, mostDaysConnectedConsecutively);
    }
}
