package com.campsite.reservations.validate.places;

import com.campsite.reservations.model.Place;
import com.campsite.reservations.validate.CampsiteRequestValidator;

import java.util.ArrayList;
import java.util.List;

public class CampsitePlaceGetAllRequestValidatorImpl implements CampsiteRequestValidator<Place> {
    @Override
    public List<String> validate(Place place) {
        List<String> errors = new ArrayList<>();

        return errors;
    }
}
