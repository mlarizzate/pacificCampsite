package com.campsite.reservations.validate.places;

import com.campsite.reservations.model.Messages;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.validate.CampsiteRequestValidator;

import java.util.ArrayList;
import java.util.List;

public class CampsitePlaceGetRequestValidatorImpl implements CampsiteRequestValidator<Place> {
    @Override
    public List<String> validate(Place user) {
        List<String> errors = new ArrayList<>();

        if(user.getId() == 0) errors.add(Messages.PLACE_GET_EXPECTED_ID_NOT_FOUND);

        return errors;
    }
}
