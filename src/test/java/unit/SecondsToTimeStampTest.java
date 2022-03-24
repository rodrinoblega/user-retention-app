package unit;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class SecondsToTimeStampTest {
    @Test
    public void seconds_to_timestamp() {
        long seconds = 1609459200;
        long miliSeconds = seconds*1000;

        DateFormat obj = new SimpleDateFormat("yyyyMMdd");
        obj.setTimeZone(TimeZone.getTimeZone("UTC-3"));
        // we create instance of the Date and pass milliseconds to the constructor
        Date date = new Date(miliSeconds);
        // now we format the res by using SimpleDateFormat
        System.out.println(obj.format(date));


    }
}
