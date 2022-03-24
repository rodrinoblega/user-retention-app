package unit.usecases.output;

import com.embrace.challenge.usecases.output.UserRetentionUseCaseResponse;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class UserRetentionUseCaseResponseTest {
    @Test
    public void simpleEqualsContract() {
        EqualsVerifier.simple().forClass(UserRetentionUseCaseResponse.class).verify();
    }
}
