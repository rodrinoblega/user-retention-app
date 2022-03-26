package unit.frameworks.interpreters;

import com.embrace.challenge.domain.entities.ConnectionDate;
import com.embrace.challenge.frameworks.exceptions.DateHelperException;
import com.embrace.challenge.frameworks.interpreters.DateHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateHelperTest extends DateHelper {

    @Test
    public void seconds_1641839745_return_10012022() {
        ConnectionDate connectionDate = DateHelper.secondsToConnectionDate("1641839745");

        Assertions.assertEquals(new ConnectionDate(10, 1, 2022), connectionDate);
    }

    @Test
    public void seconds_1609459200_return_01012021() {
        ConnectionDate connectionDate = DateHelper.secondsToConnectionDate("1609459200");

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
    public void is_not_between_range_border_final_case() {
        ConnectionDate initialConnectionDate = new ConnectionDate(1, 1, 2021);
        ConnectionDate finalConnectionDate = new ConnectionDate(14, 1, 2021);
        ConnectionDate actualConnectionDate = new ConnectionDate(14, 1, 2021);

        Assertions.assertTrue(DateHelper.isBetweenDates(actualConnectionDate, initialConnectionDate, finalConnectionDate));
    }

    @Test
    public void is_not_between_range_border_initial_case() {
        ConnectionDate initialConnectionDate = new ConnectionDate(1, 1, 2021);
        ConnectionDate finalConnectionDate = new ConnectionDate(14, 1, 2021);
        ConnectionDate actualConnectionDate = new ConnectionDate(1, 1, 2021);

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

    @Test
    public void is_less_than_throw_exception() {
        ConnectionDate actualConnectionDate = new ConnectionDate(0,0, 0);
        ConnectionDate finalConnectionDate = new ConnectionDate(0,0, 0);

        Assertions.assertThrows(DateHelperException.class, () -> DateHelper.isLessDateThan(actualConnectionDate, finalConnectionDate));
    }

    @Test
    public void add_one_day_throw_exception() {
        ConnectionDate actualConnectionDate = new ConnectionDate(0,0, 0);

        Assertions.assertThrows(DateHelperException.class, () -> DateHelper.addOneDay(actualConnectionDate));
    }

    @Test
    public void is_between_dates_throw_exception() {
        ConnectionDate actualConnectionDate = new ConnectionDate(0,0, 0);
        ConnectionDate finalConnectionDate = new ConnectionDate(0,0, 0);

        Assertions.assertThrows(DateHelperException.class, () -> DateHelper.isBetweenDates(actualConnectionDate, actualConnectionDate, finalConnectionDate));
    }

    @Test
    public void string_to_date_thrown_exception() {
        String date = "asdasd";

        Assertions.assertThrows(DateHelperException.class, () -> DateHelper.stringToDate(date));
    }

    @Test void are_in_the_same_month_first_day_one() {
        ConnectionDate initialConnectionDate = new ConnectionDate(1, 10, 2021);
        ConnectionDate finalConnectionDate = new ConnectionDate(20, 9, 2021);

        Assertions.assertFalse(DateHelper.areInTheSameMonth(initialConnectionDate, finalConnectionDate));
    }

    @Test void are_in_the_same_month_initial_date_not_less_final_date() {
        ConnectionDate initialConnectionDate = new ConnectionDate(2, 10, 2021);
        ConnectionDate finalConnectionDate = new ConnectionDate(20, 9, 2021);

        Assertions.assertFalse(DateHelper.areInTheSameMonth(initialConnectionDate, finalConnectionDate));
    }

    @Test
    void are_in_the_same_month_initial_date_less_final_date() {
        ConnectionDate initialConnectionDate = new ConnectionDate(1, 10, 2021);
        ConnectionDate finalConnectionDate = new ConnectionDate(3, 10, 2021);

        Assertions.assertTrue(DateHelper.areInTheSameMonth(initialConnectionDate, finalConnectionDate));
    }

    @Test
    void are_in_the_same_month_not_in_the_same_year() {
        ConnectionDate initialConnectionDate = new ConnectionDate(1, 10, 2021);
        ConnectionDate finalConnectionDate = new ConnectionDate(3, 10, 2022);

        Assertions.assertFalse(DateHelper.areInTheSameMonth(initialConnectionDate, finalConnectionDate));
    }

    @Test
    void are_in_the_same_month_not_in_the_same_month() {
        ConnectionDate initialConnectionDate = new ConnectionDate(1, 10, 2021);
        ConnectionDate finalConnectionDate = new ConnectionDate(3, 11, 2021);

        Assertions.assertFalse(DateHelper.areInTheSameMonth(initialConnectionDate, finalConnectionDate));
    }
}
