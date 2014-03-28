package com.vattenfall.services;

import com.vattenfall.model.Reservation;
import com.vattenfall.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by amoss on 29.01.14.
 */
@Service(value = "reservationService")
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Resource
    private ReservationRepository reservationRepository;

    @Override
    @Transactional
    public Reservation create(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(long id) {
        Reservation dayForDelete = reservationRepository.findOne(id);
        reservationRepository.delete(dayForDelete);
    }

    @Override
    @Transactional
    public List<Reservation> findAll() {
        return (List) reservationRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Reservation update(Reservation reservation) {
        Reservation dayToBeUpdated = reservationRepository.findOne(reservation.getId());
        dayToBeUpdated = reservation;
        return reservationRepository.save(dayToBeUpdated);
    }

    @Override
    @Transactional
    public Reservation findById(long id) {
        return reservationRepository.findOne(id);
    }

}
