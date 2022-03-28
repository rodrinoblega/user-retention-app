package com.embrace.challenge.usecases.output;

import com.embrace.challenge.domain.entities.Day;

import java.util.List;

public class UserRetentionUseCaseResponse {
    private final List<Day> daysInformation;

    public UserRetentionUseCaseResponse(List<Day> daysInformations) {
        this.daysInformation = daysInformations;
    }

    public List<Day> getDaysInformation() {
        return daysInformation;
    }
}
