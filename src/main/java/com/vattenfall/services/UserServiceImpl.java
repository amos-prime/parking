package com.vattenfall.services;

import com.vattenfall.exceptions.UserNotFound;
import com.vattenfall.model.User;
import com.vattenfall.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by amoss on 23.01.14.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = UserNotFound.class)
    public void delete(long id) throws UserNotFound {
        User userForDelete = userRepository.findOne(id);
        if(userForDelete == null) {
            String message = "User with id: " + id + " does not exist";
            throw new UserNotFound(message);
        }
        userRepository.delete(userForDelete);
    }

    @Override
    @Transactional(rollbackFor=UserNotFound.class)
    public User update(User user) throws UserNotFound {
        User userToBeUpdated = userRepository.findOne(user.getId());
        if(userToBeUpdated == null) {
            String message = "User with id: " + user.getId() + " does not exist";
            throw new UserNotFound(message);
        }
        userToBeUpdated = user;
        return userRepository.save(userToBeUpdated);    //  Should I save it?????
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User findById(long id) throws UserNotFound {
        User user = userRepository.findOne(id);
        if(user == null) {
            String message = "User with id: " + id + " does not exist";
            throw new UserNotFound(message);
        }
        return user;
    }
}
