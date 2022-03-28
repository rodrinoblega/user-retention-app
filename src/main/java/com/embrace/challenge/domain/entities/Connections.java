package com.embrace.challenge.domain.entities;

public class Connections {
    private int initialStreakDay;
    private int daysConnected;

    public Connections(int initialStreakDay, int daysConnected) {
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
