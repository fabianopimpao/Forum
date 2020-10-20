package me.pimpao.forum.repository;

import me.pimpao.forum.model.Course;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void shouldLoadACourseWhenSearchingForYourName() {
        String courseName = "HTML 5";
        Course course = courseRepository.findByName(courseName);
        Assert.assertNotNull(course);
        Assert.assertEquals(courseName, course.getName());
    }

    @Test
    public void shouldLoadACourseWhenItIsNotRegistered() {
        String courseName = "Ping Pong";
        Course course = courseRepository.findByName(courseName);
        Assert.assertNull(course);
    }
}
