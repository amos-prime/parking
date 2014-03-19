package com.vattenfall.services;

import com.vattenfall.model.User;

import java.util.List;

/**
 * Created by amoss on 30.01.14.
 */
public interface ParkingService {
    public List<User> getAllHolders();
    public void initData();
}
