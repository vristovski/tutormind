package mk.ukim.finki.tutormind.tutormind.model.DTOs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.tutormind.tutormind.model.enumerations.Role;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {
    String username;
    String name;
    String surname;
    String description;
}
