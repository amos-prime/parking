package com.vattenfall.configuration;

import com.vattenfall.dto.ReservationDto;
import com.vattenfall.dto.UserDto;
import com.vattenfall.model.Reservation;
import com.vattenfall.model.User;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.dozer.loader.api.TypeMappingBuilder;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amoss on 27.02.14.
 */
@Configuration
@ComponentScan(basePackages = {"com.vattenfall.services"})
public class ServicesConfig {

    @Bean
    public Mapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<String> myMappingFiles = new ArrayList<String>();
        myMappingFiles.add("dozerBeanMapping.xml");
        mapper.setMappingFiles(myMappingFiles);

        return mapper;
    }



}
