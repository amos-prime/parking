import com.vattenfall.model.ParkingDay;
import org.joda.time.DateTime;
import org.junit.Test;


import java.util.*;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by amoss on 23.12.13.
 */
public class ParkingDayTest {
    @Test
    public void ParkingDayCompareToTest1() {
        ParkingDay day1 = new ParkingDay();
        day1.setDate(new DateTime().withDate(2013, 12, 19));
        ParkingDay day2 = new ParkingDay();
        day2.setDate(new DateTime().withDate(2013, 12, 20));
        int i = day1.compareTo(day2);
        assertTrue(i<0);
    }

    @Test
    public void ParkingDayCompareToTest2() {
        ParkingDay day1 = new ParkingDay();
        day1.setDate(new DateTime().withDate(2013, 12, 20));
        ParkingDay day2 = new ParkingDay();
        day2.setDate(new DateTime().withDate(2013, 12, 19));
        int i = day1.compareTo(day2);
        assertTrue(i>0);
    }

    @Test
    public void ParkingDayCompareToTest3() {
        ParkingDay day1 = new ParkingDay();
        day1.setDate(new DateTime().withDate(2013, 12, 20));
        ParkingDay day2 = new ParkingDay();
        day2.setDate(new DateTime().withDate(2013, 12, 20));
        int i = day1.compareTo(day2);
        assertTrue(i==0);
    }

    @Test
    public void ParkingDaySortedSetTest() {
        SortedSet<ParkingDay> parkingDays = new TreeSet<ParkingDay>();
        ParkingDay day1 = new ParkingDay();
        day1.setDate(new DateTime().withDate(2013, 12, 20));
        ParkingDay day2 = new ParkingDay();
        day2.setDate(new DateTime().withDate(2013, 12, 19));
        ParkingDay day3 = new ParkingDay();
        day3.setDate(new DateTime().withDate(2013, 12, 22));
        parkingDays.add(day1);
        parkingDays.add(day2);
        parkingDays.add(day3);
        assertTrue(day2 == parkingDays.first());
    }


}
