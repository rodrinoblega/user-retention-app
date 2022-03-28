package com.embrace.challenge.domain.entities;

import java.util.*;

public class FirstDayInformation extends Day {

    public FirstDayInformation() {
        this.initialStreakDays = new ArrayList<>(Collections.nCopies(14, 0));
        this.logOfConnectionsByUser = new HashMap<>();
    }

    @Override
    public void recordLogAndUpdateStreakCounter(String activityUserId, int activityConnectionDay, List<Day> daysInformation) {
        if (!anyLogsForUserInActualDay(activityUserId)) {
            this.addOneToInitialStreakDay();
            this.updateConnectionsWith(activityUserId, new LogOfConnections(1, 1));
        }
    }

    private boolean anyLogsForUserInActualDay(String activityUserId) {
        return this.logOfConnectionsByUser.containsKey(activityUserId);
    }

    public void addOneToInitialStreakDay() {
        int actualStreakDay = initialStreakDays.get(0);
        initialStreakDays.set(0, actualStreakDay + 1);
    }

    public void updateConnectionsWith(String user, LogOfConnections logOfConnections) {
        logOfConnectionsByUser.put(user, logOfConnections);

    }
}
