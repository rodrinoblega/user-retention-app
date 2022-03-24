package com.embrace.challenge.frameworks.interpreters;

import com.embrace.challenge.domain.entities.ConnectionDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class SecondsToDateInterpreter {
    public static ConnectionDate process(String secondsString) {
        long seconds = Long.parseLong(secondsString);
        long miliSeconds = seconds * 1000;

        DateFormat year = new SimpleDateFormat("yyyy");
        year.setTimeZone(TimeZone.getTimeZone("UTC-3"));

        DateFormat month = new SimpleDateFormat("MM");
        month.setTimeZone(TimeZone.getTimeZone("UTC-3"));

        DateFormat day = new SimpleDateFormat("dd");
        day.setTimeZone(TimeZone.getTimeZone("UTC-3"));

        Date date = new Date(miliSeconds);

        return new ConnectionDate(Integer.parseInt(day.format(date)),
                Integer.parseInt(month.format(date)),
                Integer.parseInt(year.format(date))
        );

    }
}
