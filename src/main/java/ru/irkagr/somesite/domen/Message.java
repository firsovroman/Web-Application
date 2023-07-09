package ru.irkagr.somesite.domen;


import javax.persistence.*;
import java.util.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String tag;

    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_type")
    private Rank rank;


    @OneToMany(mappedBy = "message")
    private Set<Comment> commentSet = new TreeSet<>();


    public Message() {
    }

    public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    public boolean commentNotEmpty() {
        return !commentSet.isEmpty();
    }

    public int getNumberOfComment() {

        if(commentNotEmpty()){
            return commentSet.size();
        }
        return 0;
    }


    /**
     * Метод получения псевдослучайного целого числа от 1 до 3 (включая 3);
     */
    public static int rnd() {
        int max = 2;
        return (int) (Math.random() * ++max) + 1;
    }
}
