package mk.ukim.finki.tutormind.tutormind.repository;

import mk.ukim.finki.tutormind.tutormind.model.User;
import mk.ukim.finki.tutormind.tutormind.model.projections.UserDetailsProjection;

import mk.ukim.finki.tutormind.tutormind.model.enumerations.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    List<User> findAllByRole(Role role);

    @Query(value = "select username,name,surname,description,role from users where username = ?1", nativeQuery = true)
    UserDetailsProjection getUserDetailsByUsername(String username);

}
