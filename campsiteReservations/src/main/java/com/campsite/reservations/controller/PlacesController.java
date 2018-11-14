package com.campsite.reservations.controller;

import com.campsite.reservations.exception.CampsiteException;
import com.campsite.reservations.exception.PlaceNotExistException;
import com.campsite.reservations.exception.UnexpectedVerbStrategyException;
import com.campsite.reservations.manager.PlaceManager;
import com.campsite.reservations.model.Messages;
import com.campsite.reservations.model.Place;
import com.campsite.reservations.response.CampsiteResponse;
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

import java.util.List;

@Controller
@RequestMapping(value = {"/places"})
public class PlacesController {
    @Autowired
    private PlaceManager manager;

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<CampsiteResponse> create(@RequestBody Place place) {
        CampsiteRequestValidator requestValidator = new CampsitePlacePostRequestValidatorImpl();
        List<String> errors = requestValidator.validate(place);
        CampsiteResponse campsiteResponse = null;
        Place responsePlace = null;
        try {
            if(errors.isEmpty()){
                responsePlace =  manager.manage(VerbStrategy.POST, place);
            }
        } catch (CampsiteException e) {
            errors.add(e.getMessage());
        } catch (Exception e){
            return new ResponseEntity<>(new CampsiteResponse(true,e.toString(),null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(errors.isEmpty()){
            campsiteResponse = new CampsiteResponse(false, Messages.PLACE_POST_SUCCESSFULLY_CREATED, responsePlace);
        }else{
            campsiteResponse = new CampsiteResponse(true,errors.toString(),place);
        }
        return new ResponseEntity<>(campsiteResponse, campsiteResponse.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);

    }
    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public ResponseEntity<CampsiteResponse> update(@RequestBody Place place) {
        CampsiteRequestValidator requestValidator = new CampsitePlacePutRequestValidatorImpl();
        List<String> errors = requestValidator.validate(place);
        CampsiteResponse campsiteResponse = null;
        Place responsePlace = null;
        try {
            if(errors.isEmpty()){
                responsePlace =  manager.manage(VerbStrategy.PUT, place);
            }
        } catch (UnexpectedVerbStrategyException| PlaceNotExistException e) {
            errors.add(e.getMessage());
        } catch (Exception e){
            return new ResponseEntity<>(new CampsiteResponse(true,e.toString(),null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(errors.isEmpty()){
            campsiteResponse = new CampsiteResponse(false, Messages.PLACE_PUT_UPDATE_SUCCESSFULLY_PROCESSED, responsePlace);
        }else{
            campsiteResponse = new CampsiteResponse(true,errors.toString(),null);
        }
        return new ResponseEntity<>(campsiteResponse, campsiteResponse.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<CampsiteResponse> delete(@PathVariable long id) {
        CampsiteRequestValidator requestValidator = new CampsitePlaceDeleteRequestValidatorImpl();
        Place place = new Place();
        place.setId(id);
        List<String> errors = requestValidator.validate(place);
        CampsiteResponse campsiteResponse = null;
        Place responsePlace = null;
        try {
            if(errors.isEmpty()){
                responsePlace =  manager.manage(VerbStrategy.DELETE, place);
            }
        } catch (UnexpectedVerbStrategyException | PlaceNotExistException e) {
            errors.add(e.getMessage());
        } catch (Exception e){
            return new ResponseEntity<>(new CampsiteResponse(true,e.toString(),null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(errors.isEmpty()){
            campsiteResponse = new CampsiteResponse(false, Messages.PLACE_DELETE_DELLETED_SUCCESSFULLY, responsePlace);
        }else{
            campsiteResponse = new CampsiteResponse(true,errors.toString(),null);
        }
        return new ResponseEntity<>(campsiteResponse, campsiteResponse.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<CampsiteResponse> get(@PathVariable long id) {
        CampsiteRequestValidator requestValidator = new CampsitePlaceGetRequestValidatorImpl();
        Place place = new Place();
        place.setId(id);
        List<String> errors = requestValidator.validate(place);
        CampsiteResponse campsiteResponse = null;
        Place responsePlace = null;
        try {
            if(errors.isEmpty()){
                responsePlace =  manager.manage(VerbStrategy.GET, place);
            }
        } catch (UnexpectedVerbStrategyException| PlaceNotExistException e) {
            errors.add(e.getMessage());
        } catch (Exception e){
            return new ResponseEntity<>(new CampsiteResponse(true,e.toString(),null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(errors.isEmpty()){
            campsiteResponse = new CampsiteResponse(false, Messages.PLACE_GET_FOUND_SUCCESSFULLY, responsePlace);
        }else{
            campsiteResponse = new CampsiteResponse(true,errors.toString(),null);
        }
        return new ResponseEntity<>(campsiteResponse, campsiteResponse.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
}
