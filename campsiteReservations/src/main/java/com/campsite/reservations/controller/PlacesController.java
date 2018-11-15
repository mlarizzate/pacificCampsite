package com.campsite.reservations.controller;

import com.campsite.reservations.exception.PlaceNotExistException;
import com.campsite.reservations.exception.UnexpectedVerbStrategyException;
import com.campsite.reservations.manager.PlaceManager;
import com.campsite.reservations.model.Messages;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.response.CampsiteErrorResponse;
import com.campsite.reservations.strategy.VerbStrategy;
import com.campsite.reservations.validate.CampsiteRequestValidator;
import com.campsite.reservations.validate.places.CampsitePlaceDeleteRequestValidatorImpl;
import com.campsite.reservations.validate.places.CampsitePlaceGetRequestValidatorImpl;
import com.campsite.reservations.validate.places.CampsitePlacePostRequestValidatorImpl;
import com.campsite.reservations.validate.places.CampsitePlacePutRequestValidatorImpl;
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
@RequestMapping(value = {"/places"})
public class PlacesController {
    @Autowired
    private PlaceManager manager;

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<Object> create(@RequestBody Place place) {
        CampsiteRequestValidator requestValidator = new CampsitePlacePostRequestValidatorImpl();
        List<String> errors = requestValidator.validate(place);
        try {
            if(errors.isEmpty()){
                Place responsePlace =  manager.manage(VerbStrategy.POST, place);
                return new ResponseEntity<>(responsePlace, HttpStatus.OK);
            }else{
                return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
            }
        } catch (UnexpectedVerbStrategyException| PlaceNotExistException e) {
            errors.add(e.getMessage());
            return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return handleExceptions(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public ResponseEntity<Object> update(@RequestBody Place place) {
        CampsiteRequestValidator requestValidator = new CampsitePlacePutRequestValidatorImpl();
        List<String> errors = requestValidator.validate(place);
        try {
            if(errors.isEmpty()){
                Place responsePlace =  manager.manage(VerbStrategy.PUT, place);
                return new ResponseEntity<>(responsePlace, HttpStatus.OK);
            }else{
                return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
            }
        } catch (UnexpectedVerbStrategyException| PlaceNotExistException e) {
            errors.add(e.getMessage());
            return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return handleExceptions(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        CampsiteRequestValidator requestValidator = new CampsitePlaceDeleteRequestValidatorImpl();
        Place place = new Place();
        place.setId(id);
        List<String> errors = requestValidator.validate(place);
        try {
            if(errors.isEmpty()){
                Place responsePlace =  manager.manage(VerbStrategy.DELETE, place);
                return new ResponseEntity<>(responsePlace, HttpStatus.OK);
            }else{
                return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
            }
        } catch (UnexpectedVerbStrategyException| PlaceNotExistException e) {
            errors.add(e.getMessage());
            return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return handleExceptions(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> get(@PathVariable long id) {
        CampsiteRequestValidator requestValidator = new CampsitePlaceGetRequestValidatorImpl();
        Place place = new Place();
        place.setId(id);
        List<String> errors = requestValidator.validate(place);
        try {
            if(errors.isEmpty()){
                Place responsePlace =  manager.manage(VerbStrategy.GET, place);
                return new ResponseEntity<>(responsePlace, HttpStatus.OK);
            }else{
                return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
            }
        } catch (UnexpectedVerbStrategyException| PlaceNotExistException e) {
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
