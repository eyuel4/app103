package com.fenast.app.ibextube.service.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * Created by taddesee on 5/21/2018.
 */
@Service
public class EmailServiceImpl implements IEmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
        }
        catch (MailException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template, String... templateArgs) {
        String text = String.format(template.getText(), templateArgs);
        sendSimpleMessage(to, subject, text);
    }

    @Override
    public void sendMessageWithAttachement(String to, String subject, String text, String pathToAttachment) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);


            emailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void sendMessageMime(String to, String subject, String next) {
    }
}
