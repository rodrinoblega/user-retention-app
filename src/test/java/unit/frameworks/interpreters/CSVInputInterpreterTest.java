package unit.frameworks.interpreters;

import com.embrace.challenge.domain.entities.Record;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.instrumentation.Log4jImpl;
import com.embrace.challenge.frameworks.interpreters.CSVInputInterpreter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CSVInputInterpreterTest {

    @Test
    public void test_csv_interpreter_ok() {
        CSVInputInterpreter csvInterpreter = new CSVInputInterpreter(new Instrumentation(new Log4jImpl()));

        List<Record> records = csvInterpreter.run("src/test/resources/input.txt");

        Assertions.assertEquals(List.of(new Record("1234", "20211010")), records);
    }

    @Test
    public void test_csv_interpreter_no_ok() {
        CSVInputInterpreter csvInterpreter = new CSVInputInterpreter(new Instrumentation(new Log4jImpl()));

        Assertions.assertThrows(RuntimeException.class, () -> csvInterpreter.run("src/test/resources/not_valid_name.txt"));
    }
}
