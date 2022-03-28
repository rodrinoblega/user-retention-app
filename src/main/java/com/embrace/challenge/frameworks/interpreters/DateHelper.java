package com.embrace.challenge.frameworks.interpreters;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateHelper {

    public static int secondsToDay(String secondsString) {
        long miliseconds = Long.parseLong(secondsString) * 1000;
        Date date = new Date(miliseconds);

        LocalDateTime localDateTime = date.toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime();
        OffsetDateTime offset = OffsetDateTime.of(localDateTime, ZoneOffset.UTC);

        return offset.getDayOfMonth();
    }

    public static boolean isTheFirstOfMonth(int initialDay) {
        return initialDay == 1;
    }

    public static boolean isBetweenOneThirtyOne(int finalDate) {
        return finalDate <= 31;
    }
}
