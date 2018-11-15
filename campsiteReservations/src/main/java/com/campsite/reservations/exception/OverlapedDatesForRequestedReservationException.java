package com.campsite.reservations.exception;

import com.campsite.reservations.model.Messages;

public class OverlapedDatesForRequestedReservationException extends CampsiteException {
    public OverlapedDatesForRequestedReservationException() {
        super(Messages.RESERVATION_OVERLAPED_DATES_FOR_REQUESTED);
    }
}
