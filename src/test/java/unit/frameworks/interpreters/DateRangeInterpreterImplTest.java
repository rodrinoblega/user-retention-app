package unit.frameworks.interpreters;

import com.embrace.challenge.domain.entities.ConnectionDate;
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

        Assertions.assertEquals(new DateRange(new ConnectionDate(1,1,2021), new ConnectionDate(14,1,2021)), dateRange);
    }

    @Test
    public void ars_with_range_date_should_return_valid_range_date() {
        DateRange dateRange = dateRangeInterpreter.obtainsDateRangeIfApply(new String[] {"path", "01102021", "10102021"});

        Assertions.assertEquals(new DateRange(new ConnectionDate(1,10,2021), new ConnectionDate(10,10,2021)), dateRange);
    }

    @Test
    public void ars_with_no_final_range_date_should_return_default_range_date() {
        DateRange dateRange = dateRangeInterpreter.obtainsDateRangeIfApply(new String[] {"path", "01102021"});

        Assertions.assertEquals(new DateRange(new ConnectionDate(1,1,2021), new ConnectionDate(14,1,2021)), dateRange);
    }
}
