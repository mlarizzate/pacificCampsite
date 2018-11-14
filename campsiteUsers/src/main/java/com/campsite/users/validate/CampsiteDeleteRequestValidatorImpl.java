package com.campsite.users.validate;

import com.campsite.users.model.Messages;
import com.campsite.users.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CampsiteDeleteRequestValidatorImpl implements CampsiteRequestValidator {
    @Override
    public List<String> validate(User user) {
        List<String> errors = new ArrayList<>();

        if(user.getId() == 0) errors.add(Messages.DELETE_USER_EXPECTED_ID_NOT_FOUND);

        return errors;
    }
}
