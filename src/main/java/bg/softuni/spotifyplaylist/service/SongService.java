package bg.softuni.spotifyplaylist.service;

import bg.softuni.spotifyplaylist.config.UserSession;
import bg.softuni.spotifyplaylist.dto.AddSongDto;
import bg.softuni.spotifyplaylist.entity.Song;
import bg.softuni.spotifyplaylist.entity.Style;
import bg.softuni.spotifyplaylist.entity.StyleName;
import bg.softuni.spotifyplaylist.entity.User;
import bg.softuni.spotifyplaylist.repository.SongRepository;
import bg.softuni.spotifyplaylist.repository.StyleRepository;
import bg.softuni.spotifyplaylist.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SongService {

    private final UserSession userSession;
    private final UserRepository userRepository;
    private final StyleRepository styleRepository;
    private final SongRepository songRepository;

    public SongService(UserSession userSession, UserRepository userRepository, StyleRepository styleRepository, SongRepository songRepository) {
        this.userSession = userSession;
        this.userRepository = userRepository;
        this.styleRepository = styleRepository;
        this.songRepository = songRepository;
    }

    public boolean create(AddSongDto data) {

        if (!userSession.isLoggedIn()) {
            return false;
        }

        Optional<User> byId = userRepository.findById(userSession.id());

        if (byId.isEmpty()) {
            return false;
        }

        Optional<Style> byName = styleRepository.findByStyleName(StyleName.valueOf(data.getStyle()));

        if (byName.isEmpty()) {
            return false;
        }

        Song song = new Song();
        song.setPerformer(data.getPerformer());
        song.setTitle(data.getTitle());
        song.setReleaseDate(data.getReleaseDate());
        song.setDuration(data.getDuration());
        song.setStyle(byName.get());

        songRepository.save(song);
        return true;
    }

    public Map<StyleName, List<Song>> findAllByStyle() {
        Map<StyleName, List<Song>> result = new HashMap<>();

        List<Style> allStyles = styleRepository.findAll();

        for (Style style : allStyles) {
            List<Song> songs = songRepository.findSongByStyle(style);
            result.put(style.getStyleName(), songs);
        }
        return result;
    }

    //        public List<Song> listSongs(String username) {
//        User user = userRepository.findByUsername(username);
//        return songRepository.findAllSongs();
//    }
    public List<Song> listSongs() {
        return songRepository.findAllSongs();
    }

    @Transactional
    public void addToSong(UUID songId, UUID id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return;
        }

        Optional<Song> songOpt = songRepository.findById(songId);

        if (songOpt.isEmpty()) {
            return;
        }

        userOpt.get().addMyPlaylist(songOpt.get());
        userRepository.save(userOpt.get());
    }

}
