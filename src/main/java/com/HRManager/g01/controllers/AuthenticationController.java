package com.HRManager.g01.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {
    @RequestMapping("/login")
    public String login(){
        return "Authentication/loginPage";
    }


    @RequestMapping("/accessDenied")
    public String accessDenied(){

        System.out.println("the access to this page is denied");
        return "Authentication/accessDenied";
    }

    @RequestMapping("welcome")
    public String welcome(){
        return "Authentication/welcome";
    }
}
