package mk.ukim.finki.tutormind.tutormind.web.rest;

import mk.ukim.finki.tutormind.tutormind.model.Course;
import mk.ukim.finki.tutormind.tutormind.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/courses")
public class CourseRestController {

    private final CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    private List<Course> findAll() {
        return this.courseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {
        return this.courseService.findById(id)
                .map(course -> ResponseEntity.ok().body(course))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Course> save(@RequestParam String name,
                                       @RequestParam Long category,
                                       @RequestParam String description,
                                       @RequestParam Double price,
                                       @RequestParam Double length) {
        return this.courseService.save(name, category, description, price, length)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Course> save(@PathVariable Long id,
                                       @RequestParam String name,
                                       @RequestParam Long category,
                                       @RequestParam String description,
                                       @RequestParam Double price,
                                       @RequestParam Double length) {
        return this.courseService.edit(id, name, category, description, price, length)
                .map(course -> ResponseEntity.ok().body(course))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.courseService.deleteById(id);
        if(this.courseService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}