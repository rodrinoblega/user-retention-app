package unit.usecases;

import com.embrace.challenge.domain.entities.Record;
import com.embrace.challenge.domain.entities.UserRetention;
import com.embrace.challenge.usecases.UserRetentionUseCase;
import com.embrace.challenge.usecases.output.UserRetentionUseCaseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UseCaseRetentionUseCaseTest {
    @Test
    public void use_case_retention_ok() {
        UserRetentionUseCase userRetentionUseCase = new UserRetentionUseCase();

        UserRetentionUseCaseResponse useCaseResponse = userRetentionUseCase.process(List.of(new Record("1234", "20221010")));

        Assertions.assertEquals(buildUseCaseResponseExpected(), useCaseResponse);

    }

    private UserRetentionUseCaseResponse buildUseCaseResponseExpected() {
        return new UserRetentionUseCaseResponse(
                List.of(
                        new UserRetention("1234",
                                "20221010",
                                "0"
                        )
                )
        );
    }
}
