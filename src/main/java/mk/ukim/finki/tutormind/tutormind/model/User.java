package mk.ukim.finki.tutormind.tutormind.model;

import lombok.Data;
import mk.ukim.finki.tutormind.tutormind.model.enumerations.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class User {
    private String username;

    private String password;

    private String name;

    private String surname;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }
}
