package com.campsite.reservations.strategy.reservations;

import com.campsite.reservations.exception.CampsiteException;
import com.campsite.reservations.exception.PlaceAlreadyReservedForGivenRangeException;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ReservationPostStrategy extends AbstractReservationStrategy {
    public ReservationPostStrategy(ReservationService service) {
        super(service);
    }

    @Override
    public Collection<Reservation> action(Reservation reservation) throws CampsiteException {
        Collection<Reservation> dbReservations = new ArrayList<>();
        dbReservations.add(service.addNew(reservation));
        return dbReservations;
    }
}
