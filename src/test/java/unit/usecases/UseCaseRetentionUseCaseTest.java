package unit.usecases;

import com.embrace.challenge.domain.entities.ConnectionDate;
import com.embrace.challenge.domain.entities.Record;
import com.embrace.challenge.domain.entities.UserRetentionCollection;
import com.embrace.challenge.usecases.UserRetentionUseCase;
import com.embrace.challenge.usecases.output.UserRetentionUseCaseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UseCaseRetentionUseCaseTest {
    @Test
    public void proces_should_return_user_retention_collection_with_one_user_retention() {
        UserRetentionUseCase userRetentionUseCase = new UserRetentionUseCase();

        UserRetentionUseCaseResponse useCaseResponse = userRetentionUseCase.process(List.of(new Record("1234", new ConnectionDate(10, 10, 2020))));

        Assertions.assertEquals(buildUseCaseResponseExpected(), useCaseResponse);

    }

    private UserRetentionUseCaseResponse buildUseCaseResponseExpected() {
        UserRetentionCollection userRetentionCollection = new UserRetentionCollection();
        userRetentionCollection.registerARecord(
                new Record(
                        "1234",
                        new ConnectionDate(10, 10, 2020)
                )
        );

        return new UserRetentionUseCaseResponse(userRetentionCollection);
    }
}
