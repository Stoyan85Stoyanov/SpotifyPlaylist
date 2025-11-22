package bg.softuni.spotifyplaylist.repository;

import bg.softuni.spotifyplaylist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserById(UUID id);


}
