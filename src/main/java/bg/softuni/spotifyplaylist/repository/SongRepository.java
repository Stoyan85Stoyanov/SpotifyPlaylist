package bg.softuni.spotifyplaylist.repository;

import bg.softuni.spotifyplaylist.entity.Song;
import bg.softuni.spotifyplaylist.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {

    List<Song> findSongByStyle(Style style);

    @Query("SELECT s FROM Song s")
    List<Song> findAllSongs();

}
