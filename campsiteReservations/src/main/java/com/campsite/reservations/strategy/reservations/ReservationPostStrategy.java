package com.campsite.reservations.strategy.reservations;

import com.campsite.reservations.exception.CampsiteException;
import com.campsite.reservations.exception.PlaceAlreadyReservedForGivenRangeException;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.service.ReservationService;

public class ReservationPostStrategy extends AbstractReservationStrategy {
    public ReservationPostStrategy(ReservationService service) {
        super(service);
    }

    @Override
    public Reservation action(Reservation reservation) throws CampsiteException {
        service.addNew(reservation);
        return reservation;
    }
}
