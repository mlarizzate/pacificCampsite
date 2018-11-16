package com.campsite.reservations.strategy.places;

import com.campsite.reservations.exception.PlaceAlreadyExistsException;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.service.PlaceService;

import java.util.Collection;

public class PlacePostStrategy extends AbstractPlaceStrategy {
    public PlacePostStrategy(PlaceService service) {
        super(service);
    }

    @Override
    public Collection<Place> action(Place place) throws PlaceAlreadyExistsException {
        return service.addNew(place);
    }
}
