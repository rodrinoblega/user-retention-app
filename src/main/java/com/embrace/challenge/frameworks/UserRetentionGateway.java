package com.embrace.challenge.frameworks;

import com.embrace.challenge.adapters.controllers.UserRetentionController;
import com.embrace.challenge.adapters.interpreters.DateRangeInterpreter;
import com.embrace.challenge.domain.entities.DateRange;
import com.embrace.challenge.frameworks.exceptions.CSVException;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.Optional;

public class UserRetentionGateway implements CommandLineRunner {

    private final Instrumentation instrumentation;
    private final UserRetentionController userRetentionController;
    private final DateRangeInterpreter dateRangeInterpreter;

    public UserRetentionGateway(Instrumentation instrumentation, UserRetentionController userRetentionController, DateRangeInterpreter dateRangeInterpreter) {
        this.instrumentation = instrumentation;
        this.userRetentionController = userRetentionController;
        this.dateRangeInterpreter = dateRangeInterpreter;
    }

    public void run(String... ars) {
        instrumentation.logMessage("The app was started");
        Optional<String> csvPath = Arrays.stream(ars).findFirst();
        String path = csvPath.orElseThrow(
                () -> new CSVException("Please provide a csv path")
        );

        DateRange dateRange = dateRangeInterpreter.obtainsDateRangeIfApply(ars);
        userRetentionController.process(path, dateRange);

    }
}
