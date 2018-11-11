package com.campsite.reservations.server;

import com.campsite.reservations.controller.MainController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {

    // inject via application.properties
    @Value("${campsite.module.name}")
    private String moduleName;


    @Bean
    public String getModuleName(){
        return this.moduleName;
    }

    @Bean
    public MainController getMainController(){
        return new MainController();
    }
}
