package unit.frameworks.interpreters;

import com.embrace.challenge.domain.entities.DateRange;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.instrumentation.Log4jImpl;
import com.embrace.challenge.frameworks.interpreters.DateRangeInterpreterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateRangeInterpreterImplTest {
    private final DateRangeInterpreterImpl dateRangeInterpreter = new DateRangeInterpreterImpl(new Instrumentation(new Log4jImpl()));

    @Test
    public void ars_with_no_range_date_should_return_default_range_date() {
        DateRange dateRange = dateRangeInterpreter.obtainsDateRangeIfApply(new String[] {"path"});

        Assertions.assertEquals(new DateRange(1, 14), dateRange);
    }

    @Test
    public void ars_with_range_date_should_return_valid_range_date() {
        DateRange dateRange = dateRangeInterpreter.obtainsDateRangeIfApply(new String[] {"path", "01102021", "10102021"});

        Assertions.assertEquals(new DateRange(1,14), dateRange);
    }

    @Test
    public void ars_with_no_final_range_date_should_return_default_range_date() {
        DateRange dateRange = dateRangeInterpreter.obtainsDateRangeIfApply(new String[] {"path", "01102021"});

        Assertions.assertEquals(new DateRange(1, 14), dateRange);
    }

    @Test
    public void ars_with_invalid_range_date_should_return_default_range_date() {
        DateRange dateRange = dateRangeInterpreter.obtainsDateRangeIfApply(new String[] {"path", "211111", "1111111"});

        Assertions.assertEquals(new DateRange(1, 14), dateRange);
    }

    @Test
    public void ars_with_letters_as_range_date_should_return_default_range_date() {
        DateRange dateRange = dateRangeInterpreter.obtainsDateRangeIfApply(new String[] {"path", "asd", "asd"});

        Assertions.assertEquals(new DateRange(1, 14), dateRange);
    }

    @Test
    public void ars_with_first_range_date_not_first_should_return_default_range_date() {
        DateRange dateRange = dateRangeInterpreter.obtainsDateRangeIfApply(new String[] {"path", "02", "03"});

        Assertions.assertEquals(new DateRange(1, 14), dateRange);
    }

    @Test
    public void ars_with_range_than_default_should_return_that_range() {
        DateRange dateRange = dateRangeInterpreter.obtainsDateRangeIfApply(new String[] {"path", "01", "05"});

        Assertions.assertEquals(new DateRange(1, 5), dateRange);
    }
}