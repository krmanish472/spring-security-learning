package com.techxtor.springbootsecuritylearning;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/user")
    public String validateUser() {
        System.out.println("User validated");
        return "Success";
    }
}
