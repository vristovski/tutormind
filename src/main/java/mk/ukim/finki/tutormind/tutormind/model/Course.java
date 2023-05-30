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

    @ManyToOne
    private User user;

    public Course(String name, Category category, String description, Double price, Double length, User user) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.length = length;
        this.user = user;
    }

    public Course() {
    }
}
