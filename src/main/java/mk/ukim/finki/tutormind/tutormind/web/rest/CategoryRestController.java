package mk.ukim.finki.tutormind.tutormind.web.rest;

import mk.ukim.finki.tutormind.tutormind.model.Category;
import mk.ukim.finki.tutormind.tutormind.model.Course;
import mk.ukim.finki.tutormind.tutormind.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> findAll() {
        return this.categoryService.listCategories();
    }

    @PostMapping("/add")
    public ResponseEntity<Category> save(@RequestParam String name,
                                         @RequestParam String description) {
        Category createdCategory = categoryService.create(name, description);
        return ResponseEntity.ok().body(createdCategory);
    }
}
