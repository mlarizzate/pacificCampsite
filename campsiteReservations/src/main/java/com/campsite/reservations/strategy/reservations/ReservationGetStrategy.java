package com.campsite.reservations.strategy.reservations;

import com.campsite.reservations.exception.ReservationNotExistsException;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.service.ReservationService;

public class ReservationGetStrategy extends AbstractReservationStrategy {
    public ReservationGetStrategy(ReservationService service) {
        super(service);
    }

    @Override
    public Reservation action(Reservation reservation) throws ReservationNotExistsException {
        return service.get(reservation);
    }
}
