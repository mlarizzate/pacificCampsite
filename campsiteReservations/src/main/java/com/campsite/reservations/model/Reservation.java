package com.campsite.reservations.model;

import javax.persistence.*;
import java.util.Date;

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "reservation_id")
    private String reservationId;

    @Column(name = "date")
    private Date date;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="place_id")
    private Place place;
}
