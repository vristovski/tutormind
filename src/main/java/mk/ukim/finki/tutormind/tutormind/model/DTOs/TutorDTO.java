package mk.ukim.finki.tutormind.tutormind.model.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.tutormind.tutormind.model.Course;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutorDTO {
    String username;
    String name;
    String surname;
    String description;
    List<Course> courses;
}
