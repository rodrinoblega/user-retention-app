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

    public void updateRetention(Record record) {
        Optional<UserRetention> userRetention = getUserRetentionIfExists();

        userRetention.ifPresentOrElse(
                userRetentionFounded -> userRetentionFounded.update(record),
                () -> userRetentions.add(new UserRetention(record))
        );
    }

    private Optional<UserRetention> getUserRetentionIfExists() {
        return userRetentions.stream()
                .filter(userRetention -> Boolean.parseBoolean(userRetention.getId()))
                .findFirst();
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
