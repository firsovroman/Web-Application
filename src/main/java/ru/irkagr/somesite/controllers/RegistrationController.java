package ru.irkagr.somesite.controllers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.irkagr.somesite.domen.User;
import ru.irkagr.somesite.domen.dto.CaptchaResponceDTO;
import ru.irkagr.somesite.service.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    //TODO вынести в проперти
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";


    private final UserService userService;

    private final RestTemplate restTemplate;

    private final String secret;

    @Autowired
    public RegistrationController(UserService userService,
                                  RestTemplate restTemplate,
                                  Environment environment) {
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.secret = environment.getProperty("recaptcha.secret");
    }

    @GetMapping("/registration")
    public String registration() {
        LOGGER.info("Регистрация началась");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @RequestParam("g-recaptcha-response") String captchaResponce,
            @Valid User user,
            BindingResult bindingResult,
            Model model) {

        LOGGER.info("Добавляется пользователь");

        String url = String.format(CAPTCHA_URL, secret, captchaResponce);
        CaptchaResponceDTO responce = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponceDTO.class);

        if(!responce.isSuccess()) {
            model.addAttribute("captchaError","Fill captcha");
            LOGGER.info("Каптча не была нажата");
        }


        boolean isConfirmEmpty = StringUtils.isBlank(passwordConfirm);

        if(isConfirmEmpty) {
            model.addAttribute("password2Error", "Password confirmation cannot be empty");
            LOGGER.info("Пароль подтверждения пуст");
        }

        if(user.getPassword() != null && !user.getPassword().equals(passwordConfirm)){
            model.addAttribute("passwordError","Passwords are different!");
            LOGGER.info("Пароли не совпали");
            return "registration";
        }

        if(isConfirmEmpty || bindingResult.hasErrors() || !responce.isSuccess()){
            Map<String,String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            LOGGER.info("При регистрации возникли ошибки");
            return "registration";
        }

        if(userService.emailExistForUser(user.getEmail())) {
            model.addAttribute("emailError", "Email exists!");
            LOGGER.info("Почта уже существует");
            return "registration";
        }

        if(!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            LOGGER.info("Пользователь уже существует");
            return "registration";
        }

        return "redirect:/login";
    }


    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){

        boolean isActivated = userService.activateUser(code);

        if(isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated!");
            LOGGER.info("Пользователь активирован");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found!");
            LOGGER.info("Пользователь НЕ активирован");
        }

        return "login";
    }


}
