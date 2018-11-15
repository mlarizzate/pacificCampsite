package com.campsite.users.controller;

import com.campsite.users.exception.CampsiteException;
import com.campsite.users.exception.UnexpectedVerbStrategyException;
import com.campsite.users.exception.UserNotExistException;
import com.campsite.users.manager.UserManager;
import com.campsite.users.model.Messages;
import com.campsite.users.model.User;
import com.campsite.users.response.CampsiteErrorResponse;
import com.campsite.users.response.CampsiteResponse;
import com.campsite.users.strategy.VerbStrategy;
import com.campsite.users.validate.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public ResponseEntity<Object> CreateUser(@RequestBody User user) {
        CampsiteRequestValidator requestValidator = new CampsitePostRequestValidatorImpl();
        List<String> errors = requestValidator.validate(user);
        try {
            if(errors.isEmpty()){
                User responseUser =  userManager.manage(VerbStrategy.POST, user);
                return new ResponseEntity<>(responseUser, HttpStatus.OK);
            }else{
                return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
            }
        } catch (UnexpectedVerbStrategyException| UserNotExistException e) {
            errors.add(e.getMessage());
            return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return handleExceptions(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    public ResponseEntity<Object> modifyUser(@RequestBody User user) {
        CampsiteRequestValidator requestValidator = new CampsitePutRequestValidatorImpl();
        List<String> errors = requestValidator.validate(user);
        try {
            if(errors.isEmpty()){
                User responseUser =  userManager.manage(VerbStrategy.PUT, user);
                return new ResponseEntity<>(responseUser, HttpStatus.OK);
            }else{
                return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
            }
        } catch (UnexpectedVerbStrategyException| UserNotExistException e) {
            errors.add(e.getMessage());
            return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return handleExceptions(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public ResponseEntity<Object> dropUser(@PathVariable long id) {
        CampsiteRequestValidator requestValidator = new CampsiteDeleteRequestValidatorImpl();
        User user = new User();
        user.setId(id);
        List<String> errors = requestValidator.validate(user);
        try {
            if(errors.isEmpty()){
                User responseUser =  userManager.manage(VerbStrategy.DELETE, user);
                return new ResponseEntity<>(responseUser, HttpStatus.OK);
            }else{
                return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
            }
        } catch (UnexpectedVerbStrategyException| UserNotExistException e) {
            errors.add(e.getMessage());
            return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return handleExceptions(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public ResponseEntity<Object> getUser(@PathVariable long id) {
        CampsiteRequestValidator requestValidator = new CampsiteGetRequestValidatorImpl();
        User user = new User();
        user.setId(id);
        List<String> errors = requestValidator.validate(user);
        try {
            if(errors.isEmpty()){
                User responseUser =  userManager.manage(VerbStrategy.GET, user);
                return new ResponseEntity<>(responseUser, HttpStatus.OK);
            }else{
                return handleErors(Messages.BUSINESS_ERROR,errors, HttpStatus.BAD_REQUEST);
            }
        } catch (UnexpectedVerbStrategyException| UserNotExistException e) {
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
