package com.embrace.challenge.adapters.presenters;

import com.embrace.challenge.domain.entities.*;
import com.embrace.challenge.frameworks.interpreters.DateHelper;
import com.embrace.challenge.usecases.presenters.UserRetentionPresenterV2;

import java.util.List;
import java.util.Map;

public class UserRetentionPresenterV2Impl implements UserRetentionPresenterV2 {

    private int sartRangeDate;
    private int endRangeDate;

    @Override
    public void present(List<Day> dayInformations, DateRange dateRange) {
        setInitialAndFinalRangeDate(dateRange);
        int dayToAnalizeStreaks = sartRangeDate;

        while (dayToAnalizeStreaks - 1 <= endRangeDate - 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(dayToAnalizeStreaks);

            List<Integer> getInitialStreakDays = dayInformations.get(dayToAnalizeStreaks - 1).getInitialStreakDays();

            for(Integer initialStreakDay : getInitialStreakDays) {
                stringBuilder.append(",").append(initialStreakDay);
            }

            System.out.println(stringBuilder);

            dayToAnalizeStreaks = dayToAnalizeStreaks + 1;
        }
    }

    private void setInitialAndFinalRangeDate(DateRange dateRange) {
        sartRangeDate = dateRange.getInitialDay();
        endRangeDate = dateRange.getFinalDay();
    }
}
