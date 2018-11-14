package com.campsite.users.strategy;

import com.campsite.users.exception.UnexpectedVerbStrategyException;
import com.campsite.users.service.UserService;

public abstract class AbstractUserStrategy implements UserActionStrategy{
    protected UserService userService;

    public AbstractUserStrategy(UserService userService) {
        this.userService = userService;
    }

    public static UserActionStrategy resolveStrategy(VerbStrategy verbStrategy, UserService userService) throws UnexpectedVerbStrategyException {
        UserActionStrategy userActionStrategy;
        switch (verbStrategy) {
            case DELETE:
                userActionStrategy = new UserDeleteStrategy(userService);
                break;
            case POST:
                userActionStrategy = new UserPostStrategy(userService);
                break;
            case PUT:
                userActionStrategy = new UserPutStrategy(userService);
                break;
            case GET:
                userActionStrategy = new UserGetStrategy(userService);
                break;

            default:
                throw new UnexpectedVerbStrategyException();

        }
        return userActionStrategy;
    }
}
