package com.vattenfall.services;

import com.vattenfall.dto.ReservationDto;
import com.vattenfall.dto.UserDto;
import com.vattenfall.model.Reservation;
import com.vattenfall.model.User;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amoss on 26.03.14.
 */
@Service("serviceFacade")
@Transactional
public class ServiceFacadeImpl implements ServiceFacade {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserService userService;
    @Autowired
    private Mapper mapper;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userService.findAll();
        List<UserDto> usersDto = new ArrayList<UserDto>(users.size());
        for (User user : users) {
            usersDto.add(mapper.map(user, UserDto.class));
        }
        return usersDto;
    }

    @Override
    public List<ReservationDto> getReservations() {
        List<Reservation> reservations = reservationService.findAll();
        List<ReservationDto> reservationsDto = new ArrayList<ReservationDto>(reservations.size());
        for (Reservation reservation : reservations) {
            reservationsDto.add(mapper.map(reservation, ReservationDto.class));
        }
        return reservationsDto;
    }

    @Override
    public void releaseReservation(Long resId) {
        Reservation res = reservationService.findById(resId);
        res.setHolder(null);
        reservationService.update(res);
    }

}
