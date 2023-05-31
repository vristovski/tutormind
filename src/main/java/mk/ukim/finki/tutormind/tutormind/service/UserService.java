package mk.ukim.finki.tutormind.tutormind.service;

import mk.ukim.finki.tutormind.tutormind.model.Course;
import mk.ukim.finki.tutormind.tutormind.model.User;
import mk.ukim.finki.tutormind.tutormind.model.DTOs.TutorDTO;
import mk.ukim.finki.tutormind.tutormind.model.DTOs.UserDetailsDTO;
import mk.ukim.finki.tutormind.tutormind.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    UserDetailsDTO getUserDetails(String username);

    List<TutorDTO> getTutorsWithCourses();

    List<User> getTutors();

    User getCurrentUser();

}
