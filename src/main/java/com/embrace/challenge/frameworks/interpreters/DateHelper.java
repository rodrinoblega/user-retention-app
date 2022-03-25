package com.embrace.challenge.frameworks.interpreters;

import com.embrace.challenge.domain.entities.ConnectionDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateHelper {
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

    public static boolean isBetweenDates(ConnectionDate actualConnectionDate, ConnectionDate initialConnectionDate, ConnectionDate finalConnectionDate) {
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMdd");

        String actualYearString = String.format("%02d", actualConnectionDate.getYear());
        String actualMonthString = String.format("%02d", actualConnectionDate.getMonth());
        String actualDayString = String.format("%02d", actualConnectionDate.getDay());

        String initalYearString = String.format("%02d", initialConnectionDate.getYear());
        String initialMonthString = String.format("%02d", initialConnectionDate.getMonth());
        String initialDayString = String.format("%02d", initialConnectionDate.getDay());

        String finalYearString = String.format("%02d", finalConnectionDate.getYear());
        String finalMonthString = String.format("%02d", finalConnectionDate.getMonth());
        String finalDayString = String.format("%02d", finalConnectionDate.getDay());
        try {
            Date actualDate = sdformat.parse(actualYearString.concat(actualMonthString).concat(actualDayString));
            Date initialDate = sdformat.parse(initalYearString.concat(initialMonthString).concat(initialDayString));
            Date finalDate = sdformat.parse(finalYearString.concat(finalMonthString).concat(finalDayString));

            return (actualDate.compareTo(initialDate) > 0 && actualDate.compareTo(finalDate) < 0) ||
                    actualDate.compareTo(initialDate) == 0 ||
                    actualDate.compareTo(finalDate) == 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean isLessDateThan(ConnectionDate actualConnectionDate, ConnectionDate finalConnectionDate) {
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMdd");

        String actualYearString = String.format("%02d", actualConnectionDate.getYear());
        String actualMonthString = String.format("%02d", actualConnectionDate.getMonth());
        String actualDayString = String.format("%02d", actualConnectionDate.getDay());

        String finalYearString = String.format("%02d", finalConnectionDate.getYear());
        String finalMonthString = String.format("%02d", finalConnectionDate.getMonth());
        String finalDayString = String.format("%02d", finalConnectionDate.getDay());

        try {
            Date actualDate = sdformat.parse(actualYearString.concat(actualMonthString).concat(actualDayString));
            Date finalDate = sdformat.parse(finalYearString.concat(finalMonthString).concat(finalDayString));

            return (finalDate.compareTo(actualDate) > 0 ||
                    actualDate.compareTo(finalDate) == 0);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;


    }

    public static ConnectionDate addOneDay(ConnectionDate actualConnectionDate) {
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMdd");

        String actualYearString = String.format("%02d", actualConnectionDate.getYear());
        String actualMonthString = String.format("%02d", actualConnectionDate.getMonth());
        String actualDayString = String.format("%02d", actualConnectionDate.getDay());

        try {
            Date actualDate = sdformat.parse(actualYearString.concat(actualMonthString).concat(actualDayString));
            Calendar calendarUtil = Calendar.getInstance();
            calendarUtil.setTime(actualDate);
            calendarUtil.add(Calendar.DATE, 1);
            return new ConnectionDate(calendarUtil.get(Calendar.DAY_OF_MONTH), calendarUtil.get(Calendar.MONTH) + 1 , calendarUtil.get(Calendar.YEAR));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return actualConnectionDate;
    }
}
