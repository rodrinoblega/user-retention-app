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
                int initialDay = Integer.parseInt(ars[1]);
                int finalDay = Integer.parseInt(ars[2]);

                if (isTheFirstOfMonth(initialDay)) {
                    return new DateRange(initialDay, finalDay);
                }
                instrumentation.logMessage("There was an error in your range dates. They should have a dd format, be in the same month and start the first day of the month.");
                instrumentation.logMessage("We will take the default date range: 01/01/2021 - 14/01/2021.");
            } catch (Exception e) {
                instrumentation.logMessage("There was an error in your range dates. They should have a dd format, be in the same month and start the first day of the month.");
                return new DateRange(1, 14);
            }
        }
        return new DateRange(1, 14);
    }

    private boolean isTheFirstOfMonth(int initialDate) {
        return DateHelper.isTheFirstOfMonth(initialDate);
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
