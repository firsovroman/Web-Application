package ru.irkagr.somesite.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.irkagr.somesite.domen.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {



}
