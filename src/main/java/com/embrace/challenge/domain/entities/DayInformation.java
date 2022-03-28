package com.embrace.challenge.domain.entities;

import java.util.*;

public class DayInformation extends Day {

    public DayInformation(int finalRange) {
        this.initialStreakDays = new ArrayList<>(Collections.nCopies(finalRange, 0));
    }

    public List<Integer> getInitialStreakDays() {
        return initialStreakDays;
    }

    @Override
    public void recordActivityAndUpdateStreakCounter(UserAndLogDate activityUserAndLogDate, List<Day> daysInformation, Map<UserAndLogDate, Connection> logsRegistered) {

        if (!hasTheUserAlreadyConnectedThisDay(activityUserAndLogDate, logsRegistered)) {
            if (!didTheUserConnectTheDayBefore(activityUserAndLogDate, logsRegistered)) {
                this.addOneToInitialStreakDay();
                this.createARecordInLogsRegistered(activityUserAndLogDate, new Connection(activityUserAndLogDate.getDay(), 1), logsRegistered);
            } else {
                Connection previousDayLogsUser = obtainLogsForUser(activityUserAndLogDate, logsRegistered);

                this.updateARecordInLogsRegistered(activityUserAndLogDate, previousDayLogsUser, logsRegistered);
                this.updateOcurrencesOfStreakDay(daysInformation, previousDayLogsUser);
            }
        }
    }

    private void updateOcurrencesOfStreakDay(List<Day> daysInformation, Connection previousLogsOfConnectionByUser) {
        Day initialStreakDayInformation = obtainDayInformation(previousLogsOfConnectionByUser.getInitialStreakDay(), daysInformation);
        initialStreakDayInformation.substractOneToPreviousStreakDayAndUpdateActual(previousLogsOfConnectionByUser.getDaysConnected() + 1);
    }

    private void updateARecordInLogsRegistered(UserAndLogDate activityUserAndLogDate, Connection previousLogsOfConnectionByUser, Map<UserAndLogDate, Connection> logOfConnectionsByUser) {
        Connection connection = new Connection(
                previousLogsOfConnectionByUser.getInitialStreakDay(),
                previousLogsOfConnectionByUser.getDaysConnected() + 1);

        logOfConnectionsByUser.put(activityUserAndLogDate, connection);
    }

    public void createARecordInLogsRegistered(UserAndLogDate userAndLogDate, Connection connection, Map<UserAndLogDate, Connection> logsRegistered) {
        logsRegistered.put(userAndLogDate, connection);
    }

    private Connection obtainLogsForUser(UserAndLogDate activityUserAndLogDate, Map<UserAndLogDate, Connection> previousLogsConnectionsByUser) {

        return previousLogsConnectionsByUser.get(new UserAndLogDate(activityUserAndLogDate.getUser(),
                activityUserAndLogDate.getDay() - 1));
    }

    private boolean didTheUserConnectTheDayBefore(UserAndLogDate activityUserAndLogDate, Map<UserAndLogDate, Connection> previousLogsConnectionsByUser) {
        return previousLogsConnectionsByUser.containsKey(
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
