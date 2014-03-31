package services;

import com.vattenfall.configuration.PersistenceConfig;
import com.vattenfall.configuration.ServicesConfig;
import com.vattenfall.dto.ReservationDto;
import com.vattenfall.dto.UserDto;
import com.vattenfall.model.Reservation;
import com.vattenfall.model.User;
import com.vattenfall.model.UserStatus;
import com.vattenfall.services.UserService;
import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertTrue;

/**
 * Created by amoss on 27.03.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, ServicesConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class DtoMappingTest {

    @Autowired
    private Mapper mapper;

    @Autowired
    private UserService userService;

    @Test
    public void mapperBeanCreation() {
        assertTrue(mapper != null);
    }

    @Test
    public void UserToDto() {
        User user = createUser();
        UserDto userDto = mapper.map(user, UserDto.class);
        assertTrue(user.getId() == userDto.getId());
    }

    @Test
    public void UserToDomain() {
        UserDto userDto = new UserDto();
        userDto.setUsername("Username");
        userDto.setPassword("pass");
        userDto.setStatus(UserStatus.HOLDER);
        userDto.setPoints(1);
        userDto.setId(200l);
        User user = mapper.map(userDto, User.class);
        assertTrue(user.getId() == userDto.getId());
        assertTrue(user.getUsername().equals(userDto.getUsername()));
    }

    @Test
    public void ReservationToDto() {
        Reservation res = new Reservation();
        res.setDate(new DateTime().withDate(2014, 3, 27));
        res.setHolder(createUser());
        res.setId(Long.MAX_VALUE);

        ReservationDto resDto = mapper.map(res, ReservationDto.class);
        assertTrue(res.getId().equals(resDto.getId()));
        assertTrue(res.getHolder().getId().equals(resDto.getHolder().getId()));
        assertTrue(res.getDate().equals(resDto.getDate()));

    }

    @Test
    public void ReservationToDomain() {
        UserDto userDto = mapper.map(createUser(), UserDto.class);
        ReservationDto resDto = new ReservationDto();
        resDto.setDate(new DateTime().withDate(2014, 3, 27));
        resDto.setId(Long.MAX_VALUE);
        resDto.setHolder(userDto);

        Reservation res = mapper.map(resDto, Reservation.class);
        assertTrue(res.getId().equals(resDto.getId()));
        assertTrue(res.getHolder().getId().equals(resDto.getHolder().getId()));
        assertTrue(res.getDate().equals(resDto.getDate()));
    }

    private User createUser() {
        User user = new User();
        user.setUsername("User");
        user.setPassword("password");
        user.setStatus(UserStatus.HOLDER);
        user.setPoints(5);
        return userService.create(user);
    }

}
