package unit.domain.entities;

import com.embrace.challenge.domain.entities.UserRetention;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class UserRetentionTest {
    @Test
    public void simpleEqualsContract() {
        EqualsVerifier.simple().forClass(UserRetention.class).verify();
    }
}
