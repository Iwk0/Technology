package com.technology.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: imishev
 * Date: 15-3-26
 * Time: 16:21
 */
@Controller
public class ErrorController {

    @RequestMapping(value = "/accessDenied")
    public String accessDenied() {
        return "error/accessDenied";
    }
}