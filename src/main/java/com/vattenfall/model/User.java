package com.vattenfall.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by amoss on 20.12.13.
 */
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private long id;

   // @OneToMany(fetch = FetchType.EAGER, mappedBy = "holder")
   // private Set<Reservation> reservations = new HashSet<Reservation>(0);

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

    /*public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }*/

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

 /*   public void addParkingDay(Reservation day) {
        reservations.add(day);
    }*/
}
