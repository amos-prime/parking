package com.vattenfall.repository;

import com.vattenfall.model.ParkingDay;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by amoss on 29.01.14.
 */
public interface ParkingDayRepository extends JpaRepository<ParkingDay, Long> {
}
