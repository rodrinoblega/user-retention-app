package com.embrace.challenge.frameworks.interpreters;

import com.embrace.challenge.adapters.interpreters.DateRangeInterpreter;
import com.embrace.challenge.domain.entities.DateRange;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;

public class DateRangeInterpreterImpl implements DateRangeInterpreter {
    private final Instrumentation instrumentation;

    public DateRangeInterpreterImpl(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    @Override
    public DateRange obtainsDateRangeIfApply(String[] ars) {
        if (validateIfThereAreRangeDatesAsArgs(ars)) {
            try {
                int initialDay = Integer.parseInt(ars[1].substring(0,2));
                int finalDay = Integer.parseInt(ars[2].substring(0,2));

                if (isTheFirstOfMonth(initialDay) && isBetweenOneThirtyOne(finalDay)) {
                    System.out.println("The new date range is from day " + initialDay +" to " + finalDay + ".");
                    return new DateRange(initialDay, finalDay);
                }
                notifyError();
            } catch (Exception e) {
                notifyError();
                return new DateRange(1, 14);
            }
        }
        return new DateRange(1, 14);
    }

    private void notifyError() {
        instrumentation.logMessage("There was an error in your range dates. They should have a dd format, be in the same month, be in the range from 1 to 31, and start the first day of the month.");
        instrumentation.logMessage("We will take the default date range: 01/01/2021 - 14/01/2021.");
    }

    private boolean isTheFirstOfMonth(int initialDate) {
        return DateHelper.isTheFirstOfMonth(initialDate);
    }

    private boolean isBetweenOneThirtyOne(int finalDate) {
        return DateHelper.isBetweenOneThirtyOne(finalDate);
    }

    private boolean validateIfThereAreRangeDatesAsArgs(String[] ars) {
        if (ars.length == 2) {
            instrumentation.logMessage("You have to specify the final date range.");
            instrumentation.logMessage("We will take the default date range: 01/01/2021 - 14/01/2021.");
            return false;
        }
        return ars.length == 3;
    }

}
