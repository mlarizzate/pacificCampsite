package com.campsite.reservations.service;

import com.campsite.reservations.exception.PlaceAlreadyExistsException;
import com.campsite.reservations.exception.PlaceNotExistException;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository repository;

    public Place addNew(Place place) throws PlaceAlreadyExistsException {
        //validate if exists
        if(repository.findByPlacePosition(place.getPlacePosition()) != null){
            throw new PlaceAlreadyExistsException();
        }else{
            return repository.save(place);
        }
    }

    public Place update(Place place) throws PlaceNotExistException {
        Place dBUser = repository.findById(place.getId());
        if(dBUser== null) throw new PlaceNotExistException();
        if(dBUser.equals(place) && (dBUser.hashCode() == place.hashCode())){
            repository.save(place);
        }else{
            throw new PlaceNotExistException();
        }
        return place;
    }

    public Place remove(Place user) throws PlaceNotExistException {
        Place dBUser = repository.findById(user.getId());
        if(dBUser== null) throw new PlaceNotExistException();
        repository.delete(user);
        return dBUser;
    }

    public Place get(Place user) throws PlaceNotExistException {
        Place dBUser = repository.findById(user.getId());
        if(dBUser== null){
            throw new PlaceNotExistException();
        }else{
            return dBUser;
        }
    }
}
