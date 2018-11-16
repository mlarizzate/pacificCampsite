package com.campsite.reservations.strategy.places;

import com.campsite.reservations.exception.PlaceNotExistException;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.service.PlaceService;

import java.util.Collection;

public class PlaceDeleteStrategy extends AbstractPlaceStrategy {
    public PlaceDeleteStrategy(PlaceService service) {
        super(service);
    }

    @Override
    public Collection<Place> action(Place place) throws PlaceNotExistException {
        return service.remove(place);
    }
}
