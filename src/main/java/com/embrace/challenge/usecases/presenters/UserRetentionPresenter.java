package com.embrace.challenge.usecases.presenters;

import com.embrace.challenge.domain.entities.*;
import com.embrace.challenge.usecases.output.UserRetentionUseCaseResponse;

import java.util.List;
import java.util.Map;

public interface UserRetentionPresenter {
    void present(UserRetentionUseCaseResponse userRetentionUseCaseResponse, DateRange dateRange);
}
