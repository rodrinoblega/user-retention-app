package unit.usecases.output;

import com.embrace.challenge.domain.entities.DateRange;
import com.embrace.challenge.frameworks.exceptions.CSVException;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import com.embrace.challenge.frameworks.instrumentation.Log4jImpl;
import com.embrace.challenge.usecases.UserRetentionUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserRetentionUseCaseTest {

    private UserRetentionUseCase userRetentionPresenterV2 = new UserRetentionUseCase(new Instrumentation(new Log4jImpl()));

    @Test
    public void invalid_path_should_throw_exception() {
        Assertions.assertThrows(CSVException.class, () -> userRetentionPresenterV2.process("asdasd", new DateRange(1, 14)));
    }
}
