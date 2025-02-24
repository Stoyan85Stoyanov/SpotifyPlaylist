package bg.softuni.spotifyplaylist.repository;

import bg.softuni.spotifyplaylist.entity.Style;
import bg.softuni.spotifyplaylist.entity.StyleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StyleRepository extends JpaRepository<Style, UUID> {

    Optional<Style> findByStyleName(StyleName name);
}
