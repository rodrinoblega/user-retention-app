package unit.frameworks.interpreters;

import com.embrace.challenge.domain.entities.Record;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.instrumentation.Log4jImpl;
import com.embrace.challenge.frameworks.interpreters.CSVInterpreter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CSVInterpreterTest {

    @Test
    public void test_csv_interpreter_ok() {
        CSVInterpreter csvInterpreter = new CSVInterpreter(new Instrumentation(new Log4jImpl()));

        List<Record> records = csvInterpreter.interpret("src/test/resources/input.txt");

        Assertions.assertEquals(List.of(new Record("1234", "20211010")), records);
    }

    @Test
    public void test_csv_interpreter_no_ok() {
        CSVInterpreter csvInterpreter = new CSVInterpreter(new Instrumentation(new Log4jImpl()));

        Assertions.assertThrows(RuntimeException.class, () -> csvInterpreter.interpret("src/test/resources/not_valid_name.txt"));
    }
}
