package com.vattenfall.data;

import com.vattenfall.model.Parking;
import com.vattenfall.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * User: smotynga
 */
@Component
public class DataUpdater {

    @Autowired
    private DataParser parser;

    @Autowired
    private ParkingRepository repository;

//    @Scheduled(cron= "${data.update.cron}")
    @Transactional
//    @PostConstruct
    public void update(){
        List<String> absentHolders = parser.collectAbsentHolders();

        for(Parking parking : repository.findAll()){
            if(parkingHolderIsAbsent(parking, absentHolders)){
                parking.setBooked(false);
                parking.setTempHolder("");
            }
        }
    }

    private boolean parkingHolderIsAbsent(Parking parking, List<String> absentHolders) {
        for(String name : absentHolders){
            if(name.equalsIgnoreCase(parking.getHolder())){
                return true;
            }
        }
        return false;
    }
}
