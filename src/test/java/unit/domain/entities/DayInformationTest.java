package unit.domain.entities;

import com.embrace.challenge.domain.entities.Day;
import com.embrace.challenge.domain.entities.DayInformation;
import com.embrace.challenge.domain.entities.FirstDayInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DayInformationTest {

    @Test
    public void test_exist_logs_for_user_in_actual_day() {
        List<Day> daysInformation = prepareContext();

        updateWithDay1(daysInformation);
        updateWithSameUserDay1(daysInformation);

        Assertions.assertEquals(List.of(1,0,0,0,0,0,0,0,0,0,0,0,0,0), daysInformation.get(1).getInitialStreakDays());
    }

    private void updateWithSameUserDay1(List<Day> daysInformation) {
        daysInformation.get(1).recordLogAndUpdateStreakCounter("1", 2, daysInformation);
    }

    private void updateWithDay1(List<Day> daysInformation) {
        updateWithSameUserDay1(daysInformation);
    }

    private List<Day> prepareContext() {
        return List.of(
                new FirstDayInformation(),
                new DayInformation(),
                new DayInformation()
        );
    }

}
