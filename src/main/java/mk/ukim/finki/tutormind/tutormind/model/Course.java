package mk.ukim.finki.tutormind.tutormind.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
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

    public Course() {
    }
}
