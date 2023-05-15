package mk.ukim.finki.tutormind.tutormind.repository;

import mk.ukim.finki.tutormind.tutormind.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByName(String name);
    void deleteByName(String name);

}
