package com.embrace.challenge.frameworks.interpreters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateHelper {

    public static int secondsToDay(String secondsString) {
        long seconds = Long.parseLong(secondsString);
        long miliSeconds = seconds * 1000;

        DateFormat day = new SimpleDateFormat("dd");
        day.setTimeZone(TimeZone.getTimeZone("UTC-3"));

        Date date = new Date(miliSeconds);

        return Integer.parseInt(day.format(date));
    }

    public static boolean isTheFirstOfMonth(int initialDay) {
        return initialDay == 1;
    }
}
