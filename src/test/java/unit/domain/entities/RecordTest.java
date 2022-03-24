package unit.domain.entities;

import com.embrace.challenge.domain.entities.Record;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class RecordTest {
    @Test
    public void simpleEqualsContract() {
        EqualsVerifier.simple().forClass(Record.class).verify();
    }
}
