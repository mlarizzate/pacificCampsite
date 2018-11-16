package com.campsite.reservations.strategy.reservations;

import com.campsite.reservations.exception.PlaceNotExistException;
import com.campsite.reservations.exception.ReservationNotExistsException;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.service.PlaceService;
import com.campsite.reservations.service.ReservationService;

import java.util.Collection;
import java.util.Collections;

public class ReservationPutStrategy extends AbstractReservationStrategy {
    public ReservationPutStrategy(ReservationService service) {
        super(service);
    }

    @Override
    public Collection<Reservation> action(Reservation reservation) throws ReservationNotExistsException {
        Collection<Reservation> dbReservations = Collections.emptyList();
        dbReservations.add(service.update(reservation));
        return dbReservations;
    }
}
