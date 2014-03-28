package com.vattenfall.services;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
