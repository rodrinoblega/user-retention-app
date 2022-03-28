package com.embrace.challenge.domain.entities;

import java.util.*;

public class DayInformation extends Day {

    public DayInformation() {
        this.initialStreakDays = new ArrayList<>(Collections.nCopies(14, 0));
    }

    public List<Integer> getInitialStreakDays() {
        return initialStreakDays;
    }

    @Override
    public void recordLogAndUpdateStreakCounter(UserAndLogDate activityUserAndLogDate, List<Day> daysInformation, Map<UserAndLogDate, LogOfConnections> logsRegistered) {

        if (thereAreNotLogsForUserInActivityDay(activityUserAndLogDate, logsRegistered)) {
            if (thereAreNotLogsForUserInLastDay(activityUserAndLogDate, logsRegistered)) {
                this.addOneToInitialStreakDay();
                this.createARecordInLogsRegistered(activityUserAndLogDate, new LogOfConnections(activityUserAndLogDate.getDay(), 1), logsRegistered);
            } else {
                LogOfConnections previousDayLogsUser = obtainLogsForUser(activityUserAndLogDate, logsRegistered);

                this.updateARecordInLogsRegistered(activityUserAndLogDate, previousDayLogsUser, logsRegistered);
                this.updateOcurrencesOfStreakDay(daysInformation, previousDayLogsUser);
            }
        }
    }

    private void updateOcurrencesOfStreakDay(List<Day> daysInformation, LogOfConnections previousLogsOfConnectionByUser) {
        Day initialStreakDayInformation = obtainDayInformation(previousLogsOfConnectionByUser.getInitialStreakDay(), daysInformation);
        initialStreakDayInformation.substractOneToPreviousStreakDayAndUpdateActual(previousLogsOfConnectionByUser.getDaysConnected() + 1);
    }

    private void updateARecordInLogsRegistered(UserAndLogDate activityUserAndLogDate, LogOfConnections previousLogsOfConnectionByUser, Map<UserAndLogDate, LogOfConnections> logOfConnectionsByUser) {
        LogOfConnections logOfConnections = new LogOfConnections(
                previousLogsOfConnectionByUser.getInitialStreakDay(),
                previousLogsOfConnectionByUser.getDaysConnected() + 1);

        logOfConnectionsByUser.put(activityUserAndLogDate, logOfConnections);
    }

    public void createARecordInLogsRegistered(UserAndLogDate userAndLogDate, LogOfConnections logOfConnections, Map<UserAndLogDate, LogOfConnections> logsRegistered) {
        logsRegistered.put(userAndLogDate, logOfConnections);
    }

    private LogOfConnections obtainLogsForUser(UserAndLogDate activityUserAndLogDate, Map<UserAndLogDate, LogOfConnections> previousLogsConnectionsByUser) {

        return previousLogsConnectionsByUser.get(new UserAndLogDate(activityUserAndLogDate.getUser(),
                activityUserAndLogDate.getDay() - 1));
    }

    private boolean thereAreNotLogsForUserInLastDay(UserAndLogDate activityUserAndLogDate, Map<UserAndLogDate, LogOfConnections> previousLogsConnectionsByUser) {
        return !previousLogsConnectionsByUser.containsKey(
                new UserAndLogDate(
                        activityUserAndLogDate.getUser(),
                        activityUserAndLogDate.getDay() - 1)
        );
    }

    private Day obtainDayInformation(int activityConnectionDay, List<Day> daysInformation) {
        return daysInformation.get(activityConnectionDay - 1);
    }

    public void addOneToInitialStreakDay() {
        int actualStreakDay = initialStreakDays.get(0);
        initialStreakDays.set(0, actualStreakDay + 1);
    }
}
