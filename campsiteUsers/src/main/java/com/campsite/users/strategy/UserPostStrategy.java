package com.campsite.users.strategy;

import com.campsite.users.exception.UserAlreadyExistsException;
import com.campsite.users.model.User;
import com.campsite.users.service.UserService;

public class UserPostStrategy extends AbstractUserStrategy {
    public UserPostStrategy(UserService userService) {
        super(userService);
    }

    @Override
    public User action(User user) throws UserAlreadyExistsException {
        userService.addNewUser(user);
        return user;
    }
}
