package com.vattenfall.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created by amoss on 20.12.13.
 */
@Entity
@Table(name = "Parking_Days")
public class ParkingDay implements Comparable<ParkingDay> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User holder;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="tempHolder_id")
    private User tempHolder;

    @Enumerated(EnumType.STRING)
    private DayState dayState;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime date;

    private int placeNumber;

    public ParkingDay() {
    }

    //Getters & Setters
    public long getId() {
        return id;
    }

    public User getTempHolder() {
        return tempHolder;
    }

    public void setTempHolder(User tempHolder) {
        this.tempHolder = tempHolder;
    }

    public DayState getDayState() {
        return dayState;
    }

    public void setDayState(DayState dayState) {
        this.dayState = dayState;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    @Override
    public int compareTo(ParkingDay thatParkingDay) {
        return this.date.compareTo(thatParkingDay.date);
    }

    public User getHolder() {
        return holder;
    }

    public void setHolder(User holder) {
        this.holder = holder;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }


}
