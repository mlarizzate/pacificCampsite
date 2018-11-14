package com.campsite.reservations.strategy.places;

import com.campsite.reservations.exception.UnexpectedVerbStrategyException;
import com.campsite.reservations.service.PlaceService;
import com.campsite.reservations.strategy.VerbStrategy;

public abstract class AbstractPlaceStrategy implements PlaceActionStrategy {
    protected PlaceService service;

    public AbstractPlaceStrategy(PlaceService service) {
        this.service = service;
    }

    public static PlaceActionStrategy resolveStrategy(VerbStrategy verbStrategy, PlaceService service) throws UnexpectedVerbStrategyException {
        PlaceActionStrategy actionStrategy;
        switch (verbStrategy) {
            case DELETE:
                actionStrategy = new PlaceDeleteStrategy(service);
                break;
            case POST:
                actionStrategy = new PlacePostStrategy(service);
                break;
            case PUT:
                actionStrategy = new PlacePutStrategy(service);
                break;
            case GET:
                actionStrategy = new PlaceGetStrategy(service);
                break;

            default:
                throw new UnexpectedVerbStrategyException();

        }
        return actionStrategy;
    }
}
