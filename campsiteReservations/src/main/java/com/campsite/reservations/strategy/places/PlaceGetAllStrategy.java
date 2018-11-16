package com.campsite.reservations.strategy.places;

import com.campsite.reservations.model.Place;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.service.PlaceService;

import java.util.Collection;

public class PlaceGetAllStrategy extends AbstractPlaceStrategy {
    public PlaceGetAllStrategy(PlaceService service) {
        super(service);
    }

    @Override
    public Collection<Place> action(Place reservation) {
        return service.getAll();
    }
}
