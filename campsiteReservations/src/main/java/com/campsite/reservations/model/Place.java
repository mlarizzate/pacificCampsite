package com.campsite.reservations.model;

import javax.persistence.*;

@Entity
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "place_position")
    private String placePosition;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPlacePosition(String placePosition) {
        this.placePosition = placePosition;
    }

    public String getPlacePosition() {
        return placePosition;
    }
}
