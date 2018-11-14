package com.campsite.users.exception;

import com.campsite.users.model.Messages;

public class UserAlreadyExistsException extends CampsiteException {
    public UserAlreadyExistsException() {
        super(Messages.EXCEPTION_USER_ALREADY_EXISTS);
    }
}
