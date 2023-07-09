package ru.irkagr.somesite.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.irkagr.somesite.statistics.ApplicationStatistic;
import ru.irkagr.somesite.domen.Message;
import ru.irkagr.somesite.domen.User;
import ru.irkagr.somesite.repos.MessageRepo;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    ApplicationStatistic applicationStatistic;

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

        applicationStatistic.getCounterGreeting().increment();
        return "greeting";
    }

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){

        Iterable<Message> messages;

        if(!StringUtils.isBlank(filter)){
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        LOGGER.info("uploadPath: ",uploadPath);

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        applicationStatistic.getCounterMain().increment();

        return "main";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/main")
    public String add(
                      @AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag,
                      Model model,
                      @RequestParam("file") MultipartFile file
                    ) throws IOException {

        Message message = new Message(text, tag, user);

        if(file != null  && !StringUtils.isBlank(file.getOriginalFilename())) {
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            message.setFilename(resultFileName);
        }


        messageRepo.save(message);

        LOGGER.info("Добавили компанию с названием: {}", message.getTag());

        return  "redirect:/main";
    }




    // redirect:/ переводит выполнение программы не во view, а в другой контроллер который
    // уже и выводит всех пользователей, тем самым для того чтобы вывести всех после добавления еще одного
    // не требуется дублировать код


}