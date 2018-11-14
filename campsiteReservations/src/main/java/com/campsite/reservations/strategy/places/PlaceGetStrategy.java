package com.campsite.reservations.strategy.places;

import com.campsite.reservations.exception.PlaceNotExistException;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.service.PlaceService;

public class PlaceGetStrategy extends AbstractPlaceStrategy {
    public PlaceGetStrategy(PlaceService service) {
        super(service);
    }

    @Override
    public Place action(Place place) throws PlaceNotExistException {
        return service.get(place);
    }
}
