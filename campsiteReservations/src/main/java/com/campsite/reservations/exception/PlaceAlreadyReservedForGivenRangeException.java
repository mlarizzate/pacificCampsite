package com.campsite.reservations.exception;


import com.campsite.reservations.model.Messages;

public class PlaceAlreadyReservedForGivenRangeException extends CampsiteException {
    public PlaceAlreadyReservedForGivenRangeException() {
        super(Messages.PLACE_EXCEPTION_ALREADY_RESERVED_FOR_RANGE);
    }
}
