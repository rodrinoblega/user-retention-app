package com.embrace.challenge.usecases;

import com.embrace.challenge.domain.entities.Record;
import com.embrace.challenge.domain.entities.UserRetention;
import com.embrace.challenge.usecases.output.UserRetentionUseCaseResponse;

import java.util.List;

public class UserRetentionUseCase {
    public UserRetentionUseCaseResponse process(List<Record> records) {
        return new UserRetentionUseCaseResponse(List.of(new UserRetention("1234", "20221010", "0")));
    }
}
