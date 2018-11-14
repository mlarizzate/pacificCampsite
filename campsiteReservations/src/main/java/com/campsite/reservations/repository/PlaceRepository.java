package com.campsite.reservations.repository;

import com.campsite.reservations.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Long> {
    Place findById(Long id);
    Place findByPlacePosition(String placePosition);
}
