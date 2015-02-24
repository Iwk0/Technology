package com.technology.repository;

import com.technology.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-2-24
 * Time: 9:59
 */
public interface UserRepository extends JpaRepository<User, Long> {
}