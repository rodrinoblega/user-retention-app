package unit.frameworks.validations;

import com.embrace.challenge.frameworks.exceptions.CSVException;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.instrumentation.Log4jImpl;
import com.embrace.challenge.frameworks.interpreters.DateRangeInterpreterImpl;
import com.embrace.challenge.frameworks.validations.InputValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputValidationTest {
    
    private InputValidation inputValidation = new InputValidation(new DateRangeInterpreterImpl(new Instrumentation(new Log4jImpl())));
    
    @Test
    public void invalid_path_should_throw_expcetion() {
        String[] ars = new String[0];

        Assertions.assertThrows(CSVException.class, () -> inputValidation.validateAndReturnInput(ars));
    }
}
