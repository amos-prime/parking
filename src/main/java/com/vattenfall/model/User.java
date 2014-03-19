package com.vattenfall.model;


import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by amoss on 20.12.13.
 */
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    @OneToMany(mappedBy = "holder", fetch = FetchType.EAGER)
    @Sort(type = SortType.NATURAL)
    private SortedSet<ParkingDay> parkingDays = new TreeSet<ParkingDay>();

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String username;
    private String password;
    private int points;

    public User() {}

    //Getters & Setters
        public long getId() {
        return id;
    }

    public SortedSet<ParkingDay> getParkingDays() {
        return parkingDays;
    }

    public void setParkingDays(SortedSet<ParkingDay> parkingDays) {
        this.parkingDays = parkingDays;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addParkingDay(ParkingDay parkingDay) {
        parkingDays.add(parkingDay);
    }
}
