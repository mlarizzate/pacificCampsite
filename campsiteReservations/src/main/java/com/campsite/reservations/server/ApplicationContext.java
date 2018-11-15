package com.campsite.reservations.server;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {

    // inject via application.properties
    @Value("${campsite.module.name}")
    private String moduleName;

    @Value("${campsite.reservations.rules.maxdays}")
    private Integer reservationMaxDays;

    @Value("${campsite.reservations.rules.mindays}")
    private Integer reservationMinDays;

    @Value("${campsite.reservations.rules.checkin.hours}")
    private String checkInHours;

    @Value("${campsite.reservations.rules.checkin.minutes}")
    private String checkInMinutes;

    @Value("${campsite.reservations.rules.checkout.hours}")
    private String checkOutHours;

    @Value("${campsite.reservations.rules.checkout.minutes}")
    private String checkOutMinutes;


    @Bean
    @Qualifier("moduleName")
    public String getModuleName(){
        return this.moduleName;
    }

    @Bean
    @Qualifier("reservationMaxDays")
    public Integer getReservationMaxDays(){
        return this.reservationMaxDays;
    }

    @Bean
    @Qualifier("reservationMinDays")
    public Integer getReservationMinDays() {
        return this.reservationMinDays;
    }

    @Bean
    @Qualifier("checkInHours")
    public String getCheckInHours(){
        return this.checkInHours;
    }


    @Bean
    @Qualifier("checkInMinutes")
    public String getCheckInMinutes(){
        return this.checkInMinutes;
    }


    @Bean
    @Qualifier("checkOutHours")
    public String getCheckOutHours(){
        return this.checkOutHours;
    }

    @Bean
    @Qualifier("checkOutMinutes")
    public String getCheckOutMinutes(){
        return this.checkOutMinutes;
    }
}
