package unit.frameworks.interpreters;

import com.embrace.challenge.domain.entities.ConnectionDate;
import com.embrace.challenge.frameworks.interpreters.SecondsToDateInterpreter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SecondsToDateInterpreterTest extends SecondsToDateInterpreter {

    @Test
    public void seconds_1641839745_return_10012022() {
        ConnectionDate connectionDate = SecondsToDateInterpreter.process("1641839745");

        Assertions.assertEquals(new ConnectionDate(10, 1, 2022), connectionDate);
    }

    @Test
    public void seconds_1609459200_return_01012021() {
        ConnectionDate connectionDate = SecondsToDateInterpreter.process("1609459200");

        Assertions.assertEquals(new ConnectionDate(1, 1, 2021), connectionDate);
    }
}
