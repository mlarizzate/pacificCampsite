package com.campsite.reservations.strategy.reservations;

import com.campsite.reservations.exception.UnexpectedVerbStrategyException;
import com.campsite.reservations.service.PlaceService;
import com.campsite.reservations.service.ReservationService;
import com.campsite.reservations.strategy.VerbStrategy;

public abstract class AbstractReservationStrategy implements ReservationActionStrategy {
    protected ReservationService service;

    public AbstractReservationStrategy(ReservationService service) {
        this.service = service;
    }

    public static ReservationActionStrategy resolveStrategy(VerbStrategy verbStrategy, ReservationService service) throws UnexpectedVerbStrategyException {
        ReservationActionStrategy actionStrategy;
        switch (verbStrategy) {
            case DELETE:
                actionStrategy = new ReservationDeleteStrategy(service);
                break;
            case POST:
                actionStrategy = new ReservationPostStrategy(service);
                break;
            case PUT:
                actionStrategy = new ReservationPutStrategy(service);
                break;
            case GET:
                actionStrategy = new ReservationGetStrategy(service);
                break;

            default:
                throw new UnexpectedVerbStrategyException();

        }
        return actionStrategy;
    }
}
