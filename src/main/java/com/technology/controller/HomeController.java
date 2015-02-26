package com.technology.controller;

import com.google.gson.GsonBuilder;
import com.technology.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private UserRepository userRepository;

/*    @Value("${date.format}")
    private String dateFormat;*/

    @RequestMapping(value = "/login")
    public String loginPage(@RequestParam(value = "loginError", required = false) String error, ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            model.put("error", error);
            return "static/index.html";
            //return "login";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/controlPanel")
    public String controlPanel() {
        return "controlPanel";
    }

    @RequestMapping(value = "/")
    public String homePage(ModelMap model) {
        model.put("users", userRepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    String getListOfUsers() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(userRepository.findAll());
    }
}