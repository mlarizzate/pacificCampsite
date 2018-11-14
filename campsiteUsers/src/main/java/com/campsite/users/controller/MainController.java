package com.campsite.users.controller;

import com.campsite.users.exception.CampsiteException;
import com.campsite.users.exception.UnexpectedVerbStrategyException;
import com.campsite.users.exception.UserNotExistException;
import com.campsite.users.manager.UserManager;
import com.campsite.users.model.Messages;
import com.campsite.users.model.User;
import com.campsite.users.response.CampsiteResponse;
import com.campsite.users.strategy.VerbStrategy;
import com.campsite.users.validate.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private String moduleName;

    @Autowired
    private UserManager userManager;

    @Autowired
    DataSource dataSource;

    @Autowired
    public MainController() {

    }

    /**
     * Shows statistic on an easy way.
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/status")
    public ResponseEntity<Object> status() {
        Map map = new HashMap<String, String >();
        map.put("campsiteModuleName", moduleName);
        map.put("campsiteModuleStatus", "Working");

        try {
            if(StringUtils.isNotEmpty(dataSource.getConnection().getCatalog())){
                map.put("DBStatus", "OK");
            }else{
                map.put("DBStatus", "Failed");
            }
        } catch (SQLException e) {
            map.put("UnexpectedException", "Something goes wrong with the Database. Exception: " + e.getMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public ResponseEntity<CampsiteResponse> CreateUser(@RequestBody User user) {
        CampsiteRequestValidator requestValidator = new CampsitePostRequestValidatorImpl();
        List<String> errors = requestValidator.validate(user);
        CampsiteResponse campsiteResponse = null;
        User responseUser = null;
        try {
            if(errors.isEmpty()){
                 responseUser =  userManager.manage(VerbStrategy.POST, user);
            }
        } catch (CampsiteException e) {
            errors.add(e.getMessage());
        } catch (Exception e){
            return new ResponseEntity<>(new CampsiteResponse(true,e.toString(),null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(errors.isEmpty()){
            campsiteResponse = new CampsiteResponse(false, Messages.POST_USER_SUCCESSFULLY_CREATED, responseUser);
        }else{
            campsiteResponse = new CampsiteResponse(true,errors.toString(),user);
        }
        return new ResponseEntity<>(campsiteResponse, campsiteResponse.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);

    }
    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    public ResponseEntity<CampsiteResponse> modifyUser(@RequestBody User user) {
        CampsiteRequestValidator requestValidator = new CampsitePutRequestValidatorImpl();
        List<String> errors = requestValidator.validate(user);
        CampsiteResponse campsiteResponse = null;
        User responseUser = null;
        try {
            if(errors.isEmpty()){
                responseUser =  userManager.manage(VerbStrategy.PUT, user);
            }
        } catch (UnexpectedVerbStrategyException| UserNotExistException e) {
            errors.add(e.getMessage());
        } catch (Exception e){
            return new ResponseEntity<>(new CampsiteResponse(true,e.toString(),null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(errors.isEmpty()){
            campsiteResponse = new CampsiteResponse(false, Messages.PUT_USER_CHANGE_SUCCESSFULLY_PROCESSED, responseUser);
        }else{
            campsiteResponse = new CampsiteResponse(true,errors.toString(),null);
        }
        return new ResponseEntity<>(campsiteResponse, campsiteResponse.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public ResponseEntity<CampsiteResponse> dropUser(@PathVariable long id) {
        CampsiteRequestValidator requestValidator = new CampsiteDeleteRequestValidatorImpl();
        User user = new User();
        user.setId(id);
        List<String> errors = requestValidator.validate(user);
        CampsiteResponse campsiteResponse = null;
        User responseUser = null;
        try {
            if(errors.isEmpty()){
                responseUser =  userManager.manage(VerbStrategy.DELETE, user);
            }
        } catch (UnexpectedVerbStrategyException| UserNotExistException e) {
            errors.add(e.getMessage());
        } catch (Exception e){
            return new ResponseEntity<>(new CampsiteResponse(true,e.toString(),null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(errors.isEmpty()){
            campsiteResponse = new CampsiteResponse(false, Messages.DELETE_USER_DELLETED_SUCCESSFULLY, responseUser);
        }else{
            campsiteResponse = new CampsiteResponse(true,errors.toString(),null);
        }
        return new ResponseEntity<>(campsiteResponse, campsiteResponse.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public ResponseEntity<CampsiteResponse> getUser(@PathVariable long id) {
        CampsiteRequestValidator requestValidator = new CampsiteGetRequestValidatorImpl();
        User user = new User();
        user.setId(id);
        List<String> errors = requestValidator.validate(user);
        CampsiteResponse campsiteResponse = null;
        User responseUser = null;
        try {
            if(errors.isEmpty()){
                responseUser =  userManager.manage(VerbStrategy.GET, user);
            }
        } catch (UnexpectedVerbStrategyException| UserNotExistException e) {
            errors.add(e.getMessage());
        } catch (Exception e){
            return new ResponseEntity<>(new CampsiteResponse(true,e.toString(),null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(errors.isEmpty()){
            campsiteResponse = new CampsiteResponse(false, Messages.GET_USER_FOUND_SUCCESSFULLY, responseUser);
        }else{
            campsiteResponse = new CampsiteResponse(true,errors.toString(),null);
        }
        return new ResponseEntity<>(campsiteResponse, campsiteResponse.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
}
