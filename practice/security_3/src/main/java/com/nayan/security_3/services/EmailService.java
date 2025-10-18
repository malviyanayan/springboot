package com.nayan.security_3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Application properties me configured email fetch karo
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Async
    public void sendHtmlEmail(String toEmail, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(fromEmail); // <-- yaha from app property use hoga
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // HTML

            mailSender.send(message);
            System.out.println("Email sent to " + toEmail + " with subject: " + subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void sendSignUpMail(String toEmail, String signUpLink) {
        String subject = "Welcome to DoFocus!";
        String body = "<h2>Welcome to DoFocus!</h2>" +
                "<p>Thank you for signing up. Start scheduling tasks, adding todos & notes, and follow challenges!</p>" +
                "<p><a href='" + signUpLink + "' style='padding:10px 20px; background-color:#4CAF50; color:white; text-decoration:none;'>Activate your account</a></p>" +
                "<br><p>Happy Focusing!<br>— Team DoFocus</p>";
        sendHtmlEmail(toEmail, subject, body);
    }

    @Async
    public void sendResetPasswordMail(String toEmail, String resetLink) {
        String subject = "Reset Your DoFocus Password";
        String body = "<h2>Password Reset Request</h2>" +
                "<p>Click the button below to reset your password.</p>" +
                "<p><a href='" + resetLink + "' style='padding:10px 20px; background-color:#f44336; color:white; text-decoration:none;'>Reset Password</a></p>" +
                "<br><p>If you did not request a reset, ignore this email.<br>— Team DoFocus</p>";
        sendHtmlEmail(toEmail, subject, body);
    }

    @Async
    public void sendDailyTaskReminder(String toEmail, String tasksSummary) {
        String subject = "Your Daily DoFocus Tasks!";
        String body = "<h2>Good Day!</h2>" +
                "<p>Here is your task summary for today:</p>" +
                "<ul>" + tasksSummary + "</ul>" +
                "<p>Keep focusing and achieve your goals!<br>— Team DoFocus</p>";
        sendHtmlEmail(toEmail, subject, body);
    }

    @Async
    public void sendNewChallengeMail(String toEmail, String challengeTitle, String challengeLink) {
        String subject = "New Challenge on DoFocus: " + challengeTitle;
        String body = "<h2>New Challenge Alert!</h2>" +
                "<p>Check out the new challenge: <b>" + challengeTitle + "</b></p>" +
                "<p><a href='" + challengeLink + "' style='padding:10px 20px; background-color:#2196F3; color:white; text-decoration:none;'>Join Challenge</a></p>" +
                "<br><p>Challenge yourself!<br>— Team DoFocus</p>";
        sendHtmlEmail(toEmail, subject, body);
    }

    @Async
    public void sendNotificationMail(String toEmail, String messageContent) {
        String subject = "Notification from DoFocus";
        String body = "<p>" + messageContent + "</p><br><p>— Team DoFocus</p>";
        sendHtmlEmail(toEmail, subject, body);
    }
}
