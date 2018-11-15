package com.campsite.reservations.strategy.reservations;

import com.campsite.reservations.exception.PlaceNotExistException;
import com.campsite.reservations.exception.ReservationNotExistsException;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.service.PlaceService;
import com.campsite.reservations.service.ReservationService;

public class ReservationPutStrategy extends AbstractReservationStrategy {
    public ReservationPutStrategy(ReservationService service) {
        super(service);
    }

    @Override
    public Reservation action(Reservation reservation) throws ReservationNotExistsException {
        return service.update(reservation);
    }
}
