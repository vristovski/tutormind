package mk.ukim.finki.tutormind.tutormind.model.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.tutormind.tutormind.model.Course;
import mk.ukim.finki.tutormind.tutormind.model.enumerations.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {
    String username;
    String name;
    String surname;
    String description;
    String role;
    List<Course> courses;
}
