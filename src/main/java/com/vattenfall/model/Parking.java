package com.vattenfall.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * User: smotynga
 */
@Entity
public class Parking {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    private String holder;
    private String tempHolder;
    private boolean booked;

    public long getId() {
        return id;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getTempHolder() {
        return tempHolder;
    }

    public void setTempHolder(String tempHolder) {
        this.tempHolder = tempHolder;
    }
}
