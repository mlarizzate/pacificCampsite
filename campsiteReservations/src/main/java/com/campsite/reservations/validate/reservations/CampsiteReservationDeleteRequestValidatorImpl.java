package com.campsite.reservations.validate.reservations;

import com.campsite.reservations.model.Messages;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.validate.CampsiteRequestValidator;

import java.util.ArrayList;
import java.util.List;

public class CampsiteReservationDeleteRequestValidatorImpl implements CampsiteRequestValidator<Reservation> {
    @Override
    public List<String> validate(Reservation reservation) {
        List<String> errors = new ArrayList<>();

        if(reservation.getId() == 0) errors.add(Messages.RESERVATION_DELETE_EXPECTED_ID_NOT_FOUND);

        return errors;
    }
}
