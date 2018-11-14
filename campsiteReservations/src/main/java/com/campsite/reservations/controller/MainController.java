package com.campsite.reservations.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = {""})
public class MainController {

    @Autowired
    private String moduleName;

    @Autowired
    DataSource dataSource;

    public MainController() {

    }

    /**
     * Shows statistic on an easy way.
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.GET, value = "/status")
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

}
