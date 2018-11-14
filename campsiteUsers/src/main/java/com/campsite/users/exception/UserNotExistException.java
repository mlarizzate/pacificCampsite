package com.campsite.users.exception;

import com.campsite.users.model.Messages;

public class UserNotExistException extends CampsiteException {
    public UserNotExistException() {
        super(Messages.EXCEPTION_USER_NOT_EXISTS);
    }
}
