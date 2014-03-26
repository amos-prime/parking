package services;

import com.vattenfall.configuration.PersistenceConfig;
import com.vattenfall.configuration.ServicesConfig;
import com.vattenfall.exceptions.UserNotFound;
import com.vattenfall.model.User;
import com.vattenfall.model.UserStatus;
import com.vattenfall.services.ReservationService;
import com.vattenfall.services.UserService;
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
    ReservationService reservationService;
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

}

