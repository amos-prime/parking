package com.vattenfall.services;

import com.vattenfall.dto.ReservationDto;
import com.vattenfall.dto.UserDto;
import com.vattenfall.model.User;
import java.util.List;

/**
 * Created by amoss on 26.03.14.
 */
public interface ServiceFacade {

    public List<UserDto> getUsers();
    public List<ReservationDto> getReservations();
    public void releaseReservation(Long resId);



}
