package com.vattenfall.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by amoss on 20.12.13.
 */
@Entity
@Table(name = "RESERVATIONS")
public class Reservation implements Comparable<Reservation> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESERVATION_ID")
    private long id;

    @Column(name = "DATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @NotNull
    private User holder;

    public Reservation(DateTime date, User holder ) {
        this.date = date;
        this.holder = holder;
    }

    //Getters & Setters
    public long getId() {
        return id;
    }

    public User getHolder() {
        return holder;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    @Override
    public int compareTo(Reservation thatReservation) {
        return this.date.compareTo(thatReservation.date);
    }

}
