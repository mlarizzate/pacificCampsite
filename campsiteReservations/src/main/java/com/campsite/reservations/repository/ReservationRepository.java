package com.campsite.reservations.repository;

import com.campsite.reservations.model.Place;
import com.campsite.reservations.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Reservation findById(Long id);

    Reservation findByPlaceAndDateFromAndDateTo(Place place, Date dateFrom, Date dateTo);


    @Query(
            value = "select * FROM Reservations res "+
            "where  ((res.date_from >= ?1 || res.date_from <= ?2) "+
             "|| (res.date_to >= ?1 || res.date_to <= ?2))",
            nativeQuery = true)
    List<Reservation> findOverlapings(Date dateFrom, Date dateTo);

    @Query(
            value = "select * FROM Reservations res "+
                    "where  res.placeId = ?3 " +
                    "AND ((res.dateFrom = null || ?2 = null || res.dateFrom <= ?2) "+
                    "AND (?1 = null || res.dateTo = null || ?1 <= res.dateTo) "+
                    "AND (res.dateFrom = null || res.dateTo = null || res.dateFrom <= res.dateTo) "+
                    "AND (?1 = null || ?2 = null || ?1 <= ?2))",
            nativeQuery = true)
    List<Reservation> findOverlapingsByPlace(Date dateFrom, Date dateTo, long placeId);
}
