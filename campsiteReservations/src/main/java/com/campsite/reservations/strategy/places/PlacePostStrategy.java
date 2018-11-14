package com.campsite.reservations.strategy.places;

import com.campsite.reservations.exception.PlaceAlreadyExistsException;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.service.PlaceService;

public class PlacePostStrategy extends AbstractPlaceStrategy {
    public PlacePostStrategy(PlaceService service) {
        super(service);
    }

    @Override
    public Place action(Place place) throws PlaceAlreadyExistsException {
        service.addNew(place);
        return place;
    }
}
