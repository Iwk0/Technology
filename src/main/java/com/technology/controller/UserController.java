package com.technology.controller;

import com.technology.model.User;
import com.technology.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-2-24
 * Time: 13:48
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newUserPage() {
        ModelAndView modelAndView = new ModelAndView("user/new", "user", new User());
        modelAndView.getModel().put("roles", User.Role.values());
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String listUserPage(@ModelAttribute("user") User user, ModelMap model) {
        logger.info("User registration");
        user.setPassword(new ShaPasswordEncoder(256).encodePassword(user.getPassword(), ""));
        userRepository.save(user);
        model.put("users", userRepository.findAll());
        logger.info("Registration successfully");

        return "redirect:/";
    }
}