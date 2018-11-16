package com.campsite.reservations.manager;

import com.campsite.reservations.exception.CampsiteException;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.service.PlaceService;
import com.campsite.reservations.strategy.VerbStrategy;
import com.campsite.reservations.strategy.places.AbstractPlaceStrategy;
import com.campsite.reservations.strategy.places.PlaceActionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PlaceManager {
    @Autowired
    PlaceService service;

    public Collection<Place> manage(VerbStrategy verbStrategy, Place place) throws CampsiteException {
        PlaceActionStrategy userActionStrategy = AbstractPlaceStrategy.resolveStrategy(verbStrategy, service);
        return userActionStrategy.action(place);

    }
}
