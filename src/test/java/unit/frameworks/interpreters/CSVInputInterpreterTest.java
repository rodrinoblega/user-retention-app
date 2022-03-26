package unit.frameworks.interpreters;

import com.embrace.challenge.domain.entities.ConnectionDate;
import com.embrace.challenge.domain.entities.Record;
import com.embrace.challenge.frameworks.exceptions.CSVException;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.instrumentation.Log4jImpl;
import com.embrace.challenge.frameworks.interpreters.CSVInputInterpreter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CSVInputInterpreterTest {

    @Test
    public void process_valid_csv_should_return_record_list() {
        CSVInputInterpreter csvInterpreter = new CSVInputInterpreter(new Instrumentation(new Log4jImpl()));

        List<Record> records = csvInterpreter.run("src/test/resources/input_csv.txt");

        Assertions.assertEquals(List.of(new Record("1234", new ConnectionDate(10, 10, 2021))), records);
    }

    @Test
    public void process_invalid_csv_should_throw_exception() {
        CSVInputInterpreter csvInterpreter = new CSVInputInterpreter(new Instrumentation(new Log4jImpl()));

        Assertions.assertThrows(CSVException.class, () -> csvInterpreter.run("src/test/resources/not_valid_name.txt"));
    }
}
