package ru.irkagr.somesite.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.irkagr.somesite.statistics.ApplicationStatistic;
import ru.irkagr.somesite.domen.Role;
import ru.irkagr.somesite.domen.User;
import ru.irkagr.somesite.repos.UserRepo;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    ApplicationStatistic applicationStatistic;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());

        String stat = applicationStatistic.toString();

        model.addAttribute("stat",stat);

    return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        LOGGER.info("У пользователя с почтой: {} меняются права.",user.getEmail());
        return "userEdit";
    }


    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {

        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                        .map(Role::name)
                                .collect(Collectors.toSet());

        user.getRoles().clear();

        for(String key : form.keySet()) {
            if(roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
                String logMessage = String.format("Пользователю с почтой: %s и c ID: %s добавилась роль: %s",
                        user.getEmail(), user.getId(), key);
                LOGGER.info(logMessage);
            }
        }

        userRepo.save(user);
        LOGGER.info("Пользователь с именем: {} успешно изменен", user.getUsername());

        return "redirect:/user";
    }


    @PostMapping("/delete")
    public String userDelete(@RequestParam("userId") User user) {

        userRepo.delete(user);
        LOGGER.info("Пользователю с почтой: {} и именем ({}) был удален", user.getEmail(), user.getUsername());

        return "redirect:/user";
    }

}
