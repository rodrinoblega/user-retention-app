package com.embrace.challenge.domain.entities;

import java.util.Objects;

public class UserRetention {
    private final String id;
    private final String lastConnection;
    private final String consecutiveDaysConnected;

    public UserRetention(String id, String lastConnection, String consecutiveDaysConnected) {
        this.id = id;
        this.lastConnection = lastConnection;
        this.consecutiveDaysConnected = consecutiveDaysConnected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRetention that = (UserRetention) o;
        return Objects.equals(id, that.id) && Objects.equals(lastConnection, that.lastConnection) && Objects.equals(consecutiveDaysConnected, that.consecutiveDaysConnected);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastConnection, consecutiveDaysConnected);
    }
}
