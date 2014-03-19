package com.vattenfall.repository;

import com.vattenfall.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amoss on 22.01.14.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
