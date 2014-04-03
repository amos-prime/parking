package services;

import com.vattenfall.dto.ReservationDto;
import com.vattenfall.dto.UserDto;
import com.vattenfall.model.Reservation;
import com.vattenfall.model.User;
import com.vattenfall.services.ReservationService;
import com.vattenfall.services.ServiceFacadeImpl;
import com.vattenfall.services.UserService;
import org.dozer.Mapper;
import org.fest.assertions.Assertions;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.fest.assertions.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by amoss on 26.03.14.
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceFacadeTest {

    @InjectMocks
    private ServiceFacadeImpl serviceFacade;
    @Mock
    private ReservationService reservationService;
    @Mock
    private UserService userService;
    @Mock
    private Mapper mapper;

    @Test
    public void beanCreation() {
        assertTrue(serviceFacade != null);
    }

    @Test
    public void getUsers() {
        //given
        given(userService.findAll()).willReturn(getTestListOfUsers());

        //when
        serviceFacade.getUsers();

        //then
        verify(userService).findAll();
        verify(mapper, times(2)).map(any(User.class), eq(UserDto.class));
    }

    @Test
    public void getReservations() {
        //given
        given(reservationService.findAll()).willReturn(getTestListOfReservations());

        //when
        serviceFacade.getReservations();

        verify(reservationService).findAll();
        verify(mapper, times(2)).map(any(Reservation.class), eq(ReservationDto.class));
    }

    @Test
    public void releaseReservation() {
        //given
        Long resId = 1l;
        ArgumentCaptor<Reservation> captor = ArgumentCaptor.forClass(Reservation.class);
        given(reservationService.findById(resId)).willReturn(getTestReservationObject(resId));

        //when
        serviceFacade.releaseReservation(resId);

        //then
        verify(reservationService).update(captor.capture());
        assertThat(captor.getValue().getHolder()).isNull();
    }

    @Test
    public void assignReservationToUser() {

    }




    private List<User> getTestListOfUsers() {
        List<User> users = new ArrayList<User>();
        users.add(getTestUserObject(1l));
        users.add(getTestUserObject(2l));
        return users;
    }

    private List<Reservation> getTestListOfReservations() {
        List<Reservation> reservations = new ArrayList<Reservation>();
        reservations.add(getTestReservationObject(111l));
        reservations.add(getTestReservationObject(222l));
        return reservations;
    }

    private Reservation getTestReservationObject(Long resId) {
        Reservation res = new Reservation();
        res.setId(resId);
        res.setHolder(getTestUserObject(resId));
        res.setDate(new DateTime().withDate(2014, 4, 1));
        return res;
    }

    private User getTestUserObject(Long userId) {
        User user1 = new User();
        user1.setUsername("UserObject" + userId);
        user1.setId(userId);
        return user1;
    }

}

























