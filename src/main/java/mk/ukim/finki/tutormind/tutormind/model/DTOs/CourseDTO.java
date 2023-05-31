package mk.ukim.finki.tutormind.tutormind.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    String name;
    Long category;
    String description;
    Double price;
    Double length;
    String username;
}
