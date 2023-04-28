package mk.ukim.finki.tutormind.tutormind.service;

import mk.ukim.finki.tutormind.tutormind.model.Course;
import mk.ukim.finki.tutormind.tutormind.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAll();

    Optional<Course> findById(Long id);

    Optional<Course> findByName(String name);

    Optional<Course> save(String name, Category category, String description, Double price, Double length);

    Optional<Course> edit(Long id, String name, Category category, String description, Double price, Double length);

    void deleteById(Long id);

}
