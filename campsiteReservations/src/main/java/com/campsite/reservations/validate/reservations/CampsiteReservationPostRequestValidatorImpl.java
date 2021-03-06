package com.campsite.reservations.validate.reservations;

import com.campsite.reservations.model.Messages;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.validate.CampsiteRequestValidator;

import java.util.ArrayList;
import java.util.List;

public class CampsiteReservationPostRequestValidatorImpl implements CampsiteRequestValidator<Reservation> {
    @Override
    public List<String> validate(Reservation reservation) {
        List<String> errors = new ArrayList<>();

        if(reservation.getId() != 0) errors.add(Messages.RESERVATION_POST_INVALID_ID);
        if(reservation.getUserId() == 0) errors.add(Messages.RESERVATION_POST_EXPECTED_USER_ID);
        if(reservation.getPlace() == null) errors.add(Messages.RESERVATION_POST_EXPECTED_PLACE);

        if(reservation.getDateFrom() == null) errors.add(Messages.RESERVATION_POST_EXPECTED_DATE_FROM);
        if(reservation.getDateTo() == null) errors.add(Messages.RESERVATION_POST_EXPECTED_DATE_TO);

        return errors;
    }
}
