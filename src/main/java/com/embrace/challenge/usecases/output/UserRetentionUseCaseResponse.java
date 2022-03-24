package com.embrace.challenge.usecases.output;

import com.embrace.challenge.domain.entities.UserRetention;

import java.util.List;
import java.util.Objects;

public class UserRetentionUseCaseResponse {
    List<UserRetention> userRetentions;

    public UserRetentionUseCaseResponse(List<UserRetention> userRetentions) {
        this.userRetentions = userRetentions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRetentionUseCaseResponse that = (UserRetentionUseCaseResponse) o;
        return Objects.equals(userRetentions, that.userRetentions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRetentions);
    }
}
