package unit.frameworks.interpreters;

import com.embrace.challenge.frameworks.interpreters.DateHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateHelperTest extends DateHelper {

    @Test
    public void seconds_1641839745_should_return_10012022() {
        int day = DateHelper.secondsToDay("1641839745");

        Assertions.assertEquals(10, day);
    }

    @Test
    public void seconds_1609459200_should_return_01012021() {
        int day = DateHelper.secondsToDay("1609459200");

        Assertions.assertEquals(1, day);
    }
}
