package com.embrace.challenge.domain.entities;

import java.util.List;
import java.util.Map;

public abstract class Day {
    protected List<Integer> initialStreakDays;
    protected Map<String, LogOfConnections> logOfConnectionsByUser;

    public abstract void recordLogAndUpdateStreakCounter(String activityUserId, int activityConnectionDay, List<Day> daysInformation);

    public List<Integer> getInitialStreakDays() {
        return initialStreakDays;
    };

    public void substractOneToPreviousStreakDayAndUpdateActual(int daysConnected) {
        int initialPreviousStreakDay = initialStreakDays.get(daysConnected - 2);
        initialStreakDays.set(daysConnected - 2, initialPreviousStreakDay - 1);

        int actualStreakDay = initialStreakDays.get(daysConnected - 1);
        initialStreakDays.set(daysConnected - 1, actualStreakDay + 1);
    }
}
