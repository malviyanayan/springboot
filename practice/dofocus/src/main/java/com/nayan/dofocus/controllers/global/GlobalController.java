package com.nayan.dofocus.controllers.global;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GlobalController {



    @GetMapping("/")
    public String welcome(){
        return "index";
    }
}
