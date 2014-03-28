package com.vattenfall.services;

import com.vattenfall.model.Reservation;

import java.util.List;

/**
 * Created by amoss on 29.01.14.
 */
public interface ReservationService {

    public Reservation create(Reservation reservation);
    public void delete(long id);
    public List<Reservation> findAll();
    public Reservation update(Reservation reservation);
    public Reservation findById(long id);
}
