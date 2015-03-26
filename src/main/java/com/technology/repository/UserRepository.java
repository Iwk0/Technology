package com.technology.repository;

import com.technology.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-2-24
 * Time: 9:59
 */
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
    public List<User> findByUsernameLike(String username);
}