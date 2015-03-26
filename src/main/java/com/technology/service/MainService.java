package com.technology.service;

import com.technology.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Iwk0 on 09/03/2015.
 */
@Service(value = "mainService")
public class MainService {

    @Autowired
    private UserRepository userRepository;

}