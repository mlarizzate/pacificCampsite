package com.campsite.reservations.strategy.places;

import com.campsite.reservations.exception.CampsiteException;
import com.campsite.reservations.model.Place;

public interface PlaceActionStrategy {
    Place action(Place place) throws CampsiteException;


}
