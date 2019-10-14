package com.bend.his.insurance.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping("/")
@Controller
public class IndexController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "views/index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "views/app";
    }

}
