package com.embrace.challenge.usecases.presenters;

import com.embrace.challenge.domain.entities.*;

import java.util.List;
import java.util.Map;

public interface UserRetentionPresenter {
    void present(List<Day> dayInformations, DateRange dateRange);
}
