import com.vattenfall.model.Reservation;
import com.vattenfall.model.User;
import com.vattenfall.utils.ReservationByDateComparator;
import org.joda.time.DateTime;
import org.junit.Test;
import java.util.Collections.*;

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
    public void reservationSorting() {
        Reservation res1 = new Reservation();
        res1.setDate(date1);
        Reservation res2 = new Reservation();
        res2.setDate(date2);
        List<Reservation> list = new ArrayList<Reservation>();
        list.add(res2);
        list.add(res1);
        Collections.sort(list, new ReservationByDateComparator());

        assertTrue(list.get(0).equals(res1));
    }

}
