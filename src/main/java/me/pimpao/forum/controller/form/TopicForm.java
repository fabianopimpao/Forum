package me.pimpao.forum.controller.form;

import me.pimpao.forum.model.Course;
import me.pimpao.forum.model.Topic;
import me.pimpao.forum.repository.CourseRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopicForm {

    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String title;

    @NotNull
    @NotEmpty
    @Size(min = 10)
    private String message;

    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String courseName;

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Topic converter(CourseRepository courseRepository) {
        Course course = courseRepository.findByName(courseName);
        return new Topic(this.title, this.message, course);
    }
}
