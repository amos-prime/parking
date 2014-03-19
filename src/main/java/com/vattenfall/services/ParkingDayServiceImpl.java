package com.vattenfall.services;

import com.vattenfall.model.ParkingDay;
import com.vattenfall.repository.ParkingDayRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by amoss on 29.01.14.
 */
@Service(value = "parkingDayService")
@Transactional
public class ParkingDayServiceImpl implements ParkingDayService {

    @Resource
    private ParkingDayRepository parkingDayRepository;

    @Override
    @Transactional
    public ParkingDay create(ParkingDay parkingDay) {
        return parkingDayRepository.save(parkingDay);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(long id) throws Exception {
        ParkingDay dayForDelete = parkingDayRepository.findOne(id);
        parkingDayRepository.delete(dayForDelete);
    }

    @Override
    @Transactional
    public List<ParkingDay> findAll() {
        return (List) parkingDayRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkingDay update(ParkingDay parkingDay) throws Exception {
        ParkingDay dayToBeUpdated = parkingDayRepository.findOne(parkingDay.getId());
        dayToBeUpdated = parkingDay;
        return parkingDayRepository.save(dayToBeUpdated);
    }

    @Override
    @Transactional
    public ParkingDay findById(long id) throws Exception {
        return parkingDayRepository.findOne(id);
    }

}
