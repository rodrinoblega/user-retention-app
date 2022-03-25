package unit.frameworks.interpreters;

import com.embrace.challenge.domain.entities.ConnectionDate;
import com.embrace.challenge.frameworks.interpreters.DateHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateHelperTest extends DateHelper {

    @Test
    public void seconds_1641839745_return_10012022() {
        ConnectionDate connectionDate = DateHelper.process("1641839745");

        Assertions.assertEquals(new ConnectionDate(10, 1, 2022), connectionDate);
    }

    @Test
    public void seconds_1609459200_return_01012021() {
        ConnectionDate connectionDate = DateHelper.process("1609459200");

        Assertions.assertEquals(new ConnectionDate(1, 1, 2021), connectionDate);
    }

    @Test
    public void is_between_range() {
        ConnectionDate initialConnectionDate = new ConnectionDate(1, 1, 2021);
        ConnectionDate finalConnectionDate = new ConnectionDate(14, 1, 2021);
        ConnectionDate actualConnectionDate = new ConnectionDate(4, 1, 2021);

        Assertions.assertTrue(DateHelper.isBetweenDates(actualConnectionDate, initialConnectionDate, finalConnectionDate));
    }

    @Test
    public void is_not_between_range() {
        ConnectionDate initialConnectionDate = new ConnectionDate(1, 1, 2021);
        ConnectionDate finalConnectionDate = new ConnectionDate(14, 1, 2021);
        ConnectionDate actualConnectionDate = new ConnectionDate(18, 1, 2021);

        Assertions.assertFalse(DateHelper.isBetweenDates(actualConnectionDate, initialConnectionDate, finalConnectionDate));
    }

    @Test
    public void is_not_between_range_border_case() {
        ConnectionDate initialConnectionDate = new ConnectionDate(1, 1, 2021);
        ConnectionDate finalConnectionDate = new ConnectionDate(14, 1, 2021);
        ConnectionDate actualConnectionDate = new ConnectionDate(14, 1, 2021);

        Assertions.assertTrue(DateHelper.isBetweenDates(actualConnectionDate, initialConnectionDate, finalConnectionDate));
    }

    @Test
    public void is_less_than_ok() {
        ConnectionDate actualConnectionDate = new ConnectionDate(1, 1, 2021);
        ConnectionDate finalConnectionDate = new ConnectionDate(14, 1, 2021);

        Assertions.assertTrue(DateHelper.isLessDateThan(actualConnectionDate, finalConnectionDate));
    }

    @Test
    public void is_less_than_not_ok() {
        ConnectionDate actualConnectionDate = new ConnectionDate(23, 1, 2021);
        ConnectionDate finalConnectionDate = new ConnectionDate(14, 1, 2021);

        Assertions.assertFalse(DateHelper.isLessDateThan(actualConnectionDate, finalConnectionDate));
    }

    @Test
    public void is_less_than_ok_border() {
        ConnectionDate actualConnectionDate = new ConnectionDate(14, 1, 2021);
        ConnectionDate finalConnectionDate = new ConnectionDate(14, 1, 2021);

        Assertions.assertTrue(DateHelper.isLessDateThan(actualConnectionDate, finalConnectionDate));
    }
}
