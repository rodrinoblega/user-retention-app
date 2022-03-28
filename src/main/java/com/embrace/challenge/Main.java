package com.embrace.challenge;

import com.embrace.challenge.adapters.controllers.UserRetentionController;
import com.embrace.challenge.configuration.ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();

        UserRetentionController userRetentionController = applicationConfiguration.obtainUserRetentionController();

        userRetentionController.process(args);
    }
}
