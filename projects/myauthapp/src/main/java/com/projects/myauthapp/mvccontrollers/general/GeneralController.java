package com.projects.myauthapp.mvccontrollers.general;

import com.projects.myauthapp.entities.User;
import com.projects.myauthapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
public class GeneralController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(){
//        System.out.println("############## -> yaha tak tum pahuch gye ho");
        return "home";
    }

    @GetMapping("/write")
    public String writeJournal(){
        return "add_journal";
    }

    @PostMapping("/save_User")
    public String saveUser(@ModelAttribute User user){
        HashMap<String, String> userErrors = userService.verifyUser(user);

        if(userErrors.size() != 0){

        }else {

        }

        return "";
    }
}
