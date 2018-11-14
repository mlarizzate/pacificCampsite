package com.campsite.users.strategy;

import com.campsite.users.exception.CampsiteException;
import com.campsite.users.model.User;

public interface UserActionStrategy {
    User action(User user) throws CampsiteException;


}
