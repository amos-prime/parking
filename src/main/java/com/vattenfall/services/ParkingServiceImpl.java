package com.vattenfall.services;

import com.vattenfall.model.Reservation;
import com.vattenfall.model.User;
import com.vattenfall.model.UserStatus;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by amoss on 30.01.14.
 */
@Service(value = "parkingService")
@Transactional
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Override
    public List<User> getAllHolders() {
        List<User> users = userService.findAll();
        List<User> holders = new ArrayList<User>();
        for(User user : users) {
            if(user.getStatus() == UserStatus.HOLDER) {
                holders.add(user);
            }
        }
        if(holders.size() == 0) {
            initData();
            holders = getAllHolders();
        }
        return holders;
    }

    public void initData() {
        for (int i = 1; i < 5; i++) {
            User user = new User();
            user.setUsername("User" + i);
            user.setPassword("pass");
            user.setStatus(UserStatus.HOLDER);
            userService.create(user);
        }
    }

}
