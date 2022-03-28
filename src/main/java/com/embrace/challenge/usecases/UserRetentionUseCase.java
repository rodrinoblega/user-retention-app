package com.embrace.challenge.usecases;

import com.embrace.challenge.domain.entities.*;
import com.embrace.challenge.frameworks.exceptions.CSVException;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.interpreters.DateHelper;
import com.embrace.challenge.usecases.output.UserRetentionUseCaseResponse;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.*;

public class UserRetentionUseCase {
    private static final String ERRROR_MESSAGE = "There was a problem reading CSV";
    private final Instrumentation instrumentation;
    private final Map<UserAndLogDate, Connection> logsRegistered;
    private final List<Day> streakDaysInformation;

    public UserRetentionUseCase(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
        this.logsRegistered = new HashMap<>();
        this.streakDaysInformation = new ArrayList<>();
    }

    public UserRetentionUseCaseResponse process(String path, DateRange dateRange) {
        try {
            this.initializeStreakDaysInformation(dateRange);

            CSVReader csv = new CSVReader(new FileReader(path));
            String[] lineInArray;
            while ((lineInArray = csv.readNext()) != null) {

                String activityUserId = obtainGetUserId(lineInArray);
                int activityConnectionDay = getActivityConnectionDay(lineInArray);
                if (activityConnectionDay <= dateRange.getFinalDay()) {
                    Day day = obtainDayInformation(activityConnectionDay);

                    day.recordActivityAndUpdateStreakCounter(
                            new UserAndLogDate(activityUserId, activityConnectionDay),
                            streakDaysInformation,
                            logsRegistered
                    );
                }
            }
        } catch (Exception e) {
            instrumentation.logMessage(ERRROR_MESSAGE);
            throw new CSVException(ERRROR_MESSAGE);
        }

        return new UserRetentionUseCaseResponse(streakDaysInformation);
    }

    private Day obtainDayInformation(int activityConnectionDay) {
        return streakDaysInformation.get(activityConnectionDay - 1);
    }

    private int getActivityConnectionDay(String[] lineInArray) {
        return DateHelper.secondsToDay(lineInArray[0]);
    }

    private String obtainGetUserId(String[] lineInArray) {
        return lineInArray[1];
    }

    private List<Day> initializeStreakDaysInformation(DateRange dateRange) {
        this.streakDaysInformation.add(new FirstDayInformation(dateRange.getFinalDay()));

        for (int i = 2; i <= dateRange.getFinalDay(); i ++ ) {
            streakDaysInformation.add(new DayInformation(dateRange.getFinalDay()));
        }

        return streakDaysInformation;
    }
}
