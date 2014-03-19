package com.vattenfall.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by amoss on 18.02.14.
 */
@Controller
public class SecurityController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "Login";
    }
}
