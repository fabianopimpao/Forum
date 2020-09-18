package me.pimpao.forum.controller.dto;

import me.pimpao.forum.model.Topic;
import me.pimpao.forum.model.TopicStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicDetailDto {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private String authorName;
    private TopicStatus status;
    private List<ResponseDto> responses = new ArrayList<>();

    public TopicDetailDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.creationDate = topic.getCreationDate();
        this.authorName = topic.getAuthor().getName();
        this.status = topic.getStatus();
        this.responses.addAll(topic.getResponses().stream().map(ResponseDto::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public TopicStatus getStatus() {
        return status;
    }

    public List<ResponseDto> getResponses() {
        return responses;
    }
}
