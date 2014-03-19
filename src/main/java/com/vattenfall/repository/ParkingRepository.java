package com.vattenfall.repository;

import com.vattenfall.model.Parking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User: smotynga
 */
@Repository
public interface ParkingRepository extends CrudRepository<Parking, Long> {
}
