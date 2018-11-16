package com.campsite.reservations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "places")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Place implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Place place = (Place) o;
        return getId() == place.getId();
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", placePosition='" + placePosition + '\'' +
                '}';
    }
}
