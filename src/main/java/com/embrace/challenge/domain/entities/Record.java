package com.embrace.challenge.domain.entities;

import java.util.Objects;

public class Record {

    private final String user;
    private final String date;

    public Record(String user, String date) {
        this.user = user;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(user, record.user) && Objects.equals(date, record.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, date);
    }
}
