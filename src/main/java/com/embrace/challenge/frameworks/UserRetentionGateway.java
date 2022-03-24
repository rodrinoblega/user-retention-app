package com.embrace.challenge.frameworks;

import com.embrace.challenge.adapters.controllers.UserRetentionController;
import com.embrace.challenge.frameworks.instrumentation.Instrumentation;
import org.springframework.boot.CommandLineRunner;

public class UserRetentionGateway implements CommandLineRunner {

    private final Instrumentation instrumentation;
    private final UserRetentionController userRetentionController;

    public UserRetentionGateway(Instrumentation instrumentation, UserRetentionController userRetentionController) {
        this.instrumentation = instrumentation;
        this.userRetentionController = userRetentionController;
    }

    public void run(String... ars) {
        instrumentation.logMessage("The app was started");
    }
}
