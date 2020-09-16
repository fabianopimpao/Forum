package me.pimpao.forum.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String message;

    private LocalDateTime creationDate = LocalDateTime.now();

    @Enumerated(value = EnumType.STRING)
    private TopicStatus status = TopicStatus.NOT_RESPONDED;

    @ManyToOne
    private User author;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "topic")
    private List<Response> responses = new ArrayList<>();

    public Topic() {

    }

    public Topic(String title, String message, Course course) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public TopicStatus getStatus() {
        return status;
    }

    public void setStatus(TopicStatus status) {
        this.status = status;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id.equals(topic.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
