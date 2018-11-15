package com.campsite.reservations.manager;

import com.campsite.reservations.exception.CampsiteException;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.service.ReservationService;
import com.campsite.reservations.strategy.VerbStrategy;
import com.campsite.reservations.strategy.reservations.AbstractReservationStrategy;
import com.campsite.reservations.strategy.reservations.ReservationActionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ReservationManager {
    @Autowired
    @Qualifier("reservationMaxDays")
    private Integer reservationMaxDays;

    @Autowired
    @Qualifier("reservationMinDays")
    private Integer reservationMinDays;

    @Autowired
    @Qualifier("checkInHours")
    private String checkInHours;

    @Autowired
    @Qualifier("checkInMinutes")
    private String checkInMinutes;

    @Autowired
    @Qualifier("checkOutHours")
    private String checkOutHours;

    @Autowired
    @Qualifier("checkOutMinutes")
    private String checkOutMinutes;

    @Autowired
    private ReservationService service;

    public Reservation manage(VerbStrategy verbStrategy, Reservation reservation) throws CampsiteException {
        ReservationActionStrategy userActionStrategy = AbstractReservationStrategy.resolveStrategy(verbStrategy, service);
        Reservation dbReservation = userActionStrategy.action(reservation);
        return dbReservation;

    }
}
