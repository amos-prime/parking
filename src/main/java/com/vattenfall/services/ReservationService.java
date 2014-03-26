package com.vattenfall.services;

import com.vattenfall.model.Reservation;

import java.util.List;

/**
 * Created by amoss on 29.01.14.
 */
public interface ReservationService {

    public Reservation create(Reservation reservation);
    public void delete(long id) throws Exception;
    public List<Reservation> findAll();
    public Reservation update(Reservation reservation) throws Exception;
    public Reservation findById(long id) throws Exception;
}
