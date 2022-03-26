package com.embrace.challenge.adapters.presenters;

import com.embrace.challenge.domain.entities.ConnectionDate;
import com.embrace.challenge.domain.entities.DateRange;
import com.embrace.challenge.domain.entities.UserRetention;
import com.embrace.challenge.frameworks.interpreters.DateHelper;
import com.embrace.challenge.usecases.output.UserRetentionUseCaseResponse;
import com.embrace.challenge.usecases.presenters.UserRetentionPresenter;

import java.util.List;
import java.util.stream.IntStream;

public class UserRetentionPresenterImpl implements UserRetentionPresenter {

    private ConnectionDate sartRangeDate;
    private ConnectionDate endRangeDate;

    @Override
    public void present(UserRetentionUseCaseResponse userRetentionUseCaseResponse, DateRange dateRange) {
        setInitialAndFinalRangeDate(dateRange);

        List<UserRetention> userRetentions = userRetentionUseCaseResponse.obtainListOfUserRetention();
        ConnectionDate dateToAnalizeStreaks = sartRangeDate;
        

        while (dateToAnalizeStreaks.isLessThan(endRangeDate)) {
            
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(dateToAnalizeStreaks.getDay());

            processConsecutivelyOfAllPossibleInitialStreakDates(userRetentions, dateToAnalizeStreaks, stringBuilder);

            dateToAnalizeStreaks = addOneDay(dateToAnalizeStreaks);
        }
    }

    private void setInitialAndFinalRangeDate(DateRange dateRange) {
        sartRangeDate = dateRange.getInitialDate();
        endRangeDate = dateRange.getFinalDate();
    }

    private void processConsecutivelyOfAllPossibleInitialStreakDates(List<UserRetention> userRetentions, ConnectionDate dateToAnalizeStreaks, StringBuilder stringBuilder) {
        int initialPossibleDayOfStreak = sartRangeDate.getDay();
        int finalPossibleDayOfStreak = endRangeDate.getDay();

        IntStream.rangeClosed(initialPossibleDayOfStreak, finalPossibleDayOfStreak).forEach(
                currentPossibleInitialStreakDate -> {
                    int quantityInThisRange = obtainConsecutivenessOfPossibleStreakDay(userRetentions, dateToAnalizeStreaks, currentPossibleInitialStreakDate);
                    stringBuilder.append(",").append(quantityInThisRange);
                }
        );

        System.out.println(stringBuilder);
    }

    private int obtainConsecutivenessOfPossibleStreakDay(List<UserRetention> userRetentions, ConnectionDate analizedDate, int currentPossibleStreakDate) {
        return (int) userRetentions.stream()
                .filter(userRetention -> userRetention.getConsecutiveDaysConnected() == currentPossibleStreakDate)
                .filter(userRetention -> userRetention.getInitialStreakDate().equals(analizedDate))
                .count();
    }

    private ConnectionDate addOneDay(ConnectionDate streakDateToAnalize) {
        return DateHelper.addOneDay(streakDateToAnalize);
    }
}
