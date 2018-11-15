package com.campsite.reservations.service;

import com.campsite.reservations.exception.CampsiteException;
import com.campsite.reservations.exception.OverlapedDatesForRequestedReservationException;
import com.campsite.reservations.exception.PlaceAlreadyReservedForGivenRangeException;
import com.campsite.reservations.exception.ReservationNotExistsException;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repository;

    @Autowired
    private PlaceService placeService;

    public Boolean isRangeFree(Date from, Date to){
        List <Reservation> overlapedReservations = repository.findOverlapings(from,to);
        if(overlapedReservations.isEmpty()){
            return true;
        }
        return false;
    }
    public Boolean isRangeFreeByPlace(long placeId, Date from, Date to){
        List <Reservation> overlapedReservations = repository.findOverlapingsByPlace(from,to, placeId);
        if(overlapedReservations.isEmpty()){
            return true;
        }
        return false;
    }
    public Reservation addNew(Reservation reservation) throws CampsiteException {
        //validate if exists
        if(repository.findByPlaceAndDateFromAndDateTo(reservation.getPlace(),reservation.getDateFrom(),reservation.getDateTo()) != null){
            throw new PlaceAlreadyReservedForGivenRangeException();
        }
        if(!this.isRangeFree(reservation.getDateFrom(),reservation.getDateTo())){
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


    public List<Reservation> getAll(){
        return repository.findAll();
    }
}
