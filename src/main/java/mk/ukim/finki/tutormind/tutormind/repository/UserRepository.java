package mk.ukim.finki.tutormind.tutormind.repository;

import mk.ukim.finki.tutormind.tutormind.model.User;
import mk.ukim.finki.tutormind.tutormind.model.projections.UserDetailsProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    @Query(value = "select username,name,surname,description from users where username = ?1", nativeQuery = true)
    UserDetailsProjection getUserDetailsByUsername(String username);

}
