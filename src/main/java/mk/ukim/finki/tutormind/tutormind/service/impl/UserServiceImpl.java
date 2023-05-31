package mk.ukim.finki.tutormind.tutormind.service.impl;

import mk.ukim.finki.tutormind.tutormind.model.Course;
import mk.ukim.finki.tutormind.tutormind.model.User;
import mk.ukim.finki.tutormind.tutormind.model.DTOs.TutorDTO;
import mk.ukim.finki.tutormind.tutormind.model.DTOs.UserDetailsDTO;
import mk.ukim.finki.tutormind.tutormind.model.enumerations.Role;
import mk.ukim.finki.tutormind.tutormind.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.tutormind.tutormind.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.tutormind.tutormind.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.tutormind.tutormind.model.projections.UserDetailsProjection;
import mk.ukim.finki.tutormind.tutormind.repository.UserRepository;
import mk.ukim.finki.tutormind.tutormind.service.CourseService;
import mk.ukim.finki.tutormind.tutormind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CourseService courseService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
            CourseService courseService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.courseService = courseService;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Find a way to get the current user -> Currently not working
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userRepository.findByUsername(currentUserName)
                    .orElseThrow(() -> new UsernameNotFoundException(currentUserName));
        } else {
            return getTutors().stream().findFirst()
                    .orElseThrow(() -> new UsernameNotFoundException("test"));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname,
            Role userRole) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username, passwordEncoder.encode(password), name, surname, userRole);
        return userRepository.save(user);
    }

    public List<User> getTutors() {
        return userRepository.findAllByRole(Role.ROLE_TEACHER);
    }

    public List<TutorDTO> getTutorsWithCourses() {
        List<TutorDTO> tutorDTOs = new ArrayList<TutorDTO>();
        List<User> tutors = userRepository.findAllByRole(Role.ROLE_TEACHER);

        for (User tutor : tutors) {
            List<Course> allCourses = courseService.findAll();
            List<Course> courses = courseService.filterCoursesByUser(allCourses, tutor.getUsername());
            TutorDTO tutorDto = new TutorDTO(tutor.getUsername(), tutor.getName(), tutor.getSurname(),
                    tutor.getDescription(), courses);
            tutorDTOs.add(tutorDto);
        }
        return tutorDTOs;
    }

    public UserDetailsDTO getUserDetails(String username) {

        UserDetailsProjection userDetailsProjection = userRepository.getUserDetailsByUsername(username);

        List<Course> allCourses = courseService.findAll();
        List<Course> courses = courseService.filterCoursesByUser(allCourses, userDetailsProjection.getUsername());

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(userDetailsProjection.getUsername(),
                userDetailsProjection.getName(), userDetailsProjection.getSurname(),
                userDetailsProjection.getDescription(),userDetailsProjection.getRole(), courses);

        return userDetailsDTO;
    }
}
