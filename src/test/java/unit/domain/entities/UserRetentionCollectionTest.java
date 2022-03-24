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
    public void update_retention_not_existing_user() {
        UserRetentionCollection userRetentionCollection = new UserRetentionCollection();

        userRetentionCollection.updateRetention(new Record("1234", new ConnectionDate(10, 10, 2020)));

        Assertions.assertEquals(buildNotExistingUserExpected(), userRetentionCollection);
    }

    @Test
    public void update_retention_existing_user_not_consecutive() {
        UserRetentionCollection userRetentionCollection = new UserRetentionCollection(
                new ArrayList<>(List.of(new UserRetention("1234", new ConnectionDate(8, 10, 2020), 1, 1)))
        );

        userRetentionCollection.updateRetention(new Record("1234", new ConnectionDate(10, 10, 2020)));

        Assertions.assertEquals(buildExistingUserNotConsecutiveExpected(), userRetentionCollection);
    }

    @Test
    public void update_retention_existing_user_consecutive() {
        UserRetentionCollection userRetentionCollection = new UserRetentionCollection(
                new ArrayList<>(List.of(new UserRetention("1234", new ConnectionDate(9, 10, 2020), 1, 1)))
        );

        userRetentionCollection.updateRetention(new Record("1234", new ConnectionDate(10, 10, 2020)));

        Assertions.assertEquals(buildExistingUserConsecutiveExpected(), userRetentionCollection);
    }

    @Test
    public void update_retention_existing_user_consecutive_some_days_cut_with_consecutiveness() {
        UserRetentionCollection userRetentionCollection = new UserRetentionCollection(
                new ArrayList<>(List.of(
                        new UserRetention("1234", new ConnectionDate(11, 10, 2020), 3, 3))
                )
        );

        userRetentionCollection.updateRetention(new Record("1234", new ConnectionDate(15, 10, 2020)));

        Assertions.assertEquals(buildExistingUserConsecutiveCutConsecutivenessExpected(), userRetentionCollection);
    }

    @Test
    public void update_retention_existing_user_consecutive_some_days_change_consecutive_dates() {
        UserRetentionCollection userRetentionCollection = new UserRetentionCollection(
                new ArrayList<>(List.of(
                        new UserRetention("1234", new ConnectionDate(11, 10, 2020), 3, 3))
                )
        );

        userRetentionCollection.updateRetention(new Record("1234", new ConnectionDate(15, 10, 2020)));
        userRetentionCollection.updateRetention(new Record("1234", new ConnectionDate(16, 10, 2020)));
        userRetentionCollection.updateRetention(new Record("1234", new ConnectionDate(17, 10, 2020)));
        userRetentionCollection.updateRetention(new Record("1234", new ConnectionDate(18, 10, 2020)));

        Assertions.assertEquals(buildExistingUserConsecutiveSomeDaysChangeConsecutiveDates(), userRetentionCollection);
    }

    private UserRetentionCollection buildNotExistingUserExpected() {
        return new UserRetentionCollection(List.of(new UserRetention("1234", new ConnectionDate(10, 10, 2020), 1, 1)));
    }

    private UserRetentionCollection buildExistingUserNotConsecutiveExpected() {
        return new UserRetentionCollection(List.of(new UserRetention("1234", new ConnectionDate(10, 10, 2020), 1, 1)));
    }

    private UserRetentionCollection buildExistingUserConsecutiveExpected() {
        return new UserRetentionCollection(List.of(new UserRetention("1234", new ConnectionDate(10, 10, 2020), 2, 2)));
    }

    private UserRetentionCollection buildExistingUserConsecutiveCutConsecutivenessExpected() {
        return new UserRetentionCollection(List.of(
                new UserRetention("1234", new ConnectionDate(15, 10, 2020), 3, 1))
        );
    }

    private UserRetentionCollection buildExistingUserConsecutiveSomeDaysChangeConsecutiveDates() {
        return new UserRetentionCollection(List.of(
                new UserRetention("1234", new ConnectionDate(18, 10, 2020), 4, 4))
        );
    }
}
