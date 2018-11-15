package com.campsite.reservations.exception;


import com.campsite.reservations.model.Messages;

public class ReservationNotExistsException extends CampsiteException {
    public ReservationNotExistsException() {
        super(Messages.RESERVATION_EXCEPTION_NOT_EXISTS);
    }
}
