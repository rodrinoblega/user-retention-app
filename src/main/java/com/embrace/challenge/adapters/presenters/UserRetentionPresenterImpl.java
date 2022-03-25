package com.embrace.challenge.adapters.presenters;

import com.embrace.challenge.domain.entities.ConnectionDate;
import com.embrace.challenge.frameworks.interpreters.DateHelper;
import com.embrace.challenge.usecases.output.UserRetentionUseCaseResponse;
import com.embrace.challenge.usecases.presenters.UserRetentionPresenter;

import java.util.HashMap;
import java.util.Map;

public class UserRetentionPresenterImpl implements UserRetentionPresenter {

    private static final ConnectionDate START_RANGE_DATE = new ConnectionDate(1, 1, 2021);
    private static final ConnectionDate END_RANGE_DATE = new ConnectionDate(14, 1, 2021);
    private static final int RANGE_BETWEEN_DATES = 14;

    private final Map<ConnectionDate, Integer> mapOfStrikesPerDay = new HashMap<>();

    @Override
    public void present(UserRetentionUseCaseResponse userRetentionUseCaseResponse) {
        userRetentionUseCaseResponse.getUserRetentionCollection().getUserRetentions().forEach(userRetention -> {
            if (isBetweenRange(userRetention.getInitialStreakDate())) {
                mapOfStrikesPerDay.computeIfPresent(userRetention.getInitialStreakDate(), (key, val) -> val = val + 1);
                mapOfStrikesPerDay.computeIfAbsent(userRetention.getInitialStreakDate(), val -> 1);
            }
        });

        ConnectionDate actualConnectionDate = START_RANGE_DATE;

        while(DateHelper.isLessDateThan(actualConnectionDate, END_RANGE_DATE)) {
            String finalString = "";
            finalString = finalString.concat(String.valueOf(actualConnectionDate.getDay()));
            int actualRange = 1;
            while (actualRange < RANGE_BETWEEN_DATES) {
                int finalActualRange = actualRange;
                ConnectionDate finalActualConnectionDate = actualConnectionDate;
                long quantityInThisRange =
                        userRetentionUseCaseResponse.getUserRetentionCollection().getUserRetentions().stream()
                                .filter(userRetention -> userRetention.getConsecutiveDaysConnected() == finalActualRange)
                                .filter(userRetention -> userRetention.getInitialStreakDate().equals(finalActualConnectionDate))
                                .count();
                actualRange = actualRange + 1;

                finalString = finalString.concat(",".concat(String.valueOf(quantityInThisRange)));
            }
            actualConnectionDate = DateHelper.addOneDay(actualConnectionDate);


            System.out.println(finalString);
        }
    }

    private boolean isBetweenRange(ConnectionDate maxInitialStreakDate) {
        return DateHelper.isBetweenDates(maxInitialStreakDate, START_RANGE_DATE, END_RANGE_DATE);
    }

}
