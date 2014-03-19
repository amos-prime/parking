package com.vattenfall.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by amoss on 27.02.14.
 */
@Configuration
@ComponentScan(basePackages = {"com.vattenfall.services"})
public class ServicesConfig {
}
