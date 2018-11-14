package com.campsite.reservations.exception;


import com.campsite.reservations.model.Messages;

public class PlaceNotExistException extends CampsiteException {
    public PlaceNotExistException() {
        super(Messages.PLACE_EXCEPTION_PLACE_DOESNT_EXISTS);
    }
}
