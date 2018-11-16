package com.campsite.reservations.service;

import com.campsite.reservations.exception.CampsiteException;
import com.campsite.reservations.exception.OverlapedDatesForRequestedReservationException;
import com.campsite.reservations.exception.PlaceAlreadyReservedForGivenRangeException;
import com.campsite.reservations.exception.ReservationNotExistsException;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repository;

    @Autowired
    private PlaceService placeService;

    private Collection<Reservation> getOverlapedReservations(Date from, Date to){
        //gets the overlaped from and to
        Collection<Reservation> overlapedDateFrom = repository.findByDateFromBetween(from,to);
        Collection<Reservation> overlapedDateTo = repository.findByDateToBetween(from,to);

        //merges both lists
        overlapedDateFrom.addAll(overlapedDateTo);

        //create a new collection without duplicates
        Collection<Reservation> overlapedReservations = overlapedDateFrom.stream().distinct().collect(Collectors.toList());
        return overlapedReservations;
    }

    public Boolean isRangeFree(Date from, Date to){
        if(this.getOverlapedReservations(from, to).size() == 0){
            return true;
        }
        return false;
    }
    public Collection<Reservation> getOverlapedReservationsByPlaceId(long placeId, Date from, Date to) {
        //gets all places from DB
        Collection<Place> allPlaces = placeService.getAll();

        //gets all reservations  for range
        Collection<Reservation> overlapedReservations = this.getOverlapedReservations(from, to);
        if (overlapedReservations.size() == 0) {
            return Collections.emptyList();
        }

        //filters reservations by place id
        Collection<Reservation> overlapedReservationsByPlace = overlapedReservations.stream().filter(reservation -> reservation.getPlace().getId() == placeId).collect(Collectors.toList());

        return overlapedReservationsByPlace;
    }

    public Collection<Place> getFreePlacesByRange(Date from, Date to){
            //gets all reservations  for range
            Collection<Reservation> overlapedReservations =this.getOverlapedReservations(from, to);

            //gets reserved places
            Collection<Place> reservedPlaces = new ArrayList<>();
            overlapedReservations.stream().forEach(reservation -> reservedPlaces.add(reservation.getPlace()));

            //gets all places from DB
            Collection<Place> allPlaces = placeService.getAll();

            //removes all reserved place to gets the free places
            allPlaces.removeAll(reservedPlaces);

            return allPlaces;
    }
    public Reservation addNew(Reservation reservation) throws CampsiteException {
        //validate if exists
        if(repository.findByPlaceAndDateFromAndDateTo(reservation.getPlace(),reservation.getDateFrom(),reservation.getDateTo()) != null){
            throw new PlaceAlreadyReservedForGivenRangeException();
        }

        Collection<Reservation> overlapedReservationsByPlaceId = this.getOverlapedReservationsByPlaceId(reservation.getPlace().getId(),reservation.getDateFrom(),reservation.getDateTo());

        if(overlapedReservationsByPlaceId.size() > 0){
            throw new OverlapedDatesForRequestedReservationException();
        }
        return repository.save(reservation);

    }

    public Reservation update(Reservation reservation) throws ReservationNotExistsException {
        Reservation dBReservation = repository.findById(reservation.getId());
        if(dBReservation == null) throw new ReservationNotExistsException();
            repository.save(reservation);
        return reservation;
    }

    public Reservation remove(Reservation reservation) throws ReservationNotExistsException {
        Reservation dBReservation = repository.findById(reservation.getId());
        if(dBReservation== null) throw new ReservationNotExistsException();
        repository.delete(reservation);
        return dBReservation;
    }

    public Reservation get(Reservation reservation) throws ReservationNotExistsException {
        Reservation dBReservation = repository.findById(reservation.getId());
        if(dBReservation== null){
            throw new ReservationNotExistsException();
        }else{
            return dBReservation;
        }
    }


    public Collection<Reservation> getAll(Reservation reservation){
        return repository.findAll();
    }
}
