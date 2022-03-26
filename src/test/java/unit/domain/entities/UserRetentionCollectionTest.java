package unit.domain.entities;

import com.embrace.challenge.domain.entities.ConnectionDate;
import com.embrace.challenge.domain.entities.Record;
import com.embrace.challenge.domain.entities.UserRetention;
import com.embrace.challenge.domain.entities.UserRetentionCollection;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserRetentionCollectionTest {

    @Test
    public void simpleEqualsContract() {
        EqualsVerifier.simple().forClass(UserRetentionCollection.class).verify();
    }

    @Test
    public void register_record_of_a_non_existing_user_should_return_a_new_user_retention_record() {
        UserRetentionCollection userRetentionCollection = new UserRetentionCollection();

        userRetentionCollection.registerARecord(new Record("1234", new ConnectionDate(10, 10, 2020)));

        Assertions.assertEquals(buildNotExistingUserExpected(), userRetentionCollection);
    }

    @Test
    public void register_record_of_a_existing_user_not_consecutive_should_return_a_new_user_retention_record() {
        UserRetentionCollection userRetentionCollection = new UserRetentionCollection(
                new ArrayList<>(List.of(
                        new UserRetention(
                                "1234",
                                1,
                                new ConnectionDate(8, 10, 2020),
                                new ConnectionDate(8, 10, 2020)
                        )
                )
            )
        );

        userRetentionCollection.registerARecord(new Record("1234", new ConnectionDate(10, 10, 2020)));

        Assertions.assertEquals(buildExistingUserNotConsecutiveExpected(), userRetentionCollection);
    }

    @Test
    public void register_record_of_a_existing_user_consecutive_should_return_an_update_of_user_rention() {
        UserRetentionCollection userRetentionCollection = new UserRetentionCollection(
                new ArrayList<>(List.of(
                        new UserRetention(
                                "1234",
                                1,
                                new ConnectionDate(9, 10, 2020),
                                new ConnectionDate(9, 10, 2020)
                        )
                )
            )
        );

        userRetentionCollection.registerARecord(new Record("1234", new ConnectionDate(10, 10, 2020)));

        Assertions.assertEquals(buildExistingUserConsecutiveExpected(), userRetentionCollection);
    }

    @Test
    public void register_record_of_a_existing_user_not_consecutive_should_return_a_new_user_rentention_without_updating_that_last_record() {
        UserRetentionCollection userRetentionCollection = new UserRetentionCollection(
                new ArrayList<>(List.of(
                        new UserRetention(
                                "1234",
                                3,
                                new ConnectionDate(9, 10, 2020),
                                new ConnectionDate(11, 10, 2020)
                        )
                )
            )
        );

        userRetentionCollection.registerARecord(new Record("1234", new ConnectionDate(15, 10, 2020)));

        Assertions.assertEquals(buildExistingUserConsecutiveCutConsecutivenessExpected(), userRetentionCollection);
    }

    private UserRetentionCollection buildNotExistingUserExpected() {
        return new UserRetentionCollection(
                List.of(
                        new UserRetention(
                                "1234",
                                1,
                                new ConnectionDate(10, 10, 2020),
                                new ConnectionDate(10, 10, 2020)
                        )
                )
        );
    }

    private UserRetentionCollection buildExistingUserNotConsecutiveExpected() {
        return new UserRetentionCollection(
                List.of(
                        new UserRetention(
                                "1234",
                                1,
                                new ConnectionDate(8, 10, 2020),
                                new ConnectionDate(8, 10, 2020)
                        ),
                        new UserRetention(
                                "1234",
                                1,
                                new ConnectionDate(10, 10, 2020),
                                new ConnectionDate(10, 10, 2020)
                        )
                )
        );
    }

    private UserRetentionCollection buildExistingUserConsecutiveExpected() {
        return new UserRetentionCollection(
                List.of(
                        new UserRetention(
                                "1234",
                                2,
                                new ConnectionDate(9, 10, 2020),
                                new ConnectionDate(10, 10, 2020)
                        )
                )
        );
    }

    private UserRetentionCollection buildExistingUserConsecutiveCutConsecutivenessExpected() {
        return new UserRetentionCollection(
                List.of(
                        new UserRetention(
                                "1234",
                                3,
                                new ConnectionDate(9, 10, 2020),
                                new ConnectionDate(11, 10, 2020)
                        ),
                        new UserRetention(
                                "1234",
                                1,
                                new ConnectionDate(15, 10, 2020),
                                new ConnectionDate(15, 10, 2020)
                        )
                )
        );
    }
}
