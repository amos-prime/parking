package services;

import com.vattenfall.configuration.ServicesConfig;
import com.vattenfall.configuration.PersistenceConfig;
import com.vattenfall.model.Reservation;
import com.vattenfall.model.User;
import com.vattenfall.model.UserStatus;
import com.vattenfall.services.ReservationService;
import com.vattenfall.services.UserService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by amoss on 29.01.14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, ServicesConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ReservationServiceTest {

    @Autowired
    @Qualifier(value = "reservationService")
    private ReservationService reservationService;
    @Autowired
    @Qualifier(value = "userService")
    private UserService userService;
    @Autowired
    @Qualifier(value = "dataSource")
    private DataSource dataSource;

    DateTime date = new DateTime().withDate(2014, 1, 1);
    DateTime anotherDate = new DateTime().withDate(2014, 1, 2);

    @Test
    public void integrationTest() {
        assertTrue(reservationService != null);
        assertTrue(userService != null);
        assertTrue(dataSource != null);
    }

    @Test
    public void reservationServiceCreate() {
        Reservation res = getTestReservation();
        assertTrue(res.getId() != 0);
    }

    @Test
    public void reservationServiceDelete() {
        Reservation res = getTestReservation();
        long id = res.getId();
        try {
            reservationService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
        try {
            res = reservationService.findById(id);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void reservationServiceFindAll() {
        Reservation res = getTestReservation();
        Reservation res2 = new Reservation(anotherDate, getUser());
        reservationService.create(res2);

        List<Reservation> reservations = reservationService.findAll();
        assertTrue(reservations.size() == 2);
    }

    @Test
    public void reservationServiceFindAllFast() {
        List<Reservation> res = reservationService.findAll();
    }

    @Test
    public void reservationServiceFindById() {
        Reservation res = getTestReservation();
        Reservation fetchedRes = null;
        try {
            fetchedRes = reservationService.findById(res.getId());
        } catch (Exception e) {
            assertTrue(false);
        }
        assertTrue(fetchedRes != null);
        assertTrue(res.getId() == fetchedRes.getId());
    }

    @Test
    public void rollbackTest() {
        List<Reservation> res = reservationService.findAll();
        assertTrue(res.size() == 0);
    }

    private Reservation getTestReservation(){
        return reservationService.create(new Reservation(date, getUser()));
    }

    private User getUser() {
        User user = new User();
        user.setUsername("UserName");
        user.setPassword("pass");
        user.setStatus(UserStatus.HOLDER);
        return userService.create(user);
    }
}
