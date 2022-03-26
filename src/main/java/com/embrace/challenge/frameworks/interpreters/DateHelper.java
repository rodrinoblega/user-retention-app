package com.embrace.challenge.frameworks.interpreters;

import com.embrace.challenge.domain.entities.ConnectionDate;
import com.embrace.challenge.frameworks.exceptions.DateHelperException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateHelper {

    public static ConnectionDate secondsToConnectionDate(String secondsString) {
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
        try {

            SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMdd");

            TwoDecimalFormattedConnectionDate actualConnectionDateFormatted = new TwoDecimalFormattedConnectionDate(actualConnectionDate);
            TwoDecimalFormattedConnectionDate initialConnectionDateFormatted = new TwoDecimalFormattedConnectionDate(initialConnectionDate);
            TwoDecimalFormattedConnectionDate finalConnectionDateFormatted = new TwoDecimalFormattedConnectionDate(finalConnectionDate);

            Date actualDate = sdformat.parse(actualConnectionDateFormatted.yyyyMMdd());
            Date initialDate = sdformat.parse(initialConnectionDateFormatted.yyyyMMdd());
            Date finalDate = sdformat.parse(finalConnectionDateFormatted.yyyyMMdd());

            return (actualDate.compareTo(initialDate) > 0 && actualDate.compareTo(finalDate) < 0) ||
                    actualDate.compareTo(initialDate) == 0 ||
                    actualDate.compareTo(finalDate) == 0;

        } catch (Exception e) {
            throw new DateHelperException("There was an error in isBetweenDates() method");
        }
    }

    public static boolean isLessDateThan(ConnectionDate actualConnectionDate, ConnectionDate finalConnectionDate) {
        try {

            SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMdd");

            TwoDecimalFormattedConnectionDate actualConnectionDateFormatted = new TwoDecimalFormattedConnectionDate(actualConnectionDate);
            TwoDecimalFormattedConnectionDate finalConnectionDateFormatted = new TwoDecimalFormattedConnectionDate(finalConnectionDate);

            Date actualDate = sdformat.parse(actualConnectionDateFormatted.yyyyMMdd());
            Date finalDate = sdformat.parse(finalConnectionDateFormatted.yyyyMMdd());

            return (finalDate.compareTo(actualDate) > 0 ||
                    actualDate.compareTo(finalDate) == 0);

        } catch (Exception e) {
            throw new DateHelperException("There was an error in isLessDateThan() method");
        }
    }

    public static ConnectionDate addOneDay(ConnectionDate actualConnectionDate) {
        try {

            SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMdd");

            TwoDecimalFormattedConnectionDate actualConnectionDateFormatted = new TwoDecimalFormattedConnectionDate(actualConnectionDate);

            Date actualDate = sdformat.parse(actualConnectionDateFormatted.yyyyMMdd());
            Calendar calendarUtil = Calendar.getInstance();
            calendarUtil.setTime(actualDate);
            calendarUtil.add(Calendar.DATE, 1);

            return new ConnectionDate(calendarUtil.get(Calendar.DAY_OF_MONTH), calendarUtil.get(Calendar.MONTH) + 1, calendarUtil.get(Calendar.YEAR));

        } catch (ParseException e) {
            throw new DateHelperException("There was an error in addOneDay() method");
        }
    }

    public static ConnectionDate stringToDate(String initialString) {
        try {

            Date initialDate = new SimpleDateFormat("ddMMyyyy").parse(initialString);

            Calendar calendarUtil = Calendar.getInstance();
            calendarUtil.setTime(initialDate);

            return new ConnectionDate(calendarUtil.get(Calendar.DAY_OF_MONTH), calendarUtil.get(Calendar.MONTH) + 1, calendarUtil.get(Calendar.YEAR));

        } catch (ParseException e) {
            throw new DateHelperException("There was an error in stringToDate()");
        }
    }

    public static boolean areInTheSameMonth(ConnectionDate initialDate, ConnectionDate finalDate) {
        return initialDate.getDay() == 1 &&
                initialDate.isLessThan(finalDate) &&
                initialDate.getYear() == finalDate.getYear() &&
                initialDate.getMonth() == finalDate.getMonth();
    }

    private static class TwoDecimalFormattedConnectionDate {
        private final String formattedDay;
        private final String formattedMonth;
        private final String formattedYear;

        public TwoDecimalFormattedConnectionDate(ConnectionDate connectionDate) {
            this.formattedDay = String.format("%02d", connectionDate.getYear());
            this.formattedMonth = String.format("%02d", connectionDate.getMonth());
            this.formattedYear = String.format("%02d", connectionDate.getDay());
        }

        public String yyyyMMdd() {
            return formattedYear.concat(formattedMonth).concat(formattedDay);
        }
    }
}
