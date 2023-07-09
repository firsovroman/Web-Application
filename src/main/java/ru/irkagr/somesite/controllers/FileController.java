package ru.irkagr.somesite.controllers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.irkagr.somesite.domen.Comment;
import ru.irkagr.somesite.domen.Message;
import ru.irkagr.somesite.domen.Rank;
import ru.irkagr.somesite.domen.User;
import ru.irkagr.somesite.repos.CommentRepo;
import ru.irkagr.somesite.repos.MessageRepo;
import ru.irkagr.somesite.repos.UserRepo;
import ru.irkagr.somesite.service.MailSenderService;

import java.util.Set;

@Controller
public class FileController {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private MailSenderService mailSenderService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/file")
    public String geOne(@RequestParam String id, Model model) {

        Long idLikeNumber = 0L;
        try {
            idLikeNumber = new Long(id);
        }catch (NumberFormatException e) {
            System.err.println("Неверный формат строки!");
        }


        Message message = messageRepo.findById(idLikeNumber).orElseThrow( ()-> new RuntimeException("100.100 Объект по id в базе не найдет"));
        model.addAttribute("message", message);

        return "file";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/file")
    public String changeParameters(@RequestParam String id,
                                   @Nullable @RequestParam String tag,
                                   @Nullable @RequestParam String text,
                                   @Nullable @RequestParam String rank,
                                   Model model){

        Long idLikeNumber = 0L;
        try {
            idLikeNumber = new Long(id);
        }catch (NumberFormatException e) {
            System.err.println("Неверный формат строки!");
        }

        Message message = messageRepo.findById(idLikeNumber).orElseThrow( ()-> new RuntimeException("100.100 Объект по id в базе не найдет"));

        if(!StringUtils.isBlank(tag)){
            message.setTag(tag);
        }

        if(!StringUtils.isBlank(text)){
            message.setText(text);
        }

        if(!StringUtils.isBlank(rank)){
            message.setRank(Rank.valueOf(rank));
        }

        messageRepo.save(message);

        model.addAttribute("message", message);

        return "file";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/file/delete")
    public String delete(@RequestParam String id, Model model){

        Long idLikeNumber = 0L;
        try {
            idLikeNumber = new Long(id);
            Message message = messageRepo.findById(idLikeNumber).orElseThrow( ()-> new RuntimeException("100.100 Объект по id в базе не найдет"));
            messageRepo.delete(message);
        }catch (NumberFormatException e) {
            System.err.println("Неверный формат строки!");
        }

        LOGGER.info("Компания с ID: {} была удалена.", id );

        return "redirect:/main";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/file/comment")
    public String changeParameters(@RequestParam("messageId") Message message,
                                   @RequestParam("userId") User user,
                                   @RequestParam String textComment,
                                   @Nullable @RequestParam String grade,
                                   Model model){

        Comment comment = new Comment(user.getUsername(),user.getId().toString(),textComment);
        comment.setMessage(message);

        if(!StringUtils.isBlank(grade)){
            comment.setGrade(grade);
        }

        commentRepo.save(comment);

        Set<Comment> set = message.getCommentSet();
        set.add(comment);

        message.setCommentSet(set);
        messageRepo.save(message);

        model.addAttribute("message", message);

        return "file";
    }


    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/file/send")
    public String sendComment(@RequestParam("messageId") Message message,
                                   @RequestParam String userName,
                                   @RequestParam String textComment,
                                   @RequestParam String rank,
                                   Model model){

        User user = userRepo.findByUsername(userName);

        String textForAdmin = String.format(
                "Комментарий для компании с названием: %s \n" +
                "От пользователя с ID: %s \n" +
                "с именем:  %s \n" +
                "с оценкой:  %s \n" +
                "текст комментария: %s \n",
                message.getTag(), user.getId(), userName, rank ,textComment );

        mailSenderService.sendComment(userName,textForAdmin);

        model.addAttribute("message", message);
        model.addAttribute("isItSended", true);

        LOGGER.info("Комментарий от пользователя с ID: {} и именем ({}) был отправлен.", user.getId(), userName );

        return "file";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/file/comment/delete")
    public String changeParameters(@RequestParam("messageId") Message message,
                                   @RequestParam("commentId") String comment_id,
                                   Model model){

        Long id = Long.valueOf(comment_id);

        Comment comment = commentRepo.findById(id)
                .orElseThrow( () -> new RuntimeException("200.100 Комментарий по такому ID не найден"));

        Set<Comment> set = message.getCommentSet();
        set.remove(comment);
        commentRepo.delete(comment);

//        message.setCommentSet(set);
        messageRepo.save(message);

        model.addAttribute("message", message);

        return "file";
    }
}
