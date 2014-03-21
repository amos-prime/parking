package com.vattenfall.services;

import org.springframework.stereotype.Service;

@Service("sampleService")
public class SampleServiceImpl implements SampleService {

    @Override
    public String sayHello() {
        return "Stay strong - Spring is comming";
    }

}
