package com.campsite.users.manager;

import com.campsite.users.exception.CampsiteException;
import com.campsite.users.exception.UnexpectedVerbStrategyException;
import com.campsite.users.exception.UserNotExistException;
import com.campsite.users.model.User;
import com.campsite.users.repository.UserRepository;
import com.campsite.users.response.CampsiteResponse;
import com.campsite.users.service.UserService;
import com.campsite.users.strategy.AbstractUserStrategy;
import com.campsite.users.strategy.UserActionStrategy;
import com.campsite.users.strategy.VerbStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManager {
    @Autowired
    UserService userService;

    public User manage(VerbStrategy verbStrategy, User user) throws CampsiteException {
            UserActionStrategy userActionStrategy = AbstractUserStrategy.resolveStrategy(verbStrategy, userService);
            User dbUser = userActionStrategy.action(user);
            return dbUser;

    }
}
