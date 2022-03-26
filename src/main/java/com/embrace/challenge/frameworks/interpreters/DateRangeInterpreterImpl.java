package com.embrace.challenge.frameworks.interpreters;

import com.embrace.challenge.adapters.interpreters.DateRangeInterpreter;
import com.embrace.challenge.domain.entities.ConnectionDate;
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
            String initialString = ars[1];
            String finalString = ars[2];

            ConnectionDate initialDate = DateHelper.stringToDate(initialString);
            ConnectionDate finalDate = DateHelper.stringToDate(finalString);

            if (areInTheSameMonthAndStartTheFirstOfMonth(initialDate, finalDate)) {
                return new DateRange(initialDate, finalDate);
            }
            instrumentation.logMessage("There was an error in your range dates. They should have a ddMMyyyy format, be in the same month and start the first day of the month.");
            instrumentation.logMessage("We will take the default date range: 01/01/2021 - 14/01/2021.");

        }
        return new DateRange(new ConnectionDate(1, 1, 2021), new ConnectionDate(14, 1, 2021));
    }

    private boolean areInTheSameMonthAndStartTheFirstOfMonth(ConnectionDate initialDate, ConnectionDate finalDate) {
        return DateHelper.areInTheSameMonthAndStartTheFirstOfMonth(initialDate, finalDate);
    }

    private boolean validateIfThereAreRangeDatesAsArgs(String[] ars) {
        return ars.length == 3;
    }

}
