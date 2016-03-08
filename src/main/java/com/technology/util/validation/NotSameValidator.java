package com.technology.util.validation;

import com.technology.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-3-11
 * Time: 9:52
 */
@Component
public class NotSameValidator implements ConstraintValidator<NotSame, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(NotSame notSame) {}

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByUsername(username) == null;
    }
}