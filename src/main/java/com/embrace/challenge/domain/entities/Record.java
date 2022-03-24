package com.embrace.challenge.domain.entities;

import java.util.Objects;

public class Record {

    private final String user;
    private final ConnectionDate connectionDate;

    public Record(String user, ConnectionDate connectionDate) {
        this.user = user;
        this.connectionDate = connectionDate;
    }

    public String getUser() {
        return user;
    }

    public ConnectionDate getDate() {
        return connectionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(user, record.user) && Objects.equals(connectionDate, record.connectionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, connectionDate);
    }
}
