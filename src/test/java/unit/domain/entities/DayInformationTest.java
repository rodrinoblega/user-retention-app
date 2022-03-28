package unit.domain.entities;

import com.embrace.challenge.domain.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayInformationTest {

    private Map<UserAndLogDate, Connection> registeredLogs = new HashMap<>();

    @Test
    public void test_exist_logs_for_user_in_actual_day() {
        List<Day> daysInformation = prepareContext();

        updateWithDay1(daysInformation);
        updateWithSameUserDay1(daysInformation);

        Assertions.assertEquals(List.of(1,0,0,0,0,0,0,0,0,0,0,0,0,0), daysInformation.get(1).getInitialStreakDays());
    }

    private void updateWithSameUserDay1(List<Day> daysInformation) {
        daysInformation.get(1).recordActivityAndUpdateStreakCounter(new UserAndLogDate("1", 2), daysInformation, registeredLogs);
    }

    private void updateWithDay1(List<Day> daysInformation) {
        updateWithSameUserDay1(daysInformation);
    }

    private List<Day> prepareContext() {
        return List.of(
                new FirstDayInformation(14),
                new DayInformation(14),
                new DayInformation(14)
        );
    }

}
