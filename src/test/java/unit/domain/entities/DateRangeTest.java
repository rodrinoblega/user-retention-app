package unit.domain.entities;

import com.embrace.challenge.domain.entities.DateRange;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DateRangeTest {
    @Test
    public void simpleEqualsContract() {
        EqualsVerifier.simple().forClass(DateRange.class).verify();
    }
}
