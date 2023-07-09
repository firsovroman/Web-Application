package ru.irkagr.somesite.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.irkagr.somesite.domen.Role;
import ru.irkagr.somesite.domen.User;
import ru.irkagr.somesite.repos.UserRepo;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    private final MailSenderService mailSenderService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, MailSenderService mailSenderService, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.mailSenderService = mailSenderService;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean emailExistForUser(String email) {
        //TODO сделать через метод который достает с базы сразу по email
        return userRepo.findAll().stream().map(User::getEmail).anyMatch(s -> StringUtils.equals(s, email));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);
        //TODO URL вынести в пропперти
        String message = String.format("Здравствуйте, %s \n" + "Для подтверждения регистрации пройдите по ссылке " +
                        "(если ссылка выглядит неактивной скопируйте и вставьте её в адресую строку браузера). http://localhost:8080/activate/%s",
                user.getUsername(), user.getActivationCode());


        if(!StringUtils.isBlank(user.getEmail())) {
            mailSenderService.sendActivationCode(user.getEmail(), "Activation code", message);
        }


        return true;
    }

    public boolean activateUser(String code) {

        User user = userRepo.findByActivationCode(code);

        if(user == null) {
           return false;
        }

        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }

}
