package mk.ukim.finki.tutormind.tutormind.repository;

import mk.ukim.finki.tutormind.tutormind.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
