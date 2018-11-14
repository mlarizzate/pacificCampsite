package com.campsite.users.service;

import com.campsite.users.exception.UserAlreadyExistsException;
import com.campsite.users.exception.UserNotExistException;
import com.campsite.users.model.User;
import com.campsite.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addNewUser(User user) throws UserAlreadyExistsException {
        //validate if exists
        if(userRepository.findByEmailAddressAndFullName(user.getEmailAddress(),user.getFullName()) != null){
            throw new UserAlreadyExistsException();
        }else{
            return userRepository.save(user);
        }
    }

    public User updateUser(User user) throws UserNotExistException {
        User dBUser = userRepository.findById(user.getId());
        if(dBUser== null) throw new UserNotExistException();
        if(dBUser.equals(user) && (dBUser.hashCode() == user.hashCode())){
            userRepository.save(user);
        }else{
            throw new UserNotExistException();
        }
        return user;
    }

    public User removeUser(User user) throws UserNotExistException {
        User dBUser = userRepository.findById(user.getId());
        if(dBUser== null) throw new UserNotExistException();
        userRepository.delete(user);
        return dBUser;
    }

    public User getUser(User user) throws UserNotExistException {
        User dBUser = userRepository.findById(user.getId());
        if(dBUser== null){
            throw new UserNotExistException();
        }else{
            return dBUser;
        }
    }
}
