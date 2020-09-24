package me.pimpao.forum.controller;

import me.pimpao.forum.controller.dto.TopicDetailDto;
import me.pimpao.forum.controller.dto.TopicDto;
import me.pimpao.forum.controller.form.TopicForm;
import me.pimpao.forum.controller.form.TopicUpdateForm;
import me.pimpao.forum.model.Topic;
import me.pimpao.forum.repository.CourseRepository;
import me.pimpao.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    @Cacheable(value = "topicsList")
    public Page<TopicDto> listaAll(@RequestParam(required = false) String courseName,
                                   @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pagination) {

        Page<Topic> topics;
        if (courseName == null) {
            topics = topicRepository.findAll(pagination);
        } else {
            topics = topicRepository.findByCourseName(courseName, pagination);
        }

        return TopicDto.converter(topics);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicDto> create(@Valid @RequestBody TopicForm form, UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = form.converter(courseRepository);
        topic = topicRepository.save(topic);
        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailDto> find(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if (topic.isPresent()) {
            return ResponseEntity.ok(new TopicDetailDto(topic.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody TopicUpdateForm topicUpdateForm) {
        Optional<Topic> optional = topicRepository.findById(id);
        if (optional.isPresent()) {
            Topic topic = topicUpdateForm.update(id, topicRepository);
            return ResponseEntity.ok(new TopicDto(topic));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if (topic.isPresent()) {
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
