package com.campsite.reservations.strategy.reservations;

import com.campsite.reservations.exception.CampsiteException;
import com.campsite.reservations.model.Reservation;

import java.util.Collection;

public interface ReservationActionStrategy {
    Collection<Reservation> action(Reservation place) throws CampsiteException;


}
