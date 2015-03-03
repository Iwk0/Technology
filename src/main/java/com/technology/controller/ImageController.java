package com.technology.controller;

import com.technology.model.Greeting;
import com.technology.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Iwk0 on 02/03/2015.
 */
@Controller
public class ImageController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTestPage() {
        return "notification";
    }
}