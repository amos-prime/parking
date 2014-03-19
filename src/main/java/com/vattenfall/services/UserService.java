package com.vattenfall.services;

import com.vattenfall.exceptions.UserNotFound;
import com.vattenfall.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by amoss on 23.01.14.
 */
public interface UserService {

    public User create(User user);
    public void delete(long id) throws UserNotFound;
    public List<User> findAll();
    public User update(User user) throws UserNotFound;
    public User findById(long id) throws UserNotFound;
}
