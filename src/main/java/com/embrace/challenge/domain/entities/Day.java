package com.embrace.challenge.domain.entities;

import java.util.List;
import java.util.Map;

public abstract class Day {
    protected List<Integer> initialStreakDays;

    public abstract void recordLogAndUpdateStreakCounter(UserAndLogDate activityUserAndLogDate, List<Day> daysInformation, Map<UserAndLogDate, Connections> logsRegistered);

    public List<Integer> getInitialStreakDays() {
        return initialStreakDays;
    };

    protected void substractOneToPreviousStreakDayAndUpdateActual(int daysConnected) {
        int initialPreviousStreakDay = initialStreakDays.get(daysConnected - 2);
        initialStreakDays.set(daysConnected - 2, initialPreviousStreakDay - 1);

        int actualStreakDay = initialStreakDays.get(daysConnected - 1);
        initialStreakDays.set(daysConnected - 1, actualStreakDay + 1);
    }

    protected boolean thereAreNotLogsForUserInActivityDay(UserAndLogDate activityUserAndLogDate, Map<UserAndLogDate, Connections> logsRegistered) {
        return !logsRegistered.containsKey(new UserAndLogDate(activityUserAndLogDate.getUser(), activityUserAndLogDate.getDay()));
    }
}
