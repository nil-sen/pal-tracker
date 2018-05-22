package io.pivotal.pal.tracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController

public class WelcomeController {

    //Variable
    String welcomeMessage;

    public WelcomeController(@Value("${WELCOME_MESSAGE}") String message){

        this.welcomeMessage = message;
    }

    @GetMapping("/")
    public String sayHello() {
        return this.welcomeMessage;
    }
}