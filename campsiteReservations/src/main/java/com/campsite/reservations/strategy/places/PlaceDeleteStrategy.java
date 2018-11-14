package com.campsite.reservations.strategy.places;

import com.campsite.reservations.exception.PlaceNotExistException;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.service.PlaceService;

public class PlaceDeleteStrategy extends AbstractPlaceStrategy {
    public PlaceDeleteStrategy(PlaceService service) {
        super(service);
    }

    @Override
    public Place action(Place place) throws PlaceNotExistException {
        Place dBPlace = service.remove(place);
        return dBPlace;
    }
}
