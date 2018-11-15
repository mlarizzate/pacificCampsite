package com.campsite.reservations.strategy.reservations;

import com.campsite.reservations.exception.ReservationNotExistsException;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.service.ReservationService;

public class ReservationDeleteStrategy extends AbstractReservationStrategy {
    public ReservationDeleteStrategy(ReservationService service) {
        super(service);
    }

    @Override
    public Reservation action(Reservation reservation) throws ReservationNotExistsException {
        Reservation dBReservation = service.remove(reservation);
        return dBReservation;
    }
}
