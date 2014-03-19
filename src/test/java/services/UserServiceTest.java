package services;

import com.vattenfall.configuration.ServicesConfig;
import com.vattenfall.configuration.PersistenceConfig;
import com.vattenfall.exceptions.UserNotFound;
import com.vattenfall.model.DayState;
import com.vattenfall.model.ParkingDay;
import com.vattenfall.model.User;
import com.vattenfall.model.UserStatus;
import com.vattenfall.services.ParkingDayService;
import com.vattenfall.services.UserService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by amoss on 23.01.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, ServicesConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
//@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    ParkingDayService parkingDayService;
    User USER;

    public void before() {
        User user1 = new User();
        user1.setUsername("User1");
        user1.setPassword("password1");
        user1.setStatus(UserStatus.REGULAR);
        User user2 = new User();
        user2.setUsername("User2");
        user2.setPassword("password2");
        user2.setStatus(UserStatus.HOLDER);
        userService.create(user1);
        userService.create(user2);

        List<User> users = userService.findAll();
        USER = users.get(0);
        if (USER == null) {
            throw new NullPointerException("Test initialisation failure");
        }
    }

    public void clear() {
        List<User> users = userService.findAll();
            for (User user : users) {
                try {
                    userService.delete(user.getId());
                } catch (UserNotFound userNotFound) {
                    userNotFound.printStackTrace();
                }
            }
        USER = null;
    }

    @Test
    public void userServiceBeanCreation() {
        assertTrue(userService != null);
    }

    @Test
    @Rollback(value = true)
    public void userServiceSave() {
        User user = new User();
        user.setUsername("TestUsername");
        user.setPassword("password");
        user.setPoints(10);
        user.setStatus(UserStatus.REGULAR);
        User savedUser = userService.create(user);

        User checkUser = null;
        try {
            checkUser = userService.findById(savedUser.getId());
        } catch (UserNotFound userNotFound) {
            assertTrue(false);
        }
        assertTrue(savedUser.getId() == checkUser.getId());
        assertTrue(savedUser.getUsername().equals(checkUser.getUsername()));
        assertTrue(savedUser.getUsername().equals("TestUsername"));
    }

    @Test
    public void userServiceDelete() {
        before();
        try {
            userService.delete(USER.getId());
        } catch (UserNotFound userNotFound) {
            assertTrue(false);
        }
        try {
            userService.delete(USER.getId());
        } catch (UserNotFound userNotFound) {
            assertTrue(true);
        }
    }

    @Test
    public void userServiceDelete2() {
        before();
        long userId = USER.getId();
        try {
            userService.delete(userId);
        } catch (UserNotFound userNotFound) {
            assertTrue(false);
        }
        try {
            userService.findById(userId);
        } catch (UserNotFound userNotFound) {
            assertTrue(true);
        }
    }

    @Test
    public void userServiceFindById() {
        before();
        try {
            userService.findById(USER.getId());
        } catch (UserNotFound userNotFound) {
            assertTrue(false);
        }
    }

    @Test
    public void userServiceFindAll() {
        before();
        List<User> users = userService.findAll();
        assertTrue(users.size() == 2);
    }

    @Test(expected = UserNotFound.class)
    public void userServiceUpdatingUnsavedUser() throws UserNotFound {
        User user = new User();
        user.setUsername("UpdateUser");
        user.setPassword("pass");
        userService.update(user);
    }

    @Test
    public void userServiceUpdate() {
        before();
        String newUsername = "UPDATEDUSER";
        try {
            USER.setUsername(newUsername);
            User updatedUser = userService.update(USER);
            assertTrue(newUsername.equals(updatedUser.getUsername()));
        } catch (UserNotFound userNotFound) {
            assertTrue(false);
        }
        try {
            User user = userService.findById(USER.getId());
            assertTrue(newUsername.equals(user.getUsername()));
            assertTrue(USER.getId() == user.getId());
        } catch (UserNotFound userNotFound) {
            assertTrue(false);
        }
    }

    @Test
    public void userStatusTypeCorrect() {
        User user = new User();
        user.setUsername("User");
        user.setPassword("pass");
        user.setStatus(UserStatus.HOLDER);
        user = userService.create(user);
        try {
            user = userService.findById(user.getId());
            assertTrue(user.getStatus() == UserStatus.HOLDER);
        } catch (UserNotFound userNotFound) {
            userNotFound.printStackTrace();
        }
    }

    @Test
    public void fetchingUserEager() {
        User tmpHolder = new User();
        tmpHolder.setUsername("tmpHolder");
        tmpHolder.setPassword("123");
        tmpHolder.setStatus(UserStatus.REGULAR);
        tmpHolder = userService.create(tmpHolder);

        ParkingDay day1 = new ParkingDay();
        day1.setDate(new DateTime().withDate(2014, 01, 02));
        day1.setDayState(DayState.HOLDED);
        ParkingDay day2 = new ParkingDay();
        day2.setDate(new DateTime().withDate(2014, 01, 01));
        day2.setDayState(DayState.HOLDED);
        day2.setTempHolder(tmpHolder);
        parkingDayService.create(day1);
        parkingDayService.create(day2);

        User user = new User();
        user.setUsername("EagerUser");
        user.setPassword("ppp");
        user.addParkingDay(day1);
        user.addParkingDay(day2);
        user = userService.create(user);
        long userId = user.getId();

        user = null;
        try {
            user = userService.findById(userId);
        } catch (UserNotFound userNotFound) {
            userNotFound.printStackTrace();
        }
        assertTrue(user.getParkingDays().size() == 2);
        assertTrue(user.getParkingDays().first().getTempHolder().getUsername().equals("tmpHolder"));
    }

}
