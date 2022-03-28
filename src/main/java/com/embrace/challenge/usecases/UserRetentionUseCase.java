package com.embrace.challenge.usecases;

import com.embrace.challenge.domain.entities.*;
import com.embrace.challenge.frameworks.exceptions.CSVException;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.interpreters.DateHelper;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.*;

public class UserRetentionUseCase {
    private static final String ERRROR_MESSAGE = "There was a problem reading CSV";
    private final Instrumentation instrumentation;
    private Map<UserAndLogDate, LogOfConnections> logsRegistered;
    private List<Day> daysInformations;

    public UserRetentionUseCase(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
        this.logsRegistered = new HashMap<>();
        this.daysInformations = new ArrayList<>();
    }

    public List<Day> process(String path, DateRange dateRange) {
        try {
            this.initialize();

            CSVReader csv = new CSVReader(new FileReader(path));
            String[] lineInArray;
            while ((lineInArray = csv.readNext()) != null) {

                String activityUserId = obtainGetUserId(lineInArray);
                int activityConnectionDay = getActivityConnectionDay(lineInArray);

                Day day = obtainDayInformation(activityConnectionDay);
                day.recordLogAndUpdateStreakCounter(new UserAndLogDate(activityUserId, activityConnectionDay), daysInformations, logsRegistered);
            }
        } catch (Exception e) {
            instrumentation.logMessage(ERRROR_MESSAGE);
            throw new CSVException(ERRROR_MESSAGE);
        }

        return daysInformations;
    }

    private Day obtainDayInformation(int activityConnectionDay) {
        return daysInformations.get(activityConnectionDay - 1);
    }

    private int getActivityConnectionDay(String[] lineInArray) {
        return DateHelper.secondsToDay(lineInArray[0]);
    }

    private String obtainGetUserId(String[] lineInArray) {
        return lineInArray[1];
    }

    private List<Day> initialize() {
        this.daysInformations.add(new FirstDayInformation());

        for (int i = 2; i <= 14; i ++ ) {
            daysInformations.add(new DayInformation());
        }

        return daysInformations;
    }
}
