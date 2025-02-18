package bg.softuni.spotifyplaylist.controller;

import bg.softuni.spotifyplaylist.dto.SongInfoDto;
import bg.softuni.spotifyplaylist.entity.Song;
import bg.softuni.spotifyplaylist.entity.StyleName;
import bg.softuni.spotifyplaylist.service.SongService;
import bg.softuni.spotifyplaylist.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import bg.softuni.spotifyplaylist.config.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final UserSession userSession;
    private final SongService songService;
    private final UserService userService;

    public HomeController(UserSession userSession, SongService songService, UserService userService) {
        this.userSession = userSession;
        this.songService = songService;
        this.userService = userService;
    }


    @GetMapping("/")
    public String nonLoggedIndex() {

        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

//    @GetMapping("/home")
//    public String loggedInIndex() {
//
//        if (!userSession.isLoggedIn()) {
//            return "redirect:/";
//        }
//        return "index";
//    }

    @GetMapping("/home")
    @Transactional
    public String loggedInIndex(Model model) {

        List<SongInfoDto> myPlaylist = userService.findMyPlaylist(userSession.id()).stream().map(SongInfoDto::new).toList();

        model.addAttribute("myPlaylist", myPlaylist);

        int sum = 0;
        for (SongInfoDto song : myPlaylist) {
            sum += song.getDuration();
        }

        int min = sum / 60;
        int seconds = sum % 60;
        model.addAttribute("min", min);
        model.addAttribute("sec", seconds);


        if (!userSession.isLoggedIn()) {
            return "redirect:/";

        }

        Map<StyleName, List<Song>> allSongs = songService.findAllByStyle();

        List<SongInfoDto> myPlayList =
                userService.findMyPlaylist(userSession.id()).stream().map(SongInfoDto::new).toList();

        List<SongInfoDto> pop =
                allSongs.get(StyleName.POP).stream().map(SongInfoDto::new).toList();

        List<SongInfoDto> rock =
                allSongs.get(StyleName.ROCK).stream().map(SongInfoDto::new).toList();

        List<SongInfoDto> jazz =
                allSongs.get(StyleName.JAZZ).stream().map(SongInfoDto::new).toList();


        model.addAttribute("jazzData", jazz);
        model.addAttribute("rockData", rock);
        model.addAttribute("popData", pop);
        model.addAttribute("myPlaylistData", myPlayList);


        return "home";
    }
//    // Delete button !!!
//    @DeleteMapping("/playlist/{id}")
//    public String deletePlaylist(@PathVariable Long id) {
//        userService.delete(id);
//        return "redirect:/home";
//    }
}
