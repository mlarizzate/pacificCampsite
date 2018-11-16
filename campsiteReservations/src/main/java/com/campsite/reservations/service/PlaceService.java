package com.campsite.reservations.service;

import com.campsite.reservations.exception.PlaceAlreadyExistsException;
import com.campsite.reservations.exception.PlaceNotExistException;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository repository;

    public Collection<Place> addNew(Place place) throws PlaceAlreadyExistsException {
        Collection<Place> dbPlaces = new ArrayList<>();

        //validate if exists
        if(repository.findByPlacePosition(place.getPlacePosition()) != null){
            throw new PlaceAlreadyExistsException();
        }else{
            repository.save(place);
            dbPlaces.add(place);
            return dbPlaces;
        }
    }

    public Collection<Place> update(Place place) throws PlaceNotExistException {
        Collection<Place> dbPlaces = new ArrayList<>();
        Place dBPlace = repository.findById(place.getId());
        if(dBPlace== null) throw new PlaceNotExistException();
        repository.save(place);
        dbPlaces.add(place);
        return dbPlaces;
    }

    public Collection<Place> remove(Place place) throws PlaceNotExistException {
        Collection<Place> dbPlaces = new ArrayList<>();
        Place dbPlace = repository.findById(place.getId());
        if(dbPlace == null) throw new PlaceNotExistException();
        repository.delete(place);
        dbPlaces.add(place);
        return dbPlaces;
    }

    public Collection<Place> get(Place place) throws PlaceNotExistException {
        Collection<Place> dbPlaces = new ArrayList<>();
        dbPlaces.add(repository.findById(place.getId()));
        if(dbPlaces.size() == 0 ){
            throw new PlaceNotExistException();
        }else{
            return dbPlaces;
        }
    }

    public Collection<Place> getAll(){
        return repository.findAll();
    }
}
