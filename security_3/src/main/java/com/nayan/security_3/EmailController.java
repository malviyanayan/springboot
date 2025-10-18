package com.nayan.security_3;

import com.nayan.security_3.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-signup-email")
    public String sendSignupEmail(@RequestParam String to) {
        emailService.sendSignUpMail(to, "http://dofocus.com/activate?token=12345");
        return "SignUp Email is being sent in background!";
    }

    @GetMapping("/send-reset-email")
    public String sendResetEmail(@RequestParam String to) {
        emailService.sendResetPasswordMail(to, "http://dofocus.com/reset-password?token=67890");
        return "Reset Password Email is being sent in background!";
    }

    @GetMapping("/send-challenge-email")
    public String sendChallengeEmail(@RequestParam String to, @RequestParam String title) {
        emailService.sendNewChallengeMail(to, title, "http://dofocus.com/challenge/123");
        return "Challenge Email is being sent!";
    }
}
