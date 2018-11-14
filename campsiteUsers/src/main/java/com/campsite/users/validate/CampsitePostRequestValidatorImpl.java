package com.campsite.users.validate;

import com.campsite.users.model.Messages;
import com.campsite.users.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CampsitePostRequestValidatorImpl implements CampsiteRequestValidator {
    @Override
    public List<String> validate(User user) {
        List<String> errors = new ArrayList<>();

        if(user.getId() != 0) errors.add(Messages.POST_USER_INVALID_ID);
        if(StringUtils.isEmpty(user.getEmailAddress())) errors.add(Messages.POST_USER_EXPECTED_EMAIL_NOT_FOUND);
        if(StringUtils.isEmpty(user.getFullName())) errors.add(Messages.POST_USER_EXPECTED_FULLNAME_NOT_FOUND);

        return errors;
    }
}
