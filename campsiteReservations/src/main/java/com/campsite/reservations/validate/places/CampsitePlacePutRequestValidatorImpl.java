package com.campsite.reservations.validate.places;

import com.campsite.reservations.model.Messages;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.validate.CampsiteRequestValidator;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CampsitePlacePutRequestValidatorImpl implements CampsiteRequestValidator<Place> {
    @Override
    public List<String> validate(Place place) {
        List<String> errors = new ArrayList<>();

        if(place.getId() == 0) errors.add(Messages.PLACE_PUT_EXPECTED_ID_NOT_FOUND);
        if(StringUtils.isEmpty(place.getPlacePosition())) errors.add(Messages.PLACE_PUT_EXPECTED_PLACE_POSITION_NOT_FOUND);

        return errors;
    }
}
