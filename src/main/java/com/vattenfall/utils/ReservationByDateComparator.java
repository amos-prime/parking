package com.vattenfall.utils;

import com.vattenfall.model.Reservation;

import java.util.Comparator;

/**
 * Created by amoss on 28.03.14.
 */
public class ReservationByDateComparator implements Comparator<Reservation> {
    @Override
    public int compare(Reservation o1, Reservation o2) {
        return (o1.getDate().getMillis() > o2.getDate().getMillis()) ? 1
                : (o1.getDate().getMillis() < o2.getDate().getMillis()) ? -1
                : 0;
    }
}
