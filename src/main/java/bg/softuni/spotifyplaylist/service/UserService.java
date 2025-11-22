package bg.softuni.spotifyplaylist.service;

import bg.softuni.spotifyplaylist.config.UserSession;
import bg.softuni.spotifyplaylist.dto.UserLoginDto;
import bg.softuni.spotifyplaylist.dto.UserRegisterDto;
import bg.softuni.spotifyplaylist.entity.Song;
import bg.softuni.spotifyplaylist.entity.User;
import bg.softuni.spotifyplaylist.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserSession userSession;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserSession userSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }

    public boolean register(UserRegisterDto data) {
        Optional<User> existingUser = userRepository
                .findByUsernameOrEmail(data.getUsername(), data.getEmail());

        if (existingUser.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));

        this.userRepository.save(user);

        return true;
    }

    public boolean login (UserLoginDto data){
        Optional<User> byUsername = userRepository.findUserByUsername(data.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        }

        boolean passMatch = passwordEncoder.matches(data.getPassword(), byUsername.get().getPassword());

        if (!passMatch) {
            return false;
        }

        //ВАЖНО: Когато се логвам - активирам сесия (UserSession userSession)
        // и поставям в тази сесия ID-то на потребителя!!!
        userSession.login(byUsername.get().getId(), byUsername.get().getUsername());

        return true;
    }

    @Transactional
    public Set<Song> findMyPlaylist(UUID id) {
        return userRepository.findUserById(id).map(User::getPlaylist).orElse(new HashSet<>());
    }
}
