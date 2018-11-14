package com.campsite.users.server;

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



}
