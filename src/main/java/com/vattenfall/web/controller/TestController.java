package com.vattenfall.web.controller;

import com.vattenfall.model.ParkingDay;
import com.vattenfall.model.User;
import com.vattenfall.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Created by amoss on 20.03.14.
 */
@Controller
public class TestController {
    @Autowired
    private ParkingService parkingService;

    @RequestMapping(value = "/testparking", method = RequestMethod.GET)
    public String showParking(ModelMap model) {
        List<User> holders = parkingService.getAllHolders();
        Map<User, Set<ParkingDay>> holdersAndDays = new LinkedHashMap<User, Set<ParkingDay>>();
        for (User holder : holders) {
            holdersAndDays.put(holder, holder.getParkingDays());
        }
        Map<Integer, String> dates = new LinkedHashMap<Integer, String>();
        if(holders.size() > 0) {
            User holder = holders.get(0);
            SortedSet<ParkingDay> days = holder.getParkingDays();
            for (ParkingDay day : days) {
                int dayNumber = day.getDate().dayOfMonth().get();
                String dayName;
                int i = day.getDate().getDayOfWeek();
                switch (i) {
                    case 1:  dayName = "Monday";
                        break;
                    case 2:  dayName = "Tueseday";
                        break;
                    case 3:  dayName = "Wednesday";
                        break;
                    case 4:  dayName = "Thursday";
                        break;
                    case 5:  dayName = "Friday";
                        break;
                    case 6:  dayName = "Saturday";
                        break;
                    case 7:  dayName = "Sunday";
                        break;
                    default: dayName = "Unknown day";
                        break;
                }
                dates.put(dayNumber, dayName);
            }
        }

        model.addAttribute("map", holdersAndDays);
        model.addAttribute("dates", dates);
        return "TestParkingView";
    }
}
