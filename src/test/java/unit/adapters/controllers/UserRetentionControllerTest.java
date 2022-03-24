package unit.adapters.controllers;

import com.embrace.challenge.adapters.controllers.UserRetentionController;
import com.embrace.challenge.domain.entities.ConnectionDate;
import com.embrace.challenge.domain.entities.Record;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.instrumentation.Log4jImpl;
import com.embrace.challenge.frameworks.interpreters.CSVInputInterpreter;
import com.embrace.challenge.usecases.UserRetentionUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserRetentionControllerTest {

    @Test
    public void process_valid_input_should_return_a_record() {
        UserRetentionController userRetentionController = new UserRetentionController(new UserRetentionUseCase(), new CSVInputInterpreter(new Instrumentation(new Log4jImpl())));
        Record record = userRetentionController.process("src/test/resources/input.txt");

        Assertions.assertEquals(record, new Record("123", new ConnectionDate(10, 10 ,2021)));
    }
}
