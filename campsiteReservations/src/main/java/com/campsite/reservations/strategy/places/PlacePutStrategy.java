package com.campsite.reservations.strategy.places;

import com.campsite.reservations.exception.PlaceNotExistException;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.service.PlaceService;

import java.util.Collection;

public class PlacePutStrategy extends AbstractPlaceStrategy {
    public PlacePutStrategy(PlaceService service) {
        super(service);
    }

    @Override
    public Collection<Place> action(Place place) throws PlaceNotExistException {
        return service.update(place);
    }
}
