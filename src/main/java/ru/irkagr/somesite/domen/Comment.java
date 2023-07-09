package ru.irkagr.somesite.domen;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name ="comments")
public class Comment {

    private static DateTimeFormatter DTF_SEC = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Id
    @Column(name = "comment_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Message message;

    private String authorName;
    private String authorId;
    private String commentText;
    private String dateTime;
    private String grade;

    public Comment() {
    }

    public Comment(String authorName, String authorId, String commentText) {
        this.authorName = authorName;
        this.authorId = authorId;
        this.commentText = commentText;
        this.dateTime = LocalDateTime.now().format(DTF_SEC);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String text) {
        this.commentText = text;
    }

    public boolean commentTextIsNotEmpty() {
        return !commentText.isEmpty();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
