package me.pimpao.forum.controller;

import me.pimpao.forum.controller.dto.TopicDto;
import me.pimpao.forum.controller.form.TopicForm;
import me.pimpao.forum.model.Topic;
import me.pimpao.forum.repository.CourseRepository;
import me.pimpao.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<TopicDto> listaAll(String courseName) {
        if (courseName == null) {
            List<Topic> topics = topicRepository.findAll();
            return TopicDto.converter(topics);
        } else {
            List<Topic> topics = topicRepository.findByCourseName(courseName);
            return TopicDto.converter(topics);
        }
    }

    @PostMapping
    public ResponseEntity<TopicDto> create(@RequestBody TopicForm form, UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = form.converter(courseRepository);
        topic = topicRepository.save(topic);
        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

}
