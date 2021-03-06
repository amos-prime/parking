package com.vattenfall.web.controller;

import com.vattenfall.model.User;
import com.vattenfall.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by amoss on 06.02.14.
 */
@Controller
public class ParkingController {

    private static final Logger logger = Logger.getLogger(ParkingController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/parking", method = RequestMethod.GET)
    public String showParking(ModelMap model) {
        List<User> holders = userService.findAll();
        model.addAttribute("holders", holders);
        return "ParkingView";
    }

    @RequestMapping("/holders")
    @ResponseBody
    public List<User> showHolders() {
        return userService.findAll();
    }

    @RequestMapping("/welcome")
    @ExceptionHandler({Exception.class})
    public String welcome(ModelMap model) {
        logger.warn("/welcome");
        model.addAttribute("message", "hello from welcome method!");
        return "Welcome";
    }

}
