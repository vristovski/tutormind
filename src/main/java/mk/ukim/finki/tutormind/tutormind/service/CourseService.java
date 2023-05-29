package mk.ukim.finki.tutormind.tutormind.service;

import mk.ukim.finki.tutormind.tutormind.model.Category;
import mk.ukim.finki.tutormind.tutormind.model.Course;
import mk.ukim.finki.tutormind.tutormind.model.User;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAll();

    Optional<Course> findById(Long id);

    Optional<Course> findByName(String name);

    Optional<Course> save(String name, Long category, String description, Double price, Double length, User user);

    Optional<Course> edit(Long id, String name, Long category, String description, Double price, Double length);

    void deleteById(Long id);

    List<Course> filterCoursesByUser(List<Course> courses, User user);

}
