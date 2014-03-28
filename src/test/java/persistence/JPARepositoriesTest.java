package persistence;

import com.vattenfall.configuration.PersistenceConfig;
import com.vattenfall.configuration.ServicesConfig;
import com.vattenfall.model.Reservation;
import com.vattenfall.model.User;
import com.vattenfall.model.UserStatus;
import com.vattenfall.repository.ReservationRepository;
import com.vattenfall.repository.UserRepository;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by amoss on 11.03.14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, ServicesConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class JPARepositoriesTest {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    DateTime date = new DateTime().withDate(2014, 1, 1);

    @Test
    public void integrationTest() {
        assertTrue(reservationRepository != null);
    }

    @Test
    public void reservationRepositoryCreate() {
        Reservation res = saveReservation();
        assertTrue(res.getId() != 0);
    }

    @Test
    public void reservationRepositoryDelete() {
        Reservation res = saveReservation();
        long id = res.getId();
        reservationRepository.delete(id);
        res = reservationRepository.findOne(id);
        assertNull(res);
    }

    @Test
    public void reservationRepositoryFindAll() {
        Reservation res = saveReservation();
        List<Reservation> reservations = reservationRepository.findAll();
        assertTrue(reservations.size() == 1);
    }

    @Test
    public void reservationRepositoryFindById() {
        Reservation res = saveReservation();
        Reservation fetchedDay = reservationRepository.findOne(res.getId());
        assertTrue(fetchedDay != null);
        assertTrue(res.getId() == fetchedDay.getId());
    }

    @Test
    public void rollbackTest() {
        List<Reservation> reservations = reservationRepository.findAll();
        assertTrue(reservations.size() == 0);
    }

    @Test
    public void reservationEagerFetching() {
        Reservation res = saveReservation();
        Reservation fetchedRes = null;
        fetchedRes = reservationRepository.findOne(res.getId());
        assertTrue(res.getHolder().getId() == fetchedRes.getHolder().getId());
    }

    @Test
    public void userRepositorySave() {
        User user = new User();
        user.setUsername("Username");
        user.setPassword("Pass");
        userRepository.save(user);
    }

    private Reservation saveReservation() {
        Reservation res = new Reservation();
        res.setHolder(createUser());
        return reservationRepository.save(res);
    }

    private User createUser() {
        User user = new User();
        user.setUsername("UserName");
        user.setPassword("pass");
        user.setStatus(UserStatus.HOLDER);
        return userRepository.save(user);
    }

}
