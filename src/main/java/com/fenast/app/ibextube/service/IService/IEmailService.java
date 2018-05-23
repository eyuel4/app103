package com.fenast.app.ibextube.service.IService;

import org.springframework.mail.SimpleMailMessage;

/**
 * Created by taddesee on 5/21/2018.
 */
public interface IEmailService {
    void sendSimpleMessage(String to, String subject, String next);
    void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template, String ...templateArgs);
    void sendMessageWithAttachement(String to, String subject, String text, String pathToAttachment);
    void sendMessageMime(String to, String subject, String next);
}
