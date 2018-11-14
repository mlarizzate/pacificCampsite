package com.campsite.reservations.validate.places;

import com.campsite.reservations.model.Messages;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.validate.CampsiteRequestValidator;

import java.util.ArrayList;
import java.util.List;

public class CampsitePlacePostRequestValidatorImpl implements CampsiteRequestValidator<Place> {
    @Override
    public List<String> validate(Place place) {
        List<String> errors = new ArrayList<>();

        if(place.getId() != 0) errors.add(Messages.PLACE_POST_INVALID_ID);

        return errors;
    }
}
