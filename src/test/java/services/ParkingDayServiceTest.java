package services;

import com.vattenfall.configuration.ServicesConfig;
import com.vattenfall.configuration.PersistenceConfig;
import com.vattenfall.model.DayState;
import com.vattenfall.model.ParkingDay;
import com.vattenfall.model.User;
import com.vattenfall.model.UserStatus;
import com.vattenfall.services.ParkingDayService;
import com.vattenfall.services.UserService;
import junit.framework.Assert;
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
public class ParkingDayServiceTest {

    @Autowired
    @Qualifier(value = "parkingDayService")
    private ParkingDayService  parkingDayService;
    @Autowired
    @Qualifier(value = "userService")
    private UserService userService;
    @Autowired
    @Qualifier(value = "dataSource")
    private DataSource dataSource;

    @Test
    public void integrationTest() {
        assertTrue(parkingDayService != null);
        assertTrue(userService != null);
        assertTrue(dataSource != null);
    }

    @Test
    public void parkingDayServiceBeanCreation() {
        assertTrue(parkingDayService != null);
    }

    @Test
    public void parkingDayServiceCreate() {
        ParkingDay day = new ParkingDay();
        day.setDate(new DateTime());
        day.setDayState(DayState.FREE);

        User user = new User();
        user.setUsername("userName");
        user.setPassword("pass");
        user.setStatus(UserStatus.HOLDER);
        user = userService.create(user);

        day.setTempHolder(user);
        day = parkingDayService.create(day);
        assertTrue(day.getId() != 0);
        assertTrue(day.getTempHolder().getId() != 0);
    }

    @Test
    public void parkingDayServiceDelete() {
        ParkingDay day = new ParkingDay();
        day.setDate(DateTime.now());
        day.setDayState(DayState.FREE);
        day = parkingDayService.create(day);
        long id = day.getId();
        try {
            parkingDayService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
        try {
            day = parkingDayService.findById(id);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void parkingDayServiceFindAll() {
        ParkingDay day = new ParkingDay();
        day.setDate(DateTime.now());
        day.setDayState(DayState.FREE);
        day = parkingDayService.create(day);
        ParkingDay day2 = new ParkingDay();
        day2.setDate(DateTime.now());
        day2.setDayState(DayState.FREEDAY);
        day2 = parkingDayService.create(day2);

        List<ParkingDay> days = parkingDayService.findAll();
        assertTrue(days.size() == 2);
    }

    @Test
    public void parkingDayServiceFindAllFast() {
        List<ParkingDay> days = parkingDayService.findAll();
    }

    @Test
    public void parkingDayServiceFindById() {
        ParkingDay day = new ParkingDay();
        day.setDate(DateTime.now());
        day.setDayState(DayState.FREE);
        day = parkingDayService.create(day);

        ParkingDay fetchedDay = null;
        try {
            fetchedDay = parkingDayService.findById(day.getId());
        } catch (Exception e) {
            assertTrue(false);
        }
        assertTrue(fetchedDay != null);
        assertTrue(day.getId() == fetchedDay.getId());
        assertTrue(day.getDayState() == fetchedDay.getDayState());
    }

    @Test
    public void rollbackTest() {
        List<ParkingDay> days = parkingDayService.findAll();
        assertTrue(days.size() == 0);
    }

    @Test
    public void  eagerFetching() {
        User holder = new User();
        holder.setUsername("holder");
        holder.setPassword("123");
        holder = userService.create(holder);
        User tmpHolder = new User();
        tmpHolder.setUsername("tmpHolder");
        tmpHolder.setPassword("123");
        tmpHolder = userService.create(tmpHolder);

        ParkingDay day = new ParkingDay();
        day.setDayState(DayState.HOLDED);
        day.setHolder(holder);
        day.setTempHolder(tmpHolder);
        day = parkingDayService.create(day);
        try {
            day = parkingDayService.findById(day.getId());
            assertTrue(day.getHolder().getId() == holder.getId());
            assertTrue(day.getTempHolder().getId() == tmpHolder.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
