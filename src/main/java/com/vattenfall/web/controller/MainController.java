package com.vattenfall.web.controller;

import com.vattenfall.model.Parking;
import com.vattenfall.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * User: smotynga
 */
@Controller
public class MainController {

    private ArrayList<Parking> parkings;

    @Autowired
    private ParkingRepository repository;

    @RequestMapping("/data")
    @ResponseBody
    public List<Parking> data() {
        return (List<Parking>) repository.findAll();
    }

    @RequestMapping("/booking/{id}/{tmpholder}")
    @ResponseBody
    @Transactional //TODO SM: do it in some service class
    public void booking(@PathVariable("id") long id, @PathVariable("tmpholder") String tmpHolder) {
        Parking one = repository.findOne(id);
        one.setTempHolder(tmpHolder);
        one.setBooked(true);
    }

}
