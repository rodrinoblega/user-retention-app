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
    public void recordLogAndUpdateStreakCounter(UserAndLogDate activityUserAndLogDate, List<Day> daysInformation, Map<UserAndLogDate, Connections> logsRegistered) {

        if (thereAreNotLogsForUserInActivityDay(activityUserAndLogDate, logsRegistered)) {
            if (thereAreNotLogsForUserInLastDay(activityUserAndLogDate, logsRegistered)) {
                this.addOneToInitialStreakDay();
                this.createARecordInLogsRegistered(activityUserAndLogDate, new Connections(activityUserAndLogDate.getDay(), 1), logsRegistered);
            } else {
                Connections previousDayLogsUser = obtainLogsForUser(activityUserAndLogDate, logsRegistered);

                this.updateARecordInLogsRegistered(activityUserAndLogDate, previousDayLogsUser, logsRegistered);
                this.updateOcurrencesOfStreakDay(daysInformation, previousDayLogsUser);
            }
        }
    }

    private void updateOcurrencesOfStreakDay(List<Day> daysInformation, Connections previousLogsOfConnectionByUser) {
        Day initialStreakDayInformation = obtainDayInformation(previousLogsOfConnectionByUser.getInitialStreakDay(), daysInformation);
        initialStreakDayInformation.substractOneToPreviousStreakDayAndUpdateActual(previousLogsOfConnectionByUser.getDaysConnected() + 1);
    }

    private void updateARecordInLogsRegistered(UserAndLogDate activityUserAndLogDate, Connections previousLogsOfConnectionByUser, Map<UserAndLogDate, Connections> logOfConnectionsByUser) {
        Connections connections = new Connections(
                previousLogsOfConnectionByUser.getInitialStreakDay(),
                previousLogsOfConnectionByUser.getDaysConnected() + 1);

        logOfConnectionsByUser.put(activityUserAndLogDate, connections);
    }

    public void createARecordInLogsRegistered(UserAndLogDate userAndLogDate, Connections connections, Map<UserAndLogDate, Connections> logsRegistered) {
        logsRegistered.put(userAndLogDate, connections);
    }

    private Connections obtainLogsForUser(UserAndLogDate activityUserAndLogDate, Map<UserAndLogDate, Connections> previousLogsConnectionsByUser) {

        return previousLogsConnectionsByUser.get(new UserAndLogDate(activityUserAndLogDate.getUser(),
                activityUserAndLogDate.getDay() - 1));
    }

    private boolean thereAreNotLogsForUserInLastDay(UserAndLogDate activityUserAndLogDate, Map<UserAndLogDate, Connections> previousLogsConnectionsByUser) {
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
