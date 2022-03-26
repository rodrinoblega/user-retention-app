package com.embrace.challenge.adapters.interpreters;

import com.embrace.challenge.domain.entities.DateRange;

public interface DateRangeInterpreter {
    DateRange obtainsDateRangeIfApply(String[] ars);
}
