package com.technology.controller;

import com.google.gson.Gson;
import com.technology.model.Tree;
import com.technology.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class PagingController {

    private final static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21);
    private final static Logger logger = Logger.getLogger(PagingController.class);

    @RequestMapping(value = "/pagination", method = RequestMethod.GET)
    public String getPagination(String page, ModelMap model) {
        int listSize = list.size();
        int parsedPage = 0;

        if (page != null) {
            try {
                int number = Integer.parseInt(page) - 1;
                parsedPage = number < 0 ? listSize : number;
            } catch (NumberFormatException e) {
                parsedPage = listSize;
                logger.error(e);
            }
        }

        int start = Math.min(parsedPage * 10, listSize);
        int end = Math.min(start + 10, listSize);

        model.put("list", list.subList(start, end));

        return "pagination";
    }

    @RequestMapping(value = "/pagination", method = RequestMethod.POST)
    public
    @ResponseBody String sendJSON() {
        User user = new User();
        user.setAmount("23");
        user.setPassword("abv123");
        user.setUsername("test");
        user.setRole(User.Role.ADMIN);

        return new Gson().toJson(user, User.class);
    }

    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    public
    @ResponseBody String json(@RequestBody Tree tree) {
        //Tree tree = new Gson().fromJson(json, Tree.class);
        return "";
    }
}