package com.embrace.challenge.domain.entities;

import java.util.*;

public class DayInformation extends Day {

    public DayInformation() {
        this.initialStreakDays = new ArrayList<>(Collections.nCopies(14, 0));
        this.logOfConnectionsByUser = new HashMap<>();
    }

    public List<Integer> getInitialStreakDays() {
        return initialStreakDays;
    }

    @Override
    public void recordLogAndUpdateStreakCounter(String activityUserId, int activityConnectionDay, List<Day> daysInformation) {

        Map<String, LogOfConnections> previousDayLogsByUser = obtainLogsOfPreviousDay(activityConnectionDay, daysInformation);

        if (!anyLogsForUserInActualDay(activityUserId)) {
            if (!anyLogsForUserInLastDay(activityUserId, previousDayLogsByUser)) {
                this.addOneToInitialStreakDay();
                this.updateConnectionsByUser(activityUserId, new LogOfConnections(activityConnectionDay, 1));
            } else {
                LogOfConnections previousLogs = obtainLogsForUser(activityUserId, previousDayLogsByUser);
                Map<String, LogOfConnections> logsByUser = this.logOfConnectionsByUser;

                updateActualDayWithConnectionInfo(activityUserId, previousLogs, logsByUser);

                updateOcurrencesOfStreakDay(daysInformation, previousLogs);
            }
        }
    }

    private void updateOcurrencesOfStreakDay(List<Day> daysInformation, LogOfConnections previousLogsOfConnectionByUser) {
        Day initialStreakDayInformation = obtainDayInformation(previousLogsOfConnectionByUser.getInitialStreakDay(), daysInformation);
        initialStreakDayInformation.substractOneToPreviousStreakDayAndUpdateActual(previousLogsOfConnectionByUser.getDaysConnected() + 1);
    }

    private void updateActualDayWithConnectionInfo(String activityUserId, LogOfConnections previousLogsOfConnectionByUser, Map<String, LogOfConnections> logOfConnectionsByUser) {
        LogOfConnections logOfConnections = new LogOfConnections(
                previousLogsOfConnectionByUser.getInitialStreakDay(),
                previousLogsOfConnectionByUser.getDaysConnected() + 1);

        logOfConnectionsByUser.put(activityUserId, logOfConnections);
    }

    private LogOfConnections obtainLogsForUser(String activityUserId, Map<String, LogOfConnections> previousLogsConnectionsByUser) {
        return previousLogsConnectionsByUser.get(activityUserId);
    }

    private Map<String, LogOfConnections> obtainLogsOfPreviousDay(int activityConnectionDay, List<Day> daysInformation) {
        Day previousInformation = obtainPreviousDayInformation(activityConnectionDay, daysInformation);
        return previousInformation.logOfConnectionsByUser;
    }

    private boolean anyLogsForUserInActualDay(String activityUserId) {
        return this.logOfConnectionsByUser.containsKey(activityUserId);
    }

    private boolean anyLogsForUserInLastDay(String activityUserId, Map<String, LogOfConnections> previousLogsConnectionsByUser) {
        return previousLogsConnectionsByUser.containsKey(activityUserId);
    }

    private Day obtainDayInformation(int activityConnectionDay, List<Day> daysInformation) {
        return daysInformation.get(activityConnectionDay - 1);
    }

    private Day obtainPreviousDayInformation(int activityConnectionDay, List<Day> daysInformation) {
        return daysInformation.get(activityConnectionDay - 2);
    }

    public void addOneToInitialStreakDay() {
        int actualStreakDay = initialStreakDays.get(0);
        initialStreakDays.set(0, actualStreakDay + 1);
    }

    public void updateConnectionsByUser(String user, LogOfConnections logOfConnections) {
        logOfConnectionsByUser.put(user, logOfConnections);
    }
}
