package mk.ukim.finki.tutormind.tutormind.service.impl;

import mk.ukim.finki.tutormind.tutormind.model.Category;
import mk.ukim.finki.tutormind.tutormind.model.Course;
import mk.ukim.finki.tutormind.tutormind.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.tutormind.tutormind.model.exceptions.CourseNotFoundException;
import mk.ukim.finki.tutormind.tutormind.repository.CategoryRepository;
import mk.ukim.finki.tutormind.tutormind.repository.CourseRepository;
import mk.ukim.finki.tutormind.tutormind.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;

    public CourseServiceImpl(CourseRepository courseRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return this.courseRepository.findById(id);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return this.courseRepository.findByName(name);
    }

    @Override
    public Optional<Course> save(String name, Long categoryId, String description, Double price, Double length) {
        Category category = this.categoryRepository.findById(categoryId).
                orElseThrow(() -> new CategoryNotFoundException(categoryId));

    this.courseRepository.deleteByName(name);
    Course course = new Course(name, category, description, price, length);
    this.courseRepository.save(course);
    return Optional.of(course);
    }

    @Override
    public Optional<Course> edit(Long id, String name, Long categoryId, String description, Double price, Double length) {
        Course course = this.courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));

        course.setName(name);
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        course.setCategory(category);
        course.setDescription(description);
        course.setPrice(price);
        course.setLength(length);

        this.courseRepository.save(course);
        return Optional.of(course);
    }

    @Override
    public void deleteById(Long id) {
        this.courseRepository.deleteById(id);
    }
}
