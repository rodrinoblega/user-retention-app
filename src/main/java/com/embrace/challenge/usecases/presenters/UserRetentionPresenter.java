package com.embrace.challenge.usecases.presenters;

import com.embrace.challenge.domain.entities.*;
import com.embrace.challenge.usecases.output.UserRetentionUseCaseResponse;

public interface UserRetentionPresenter {
    void present(UserRetentionUseCaseResponse userRetentionUseCaseResponse, DateRange dateRange);
}
