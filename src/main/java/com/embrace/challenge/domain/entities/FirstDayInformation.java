package com.embrace.challenge.domain.entities;

import java.util.*;

public class FirstDayInformation extends Day {

    public FirstDayInformation(int finalRange) {
        this.initialStreakDays = new ArrayList<>(Collections.nCopies(finalRange, 0));
    }

    @Override
    public void recordActivityAndUpdateStreakCounter(UserAndLogDate activityUserAndLogDate, List<Day> daysInformation, Map<UserAndLogDate, Connection> logsRegistered) {
        if (!hasTheUserAlreadyConnectedThisDay(activityUserAndLogDate, logsRegistered)) {
            this.addOneToInitialStreakDay();
            this.createARecordInLogsRegistered(activityUserAndLogDate, new Connection(1, 1), logsRegistered);
        }
    }

    public void addOneToInitialStreakDay() {
        int actualStreakDay = initialStreakDays.get(0);
        initialStreakDays.set(0, actualStreakDay + 1);
    }

    public void createARecordInLogsRegistered(UserAndLogDate userAndLogDate, Connection connection, Map<UserAndLogDate, Connection> logsRegistered) {
        logsRegistered.put(userAndLogDate, connection);
    }
}
