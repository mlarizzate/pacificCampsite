package com.campsite.reservations.exception;


import com.campsite.reservations.model.Messages;

public class UnexpectedVerbStrategyException extends CampsiteException {
    public UnexpectedVerbStrategyException() {
        super(Messages.EXCEPTION_UNEXPECTED_VERB);
    }
}
