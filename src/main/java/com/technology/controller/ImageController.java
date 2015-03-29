package com.technology.controller;

import com.technology.model.Greeting;
import com.technology.model.HelloMessage;
import com.technology.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Iwk0 on 02/03/2015.
 */
@Controller
public class ImageController {

    @Autowired
    private FileRepository fileRepository;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTestPage() {
        return "notification";
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public ModelAndView getImagePage(@PathVariable(value = "id") String id) {
        return new ModelAndView("image", "file", fileRepository.findOne(Long.valueOf(id)));
    }
}