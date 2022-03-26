package com.embrace.challenge.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserRetentionCollection {
    private final List<UserRetention> userRetentions;

    public UserRetentionCollection() {
        this.userRetentions = new ArrayList<>();
    }

    public UserRetentionCollection(List<UserRetention> userRetentions) {
        this.userRetentions = userRetentions;
    }

    public void registerARecord(Record record) {
        Optional<UserRetention> userRetention = findLastUserRetentionIfExists(record);

        userRetention.ifPresentOrElse(
                userRetentionFounded -> userRetentionFounded.addOrUpdateIfApplies(userRetentions, record),
                () -> userRetentions.add(new UserRetention(record))
        );
    }

    private Optional<UserRetention> findLastUserRetentionIfExists(Record record) {
        return userRetentions.stream()
                .filter(userRetention -> userRetention.getUserId().equals(record.getUser()))
                .reduce((first, second) -> second);
    }

    public List<UserRetention> getUserRetentions() {
        return userRetentions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRetentionCollection that = (UserRetentionCollection) o;
        return Objects.equals(userRetentions, that.userRetentions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRetentions);
    }
}
