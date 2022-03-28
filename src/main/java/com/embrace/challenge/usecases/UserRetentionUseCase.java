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
    private final List<Day> dayInformations = List.of(
            new FirstDayInformation(),
            new DayInformation(),
            new DayInformation(),
            new DayInformation(),
            new DayInformation(),
            new DayInformation(),
            new DayInformation(),
            new DayInformation(),
            new DayInformation(),
            new DayInformation(),
            new DayInformation(),
            new DayInformation(),
            new DayInformation(),
            new DayInformation()
    );

    public UserRetentionUseCase(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    public List<Day> process(String path, DateRange dateRange) {
        try {

            CSVReader csv = new CSVReader(new FileReader(path));
            String[] lineInArray;
            while ((lineInArray = csv.readNext()) != null) {

                String activityUserId = obtainGetUserId(lineInArray);
                int activityConnectionDay = getActivityConnectionDay(lineInArray);

                Day day = obtainDayInformation(activityConnectionDay);

                day.recordLogAndUpdateStreakCounter(activityUserId, activityConnectionDay, dayInformations);
            }
        } catch (Exception e) {
            instrumentation.logMessage(ERRROR_MESSAGE);
            throw new CSVException(ERRROR_MESSAGE);
        }

        return dayInformations;
    }

    private Day obtainDayInformation(int activityConnectionDay) {
        return dayInformations.get(activityConnectionDay - 1);
    }

    private int getActivityConnectionDay(String[] lineInArray) {
        return DateHelper.secondsToDay(lineInArray[0]);
    }

    private String obtainGetUserId(String[] lineInArray) {
        return lineInArray[1];
    }
}
