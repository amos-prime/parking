package com.vattenfall.services;

import com.vattenfall.model.ParkingDay;

import java.util.List;

/**
 * Created by amoss on 29.01.14.
 */
public interface ParkingDayService {

    public ParkingDay create(ParkingDay parkingDay);
    public void delete(long id) throws Exception;
    public List<ParkingDay> findAll();
    public ParkingDay update(ParkingDay parkingDay) throws Exception;
    public ParkingDay findById(long id) throws Exception;
}
