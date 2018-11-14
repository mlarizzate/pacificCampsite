package com.campsite.users.exception;

import com.campsite.users.model.Messages;

public class UnexpectedVerbStrategyException extends CampsiteException {
    public UnexpectedVerbStrategyException() {
        super(Messages.EXCEPTION_UNEXPECTED_VERB);
    }
}
