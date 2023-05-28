package mk.ukim.finki.tutormind.tutormind.model.DTOs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.tutormind.tutormind.model.enumerations.Role;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {
    String username;
    String password;
    String repeatPassword;
    String name;
    String surname;
    Role role;
}
