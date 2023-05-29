package mk.ukim.finki.tutormind.tutormind.web.rest;

import mk.ukim.finki.tutormind.tutormind.config.security.JwtTokenProvider;
import mk.ukim.finki.tutormind.tutormind.model.User;
import mk.ukim.finki.tutormind.tutormind.model.DTOs.LoginUserDTO;
import mk.ukim.finki.tutormind.tutormind.model.DTOs.RegisterUserDTO;
import mk.ukim.finki.tutormind.tutormind.repository.UserRepository;
import mk.ukim.finki.tutormind.tutormind.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public UserController(UserService userService,
            AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
            UserRepository userRepository) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterUserDTO registerUserDTO) {
        return userService.register(registerUserDTO.getUsername(), registerUserDTO.getPassword(),
                registerUserDTO.getRepeatPassword(), registerUserDTO.getName(),
                registerUserDTO.getSurname(), registerUserDTO.getRole());
    }

    @GetMapping("/login")
    public Map<Object, Object> loginUser(@RequestBody LoginUserDTO loginUserDTO) {

        try {
            String username = loginUserDTO.getUsername();
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, loginUserDTO.getPassword()));
            String token = jwtTokenProvider.createToken(username, userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRole()
                    .toString());

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);

            return model;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @GetMapping("/validate")
    public boolean validateToken(ServletRequest req) {
        try {
            String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
            return token != null && jwtTokenProvider.validateToken(token);
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/tutors")
    public List<User> getAllTutors() {
        return userService.getTutors();
    }
}
