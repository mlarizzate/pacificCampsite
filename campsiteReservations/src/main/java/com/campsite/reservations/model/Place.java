package com.campsite.reservations.model;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return getId() == place.getId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", placePosition='" + placePosition + '\'' +
                '}';
    }
}
