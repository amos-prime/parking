package com.vattenfall.dto;

import org.joda.time.DateTime;

/**
 * Created by amoss on 27.03.14.
 */
public class ReservationDto {
    private Long id;
    private DateTime date;
    private UserDto holder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public UserDto getHolder() {
        return holder;
    }

    public void setHolder(UserDto holder) {
        this.holder = holder;
    }
}
