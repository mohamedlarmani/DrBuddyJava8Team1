package com.develhope.drbuddy.notifications;

import com.develhope.drbuddy.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    /**
     * Sends a registration email to the patient.
     * @param patient The patient who completed the registration and will receive the email.
     */
    public void sendRegistrationEmail(Patient patient) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailFrom);
        simpleMailMessage.setReplyTo(mailFrom);
        simpleMailMessage.setSubject("Registration completed");
        simpleMailMessage.setText("Ciao " + patient.getFirstname() + ", conferma la tua mail inserendo il codice: " + patient.getActivationCode());
        simpleMailMessage.setTo(patient.getEmail());
        javaMailSender.send(simpleMailMessage);
    }

}
