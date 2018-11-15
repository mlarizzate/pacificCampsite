package com.campsite.reservations.controller;

import com.campsite.reservations.exception.CampsiteException;
import com.campsite.reservations.exception.ReservationNotExistsException;
import com.campsite.reservations.exception.UnexpectedVerbStrategyException;
import com.campsite.reservations.manager.ReservationManager;
import com.campsite.reservations.model.Messages;
import com.campsite.reservations.model.Reservation;
import com.campsite.reservations.response.CampsiteErrorResponse;
import com.campsite.reservations.response.CampsiteResponse;
import com.campsite.reservations.strategy.VerbStrategy;
import com.campsite.reservations.validate.CampsiteRequestValidator;
import com.campsite.reservations.validate.reservations.CampsiteReservationDeleteRequestValidatorImpl;
import com.campsite.reservations.validate.reservations.CampsiteReservationGetRequestValidatorImpl;
import com.campsite.reservations.validate.reservations.CampsiteReservationPostRequestValidatorImpl;
import com.campsite.reservations.validate.reservations.CampsiteReservationPutRequestValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/reservations"})
public class ReservationsController {
    @Autowired
    private ReservationManager manager;

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<Object> create(@RequestBody Reservation reservation) {
        CampsiteRequestValidator requestValidator = new CampsiteReservationPostRequestValidatorImpl();
        List<String> errors = requestValidator.validate(reservation);
        try {
            if(errors.isEmpty()){
                Reservation responsePlace =  manager.manage(VerbStrategy.POST, reservation);
                return new ResponseEntity<>(responsePlace, HttpStatus.OK);
            }else{
                return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
            }
        } catch (CampsiteException e) {
            errors.add(e.getMessage());
            return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return handleExceptions(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public ResponseEntity<Object> update(@RequestBody Reservation reservation) {
        CampsiteRequestValidator requestValidator = new CampsiteReservationGetRequestValidatorImpl();
        List<String> errors = requestValidator.validate(reservation);
        try {
            if(errors.isEmpty()){
                Reservation responsePlace =  manager.manage(VerbStrategy.PUT, reservation);
                return new ResponseEntity<>(responsePlace, HttpStatus.OK);
            }else{
                return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
            }
        } catch (CampsiteException e) {
            errors.add(e.getMessage());
            return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return handleExceptions(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        CampsiteRequestValidator requestValidator = new CampsiteReservationDeleteRequestValidatorImpl();
        Reservation reservation = new Reservation();
        reservation.setId(id);
        List<String> errors = requestValidator.validate(reservation);
        try {
            if(errors.isEmpty()){
                Reservation responsePlace =  manager.manage(VerbStrategy.DELETE, reservation);
                return new ResponseEntity<>(responsePlace, HttpStatus.OK);
            }else{
                return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
            }
        } catch (CampsiteException e) {
            errors.add(e.getMessage());
            return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return handleExceptions(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> get(@PathVariable long id) {
        CampsiteRequestValidator requestValidator = new CampsiteReservationGetRequestValidatorImpl();
        Reservation reservation = new Reservation();
        reservation.setId(id);
        List<String> errors = requestValidator.validate(reservation);
        try {
            if(errors.isEmpty()){
                Reservation responsePlace =  manager.manage(VerbStrategy.GET, reservation);
                return new ResponseEntity<>(responsePlace, HttpStatus.OK);
            }else{
                return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
            }
        } catch (CampsiteException e) {
            errors.add(e.getMessage());
            return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return handleExceptions(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    protected ResponseEntity<Object> handleExceptions(Exception ex,HttpStatus status) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        CampsiteErrorResponse campsiteErrorResponse = new CampsiteErrorResponse(status, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(campsiteErrorResponse, status);
    }

    protected ResponseEntity<Object> handleErors(String message, List<String> errors,HttpStatus status) {
        CampsiteErrorResponse campsiteErrorResponse = new CampsiteErrorResponse(status, message, errors);
        return new ResponseEntity<>(campsiteErrorResponse, status);
    }
}
