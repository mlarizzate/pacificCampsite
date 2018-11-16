package com.campsite.reservations.strategy.reservations;

import com.campsite.reservations.exception.ReservationNotExistsException;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.service.ReservationService;

import java.util.Collection;

public class ReservationGetAllStrategy extends AbstractReservationStrategy {
    public ReservationGetAllStrategy(ReservationService service) {
        super(service);
    }

    @Override
    public Collection<Reservation> action(Reservation reservation) throws ReservationNotExistsException {
        return service.getAll(reservation);
    }
}
