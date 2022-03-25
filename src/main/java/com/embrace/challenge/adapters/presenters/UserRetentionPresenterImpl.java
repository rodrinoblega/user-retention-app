package com.embrace.challenge.adapters.presenters;

import com.embrace.challenge.domain.entities.ConnectionDate;
import com.embrace.challenge.domain.entities.UserRetention;
import com.embrace.challenge.frameworks.interpreters.DateHelper;
import com.embrace.challenge.usecases.output.UserRetentionUseCaseResponse;
import com.embrace.challenge.usecases.presenters.UserRetentionPresenter;

import java.util.List;
import java.util.stream.IntStream;

public class UserRetentionPresenterImpl implements UserRetentionPresenter {

    private static final ConnectionDate START_RANGE_DATE = new ConnectionDate(1, 1, 2021);
    private static final ConnectionDate END_RANGE_DATE = new ConnectionDate(14, 1, 2021);

    @Override
    public void present(UserRetentionUseCaseResponse userRetentionUseCaseResponse) {
        List<UserRetention> userRetentions = userRetentionUseCaseResponse.getUserRetentionCollection().getUserRetentions();
        ConnectionDate dateToAnalizeStreaks = START_RANGE_DATE;
        

        while (dateToAnalizeStreaks.isLessThan(END_RANGE_DATE)) {
            
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(dateToAnalizeStreaks.getDay());

            processConsecutivelyOfAllPossibleInitialStreakDates(userRetentions, dateToAnalizeStreaks, stringBuilder);
            
            dateToAnalizeStreaks = addOneDay(dateToAnalizeStreaks);
            System.out.println(stringBuilder);
        }
    }

    private void processConsecutivelyOfAllPossibleInitialStreakDates(List<UserRetention> userRetentions, ConnectionDate analizedDate, StringBuilder stringBuilder) {
        int initialPossibleDayOfStreak = START_RANGE_DATE.getDay();
        int finalPossibleDayOfStreak = END_RANGE_DATE.getDay();

        IntStream.range(initialPossibleDayOfStreak, finalPossibleDayOfStreak).forEach(
                currentPossibleStreakDate -> {
                    int quantityInThisRange = obtainConsecutivenessOfPossiblestreakDay(userRetentions, analizedDate, currentPossibleStreakDate);
                    stringBuilder.append(",").append(quantityInThisRange);
                }
        );
    }

    private int obtainConsecutivenessOfPossiblestreakDay(List<UserRetention> userRetentions, ConnectionDate analizedDate, int currentPossibleStreakDate) {
        return (int) userRetentions.stream()
                .filter(userRetention -> userRetention.getConsecutiveDaysConnected() == currentPossibleStreakDate)
                .filter(userRetention -> userRetention.getInitialStreakDate().equals(analizedDate))
                .count();
    }

    private ConnectionDate addOneDay(ConnectionDate streakDateToAnalize) {
        return DateHelper.addOneDay(streakDateToAnalize);
    }
}
