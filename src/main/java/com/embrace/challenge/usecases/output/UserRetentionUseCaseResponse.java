package com.embrace.challenge.usecases.output;

import com.embrace.challenge.domain.entities.UserRetention;
import com.embrace.challenge.domain.entities.UserRetentionCollection;

import java.util.List;
import java.util.Objects;

public class UserRetentionUseCaseResponse {
    private final UserRetentionCollection userRetentionCollection;

    public UserRetentionUseCaseResponse(UserRetentionCollection userRetentionCollection) {
        this.userRetentionCollection = userRetentionCollection;
    }

    public List<UserRetention> obtainListOfUserRetention() {
        return  userRetentionCollection.getUserRetentions();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRetentionUseCaseResponse that = (UserRetentionUseCaseResponse) o;
        return Objects.equals(userRetentionCollection, that.userRetentionCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRetentionCollection);
    }
}
