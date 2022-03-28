package unit.domain.entities;

import com.embrace.challenge.domain.entities.DateRange;
import com.embrace.challenge.domain.entities.UserAndLogDate;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class UserAndLogDateTest {
    @Test
    public void simpleEqualsContract() {
        EqualsVerifier.simple().forClass(UserAndLogDate.class).verify();
    }
}
