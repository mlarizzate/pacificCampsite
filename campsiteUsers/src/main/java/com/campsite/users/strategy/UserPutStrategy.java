package com.campsite.users.strategy;

import com.campsite.users.exception.UserNotExistException;
import com.campsite.users.model.User;
import com.campsite.users.service.UserService;

public class UserPutStrategy extends AbstractUserStrategy {
    public UserPutStrategy(UserService userService) {
        super(userService);
    }

    @Override
    public User action(User user) throws UserNotExistException {
        return userService.updateUser(user);
    }
}
