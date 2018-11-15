package com.campsite.reservations.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "reservation_code")
    private String reservationCode;

    @Column(name = "user_id")
    private long userId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="place_id")
    private Place place;

    @Column(name = "date_from")
    private Date dateFrom;

    @Column(name = "date_to")
    private Date dateTo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(getPlace(), that.getPlace()) &&
                Objects.equals(getDateFrom(), that.getDateFrom()) &&
                Objects.equals(getDateTo(), that.getDateTo());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPlace(), getDateFrom(), getDateTo());
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationCode='" + reservationCode + '\'' +
                ", userId=" + userId +
                ", place=" + place +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }

}
