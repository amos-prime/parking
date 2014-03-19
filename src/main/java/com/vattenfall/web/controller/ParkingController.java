package com.vattenfall.web.controller;

import com.vattenfall.model.ParkingDay;
import com.vattenfall.model.User;
import com.vattenfall.services.ParkingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by amoss on 06.02.14.
 */
@Controller
public class ParkingController {

    private static final Logger logger = Logger.getLogger(ParkingController.class);

    @Autowired
    private ParkingService parkingService;

    @RequestMapping(value = "/parking", method = RequestMethod.GET)
    public String showParking(ModelMap model) {
        List<User> holders = parkingService.getAllHolders();
        HashMap<User, Set<ParkingDay>> holdersAndDays = new HashMap<User, Set<ParkingDay>>();
        for (User holder : holders) {
            holdersAndDays.put(holder, holder.getParkingDays());
        }
        model.addAttribute("map", holdersAndDays);
        return "ParkingView";
    }

    @RequestMapping("/holders")
    @ResponseBody
    public List<User> showHolders() {
        return parkingService.getAllHolders();
    }

    @RequestMapping("/welcome")
    @ExceptionHandler({Exception.class})
    public String welcome(ModelMap model) {
        logger.warn("/welcome");
        model.addAttribute("message", "hello from welcome method!");
        return "Welcome";
    }

}
