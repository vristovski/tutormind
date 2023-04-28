package mk.ukim.finki.tutormind.tutormind.model;

import lombok.Data;
import mk.ukim.finki.tutormind.tutormind.model.enumerations.Category;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class Course {

    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    private String description;

    private Double price;

    private Double length;

    public Course(String name, Category category, String description, Double price, Double length) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.length = length;
    }
}
