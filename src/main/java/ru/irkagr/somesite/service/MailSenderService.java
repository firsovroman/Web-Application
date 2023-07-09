package ru.irkagr.somesite.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MailSenderService.class);

    @Value("${spring.mail.username}")
    private String mailUserName;

    @Autowired
    private JavaMailSender mailSender;

    //TODO вынести в проперти
    private String MAIL_RECEIVER = "eftelt@rambler.ru";

    public void sendActivationCode(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(mailUserName);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);

        LOGGER.info("Код активации был отправлен: {}", emailTo);
    }


    public void sendComment(String userName, String textComment) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(mailUserName);
        mailMessage.setTo(MAIL_RECEIVER);
        mailMessage.setSubject("Новый комментарий от: " + userName);
        mailMessage.setText(textComment);

        mailSender.send(mailMessage);
        LOGGER.info("Комментарий был отправлен от: {}", userName);
    }

}
