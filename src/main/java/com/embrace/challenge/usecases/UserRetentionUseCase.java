package com.embrace.challenge.usecases;

import com.embrace.challenge.domain.entities.Record;
import com.embrace.challenge.domain.entities.UserRetentionCollection;
import com.embrace.challenge.usecases.output.UserRetentionUseCaseResponse;
import java.util.List;

public class UserRetentionUseCase {
    public UserRetentionUseCaseResponse process(List<Record> records) {
        UserRetentionCollection userRetentionCollection = new UserRetentionCollection();

        records.forEach(userRetentionCollection::registerARecord);

        return new UserRetentionUseCaseResponse(userRetentionCollection);
    }
}
