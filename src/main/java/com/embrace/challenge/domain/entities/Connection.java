package com.embrace.challenge.domain.entities;

public class Connection {
    private final int initialStreakDay;
    private final int daysConnected;

    public Connection(int initialStreakDay, int daysConnected) {
        this.initialStreakDay = initialStreakDay;
        this.daysConnected = daysConnected;
    }

    public int getInitialStreakDay() {
        return initialStreakDay;
    }

    public int getDaysConnected() {
        return daysConnected;
    }
}
