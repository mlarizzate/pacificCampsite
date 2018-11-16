package com.campsite.reservations.strategy.reservations;

import com.campsite.reservations.exception.ReservationNotExistsException;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;

public class ReservationDeleteStrategy extends AbstractReservationStrategy {
    public ReservationDeleteStrategy(ReservationService service) {
        super(service);
    }

    @Override
    public Collection<Reservation> action(Reservation reservation) throws ReservationNotExistsException {
        Collection<Reservation>  dBReservations = new ArrayList<>();
                dBReservations.add(service.remove(reservation));
        return dBReservations;
    }
}
