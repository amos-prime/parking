package com.vattenfall.repository;

import com.vattenfall.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by amoss on 29.01.14.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
