package com.campsite.reservations.strategy.reservations;

import com.campsite.reservations.exception.ReservationNotExistsException;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ReservationGetStrategy extends AbstractReservationStrategy {
    public ReservationGetStrategy(ReservationService service) {
        super(service);
    }

    @Override
    public Collection<Reservation> action(Reservation reservation) throws ReservationNotExistsException {
        Collection<Reservation> dbReservations = new ArrayList<>();

        dbReservations.add(service.get(reservation));
        return dbReservations;
    }
}
