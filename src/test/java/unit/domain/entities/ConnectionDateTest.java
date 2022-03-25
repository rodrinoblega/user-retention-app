package unit.domain.entities;

import com.embrace.challenge.domain.entities.ConnectionDate;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectionDateTest {

    @Test
    public void simpleEqualsContract() {
        EqualsVerifier.simple().forClass(ConnectionDate.class).verify();
    }

    @Test
    public void test_isOneDayAfterLastConnection_different_year() {
        ConnectionDate actualConnectionDate = new ConnectionDate(11, 10, 2021);

        Assertions.assertFalse(actualConnectionDate.isOneDayAfterLastConnection(new ConnectionDate(10, 10, 2022)));
    }

    @Test
    public void test_isOneDayAfterLastConnection_different_month() {
        ConnectionDate actualConnectionDate = new ConnectionDate(11, 12, 2021);

        Assertions.assertFalse(actualConnectionDate.isOneDayAfterLastConnection(new ConnectionDate(10, 11, 2021)));
    }

    @Test
    public void test_add_one_day() {
        ConnectionDate onnectionDateToAddDay = new ConnectionDate(11, 12, 2021);
        ConnectionDate actualConnectionDate = onnectionDateToAddDay.addOneDay();

        Assertions.assertEquals(new ConnectionDate(12, 12, 2021), actualConnectionDate);
    }
}
