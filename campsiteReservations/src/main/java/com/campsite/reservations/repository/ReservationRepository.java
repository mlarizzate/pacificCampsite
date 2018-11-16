package com.campsite.reservations.repository;

import com.campsite.reservations.model.Place;
import com.campsite.reservations.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Reservation findById(Long id);

    Reservation findByPlaceAndDateFromAndDateTo(Place place, Date dateFrom, Date dateTo);

    Collection<Reservation> findByDateToBetween(Date dateFrom, Date dateTo);
    Collection<Reservation> findByDateFromBetween(Date dateFrom, Date dateTo);

}
