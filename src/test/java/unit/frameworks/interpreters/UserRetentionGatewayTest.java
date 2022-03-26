package unit.frameworks;

import com.embrace.challenge.adapters.controllers.UserRetentionController;
import com.embrace.challenge.adapters.presenters.UserRetentionPresenterImpl;
import com.embrace.challenge.frameworks.UserRetentionGateway;
import com.embrace.challenge.frameworks.exceptions.CSVException;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.instrumentation.Log4jImpl;
import com.embrace.challenge.frameworks.interpreters.CSVInputInterpreter;
import com.embrace.challenge.frameworks.interpreters.DateRangeInterpreterImpl;
import com.embrace.challenge.frameworks.validations.InputValidation;
import com.embrace.challenge.usecases.UserRetentionUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserRetentionGatewayTest {

    private final Instrumentation instrumentation = new Instrumentation(new Log4jImpl());
    UserRetentionGateway userRetentionGateway = new UserRetentionGateway(
            instrumentation,
            new UserRetentionController(
                    new UserRetentionUseCase(),
                    new CSVInputInterpreter(instrumentation),
                    new UserRetentionPresenterImpl()
            ),
            new InputValidation(
                    new DateRangeInterpreterImpl(instrumentation)
            )
    );

    @Test
    public void test_exception_when_no_args() {
        Assertions.assertThrows(CSVException.class, () -> userRetentionGateway.run());
    }
}
