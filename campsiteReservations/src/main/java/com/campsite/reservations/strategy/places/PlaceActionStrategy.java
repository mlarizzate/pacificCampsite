package com.campsite.reservations.strategy.places;

import com.campsite.reservations.exception.CampsiteException;
import com.campsite.reservations.model.Place;

import java.util.Collection;

public interface PlaceActionStrategy {
    Collection<Place> action(Place place) throws CampsiteException;


}
