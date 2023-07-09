package ru.irkagr.somesite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Objects;
import java.util.Properties;

@Configuration
public class MailConfig {

    private final String host;
    private final String username;
    private final String password;
    private final int port;
    private final String protocol;
    private final String debug;

    @Autowired
    public MailConfig(Environment environment) {
        this.host = environment.getProperty("spring.mail.host");
        this.username = environment.getProperty("spring.mail.username");
        this.password = environment.getProperty("spring.mail.password");
        this.port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("spring.mail.port")));
        this.protocol = environment.getProperty("spring.mail.protocol");
        this.debug = environment.getProperty("mail.debug");
    }

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol", protocol);
        properties.setProperty("mail.debug", debug); // TODO в продакшене отключить

        return mailSender;
    }

}
