package com.campsite.reservations.exception;


import com.campsite.reservations.model.Messages;

public class PlaceAlreadyExistsException extends CampsiteException {
    public PlaceAlreadyExistsException() {
        super(Messages.PLACE_EXCEPTION_PLACE_ALREADY_EXISTS);
    }
}
