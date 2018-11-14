package com.campsite.users.strategy;

import com.campsite.users.exception.UserNotExistException;
import com.campsite.users.model.User;
import com.campsite.users.service.UserService;

public class UserDeleteStrategy extends AbstractUserStrategy{
    public UserDeleteStrategy(UserService userService) {
        super(userService);
    }

    @Override
    public User action(User user) throws UserNotExistException {
        User dBUser = userService.removeUser(user);
        return dBUser;
    }
}
