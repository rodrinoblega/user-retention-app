package com.embrace.challenge.usecases.presenters;

import com.embrace.challenge.domain.entities.*;

import java.util.List;
import java.util.Map;

public interface UserRetentionPresenterV2 {
    void present(List<Day> dayInformations, DateRange dateRange);
}
