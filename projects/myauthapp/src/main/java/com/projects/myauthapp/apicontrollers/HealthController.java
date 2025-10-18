package com.projects.myauthapp.apicontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actuator")
public class HealthController {
    @GetMapping("/info")
    public String myInfo(){
        return "MyJournalApp Spring Boot Application version Initial SNAPSHOT version fron com.project.myauthapp";
    }
}
