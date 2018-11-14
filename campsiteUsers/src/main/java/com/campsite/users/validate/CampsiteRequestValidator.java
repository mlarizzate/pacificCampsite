package com.campsite.users.validate;

import com.campsite.users.model.User;

import java.util.List;

public interface CampsiteRequestValidator {
    List<String> validate(User user);
}
