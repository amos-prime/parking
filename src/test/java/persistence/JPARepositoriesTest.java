package persistence;

import com.vattenfall.configuration.PersistenceConfig;
import com.vattenfall.configuration.ServicesConfig;
import com.vattenfall.model.DayState;
import com.vattenfall.model.ParkingDay;
import com.vattenfall.model.User;
import com.vattenfall.model.UserStatus;
import com.vattenfall.repository.ParkingDayRepository;
import com.vattenfall.repository.UserRepository;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.util.List;

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
    private ParkingDayRepository parkingDayRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void integrationTest() {
        assertTrue(parkingDayRepository != null);
    }

    @Test
    public void parkingDayRepositoryCreate() {
        ParkingDay day = new ParkingDay();
        day.setDate(new DateTime());
        day.setDayState(DayState.FREE);
        parkingDayRepository.save(day);
    }

    @Test
    public void parkingDayRepositoryCreate2() {
        ParkingDay day = new ParkingDay();
        day.setDate(new DateTime());
        day.setDayState(DayState.FREE);

        User user = new User();
        user.setUsername("userName");
        user.setPassword("pass");
        user.setStatus(UserStatus.HOLDER);
        user = userRepository.save(user);

        day.setTempHolder(user);
        day = parkingDayRepository.save(day);
        assertTrue(day.getId() != 0);
        assertTrue(day.getTempHolder().getId() != 0);
    }

    @Test
    public void parkingDayRepositoryDelete() {
        ParkingDay day = new ParkingDay();
        day.setDate(DateTime.now());
        day.setDayState(DayState.FREE);
        day = parkingDayRepository.save(day);
        long id = day.getId();
        try {
            parkingDayRepository.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
        try {
            day = parkingDayRepository.findOne(id);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void parkingDayRepositoryFindAll() {
        ParkingDay day = new ParkingDay();
        day.setDate(DateTime.now());
        day.setDayState(DayState.FREE);
        day = parkingDayRepository.save(day);
        ParkingDay day2 = new ParkingDay();
        day2.setDate(DateTime.now());
        day2.setDayState(DayState.FREEDAY);
        day2 = parkingDayRepository.save(day2);

        List<ParkingDay> days = parkingDayRepository.findAll();
        assertTrue(days.size() == 2);
    }

    @Test
    public void parkingDayRepositoryFindAllFast() {
        List<ParkingDay> days = parkingDayRepository.findAll();
    }

    @Test
    public void parkingDayRepositoryFindById() {
        ParkingDay day = new ParkingDay();
        day.setDate(DateTime.now());
        day.setDayState(DayState.FREE);
        day = parkingDayRepository.save(day);

        ParkingDay fetchedDay;
        fetchedDay = parkingDayRepository.findOne(day.getId());

        assertTrue(fetchedDay != null);
        assertTrue(day.getId() == fetchedDay.getId());
        assertTrue(day.getDayState() == fetchedDay.getDayState());
    }

    @Test
    public void rollbackTest() {
        List<ParkingDay> days = parkingDayRepository.findAll();
        assertTrue(days.size() == 0);
    }

    @Test
    public void  eagerFetching() {
        User holder = new User();
        holder.setUsername("holder");
        holder.setPassword("123");
        holder = userRepository.save(holder);
        User tmpHolder = new User();
        tmpHolder.setUsername("tmpHolder");
        tmpHolder.setPassword("123");
        tmpHolder = userRepository.save(tmpHolder);

        ParkingDay day = new ParkingDay();
        day.setDayState(DayState.HOLDED);
        day.setHolder(holder);
        day.setTempHolder(tmpHolder);
        day = parkingDayRepository.save(day);
        try {
            day = parkingDayRepository.findOne(day.getId());
            assertTrue(day.getHolder().getId() == holder.getId());
            assertTrue(day.getTempHolder().getId() == tmpHolder.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
