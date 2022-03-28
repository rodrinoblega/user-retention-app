package com.embrace.challenge.domain.entities;

import java.util.*;

public class FirstDayInformation extends Day {

    public FirstDayInformation() {
        this.initialStreakDays = new ArrayList<>(Collections.nCopies(14, 0));
    }

    @Override
    public void recordLogAndUpdateStreakCounter(UserAndLogDate activityUserAndLogDate, List<Day> daysInformation, Map<UserAndLogDate, Connections> logsRegistered) {
        if (thereAreNotLogsForUserInActivityDay(activityUserAndLogDate, logsRegistered)) {
            this.addOneToInitialStreakDay();
            this.createARecordInLogsRegistered(activityUserAndLogDate, new Connections(1, 1), logsRegistered);
        }
    }

    public void addOneToInitialStreakDay() {
        int actualStreakDay = initialStreakDays.get(0);
        initialStreakDays.set(0, actualStreakDay + 1);
    }

    public void createARecordInLogsRegistered(UserAndLogDate userAndLogDate, Connections connections, Map<UserAndLogDate, Connections> logsRegistered) {
        logsRegistered.put(userAndLogDate, connections);
    }
}
