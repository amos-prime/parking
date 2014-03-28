import com.vattenfall.model.Reservation;
import com.vattenfall.model.User;
import org.joda.time.DateTime;
import org.junit.Test;


import java.util.*;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by amoss on 23.12.13.
 */
public class ReservationTest {

    DateTime date1 = new DateTime().withDate(2013, 12, 19);
    DateTime date2 = new DateTime().withDate(2013, 12, 20);
    User user = new User();

    @Test
    public void ParkingDayCompareToTest1() {
        Reservation res1 = new Reservation();
        res1.setDate(date1);
        Reservation res2 = new Reservation();
        res2.setDate(date2);

        int i = res1.compareTo(res2);
        assertTrue(i<0);
    }

    @Test
    public void ParkingDayCompareToTest2() {
        Reservation res1 = new Reservation();
        res1.setDate(date2);
        Reservation res2 = new Reservation();
        res2.setDate(date1);
        int i = res1.compareTo(res2);
        assertTrue(i>0);
    }

    @Test
    public void ParkingDayCompareToTest3() {
        Reservation res1 = new Reservation();
        res1.setDate(date1);
        Reservation res2 = new Reservation();
        res2.setDate(date1);
        int i = res1.compareTo(res2);
        assertTrue(i==0);
    }



}
