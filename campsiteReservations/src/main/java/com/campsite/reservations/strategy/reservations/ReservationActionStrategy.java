package com.campsite.reservations.strategy.reservations;

import com.campsite.reservations.exception.CampsiteException;
import com.campsite.reservations.model.Reservation;

public interface ReservationActionStrategy {
    Reservation action(Reservation place) throws CampsiteException;


}
