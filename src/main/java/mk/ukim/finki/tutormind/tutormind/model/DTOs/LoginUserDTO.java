package mk.ukim.finki.tutormind.tutormind.model.DTOs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO {
    String username;
    String password;
}
