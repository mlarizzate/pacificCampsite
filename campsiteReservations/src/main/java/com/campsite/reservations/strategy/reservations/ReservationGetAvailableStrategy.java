package com.campsite.reservations.strategy.reservations;

import com.campsite.reservations.exception.ReservationNotExistsException;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;

public class ReservationGetAvailableStrategy extends AbstractReservationStrategy {
    public ReservationGetAvailableStrategy(ReservationService service) {
        super(service);
    }

    @Override
    public Collection<Reservation> action(Reservation reservation) throws ReservationNotExistsException {
        //Gets freePlaces
        Collection<Place> freePlaces = service.getFreePlacesByRange(reservation.getDateFrom(), reservation.getDateTo());
        //Builds reservations to offer
        Collection<Reservation> reservationsToOffer = new ArrayList<>();

        freePlaces.stream().forEach(place -> reservationsToOffer.add(new Reservation(place,reservation.getDateFrom(),reservation.getDateTo())));
        return reservationsToOffer;
    }
}
