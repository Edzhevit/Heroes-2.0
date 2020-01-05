package softuni.heroes.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.heroes.data.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
