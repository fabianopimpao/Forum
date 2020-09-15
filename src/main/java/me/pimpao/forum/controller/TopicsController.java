package me.pimpao.forum.controller;

import me.pimpao.forum.controller.dto.TopicDto;
import me.pimpao.forum.model.Course;
import me.pimpao.forum.model.Topic;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicsController {

    @RequestMapping("/topics")
    public List<TopicDto> listaAll() {
        Topic topic = new Topic("Dúvida", "Dúvida com Spring", new Course("Spring", "Programação"));

        return TopicDto.converter(Arrays.asList(topic, topic, topic));
    }

}
