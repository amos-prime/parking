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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.TreeSet;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by amoss on 18.03.14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, ServicesConfig.class})
//@Transactional
//@TransactionConfiguration(defaultRollback = false)
public class InsertExampleData {

    @Autowired
    private ParkingDayRepository parkingDayRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Ignore
    public void insertExampleData() {
        for (int i = 1; i < 5; i++) {
            TreeSet<ParkingDay> days = new TreeSet<ParkingDay>();
            for (int d = 1; d < 30; d++) {
                ParkingDay day = new ParkingDay();
                day.setDate(new DateTime().withDate(2014, 3, d));
                day.setDayState(DayState.HOLDED);
                day = parkingDayRepository.save(day);
                days.add(day);
            }
            User user = new User();
            user.setUsername("User" + i);
            user.setPassword("pass");
            user.setStatus(UserStatus.HOLDER);
            user.setParkingDays(days);
            user = userRepository.save(user);
        }
    }
}
