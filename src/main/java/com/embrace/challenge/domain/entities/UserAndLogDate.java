package com.embrace.challenge.domain.entities;

import java.util.Objects;

public class UserAndLogDate {
    private String user;
    private int day;

    public UserAndLogDate(String user, int day) {
        this.user = user;
        this.day = day;
    }

    public String getUser() {
        return user;
    }

    public int getDay() {
        return day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAndLogDate that = (UserAndLogDate) o;
        return day == that.day && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, day);
    }
}
