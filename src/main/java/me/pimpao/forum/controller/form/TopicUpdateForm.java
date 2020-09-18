package me.pimpao.forum.controller.form;

import me.pimpao.forum.model.Topic;
import me.pimpao.forum.repository.TopicRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopicUpdateForm {

    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String title;

    @NotNull
    @NotEmpty
    @Size(min = 10)
    private String message;

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

    public Topic update(Long id, TopicRepository topicRepository) {
        Topic topic = topicRepository.getOne(id);
        topic.setTitle(this.title);
        topic.setMessage(this.message);
        return topicRepository.save(topic);
    }
}
