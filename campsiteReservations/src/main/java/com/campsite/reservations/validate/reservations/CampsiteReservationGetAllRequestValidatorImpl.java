package com.campsite.reservations.validate.reservations;

import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.validate.CampsiteRequestValidator;

import java.util.ArrayList;
import java.util.List;

public class CampsiteReservationGetAllRequestValidatorImpl implements CampsiteRequestValidator<Reservation> {
    @Override
    public List<String> validate(Reservation reservation) {
        List<String> errors = new ArrayList<>();

        return errors;
    }
}
