package bg.softuni.spotifyplaylist.controller;

import bg.softuni.spotifyplaylist.config.UserSession;
import bg.softuni.spotifyplaylist.dto.AddSongDto;
import bg.softuni.spotifyplaylist.service.SongService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class SongController {

    private final UserSession userSession;
    private final SongService songService;

    public SongController(UserSession userSession, SongService songService) {
        this.userSession = userSession;
        this.songService = songService;

    }

    @ModelAttribute("songData")
    public AddSongDto songData() {
        return new AddSongDto();
    }

    @GetMapping("/song-add")
    public String addSong() {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        return "song-add";
    }

    @PostMapping("/song-add")
    public String doAddSong(@Valid AddSongDto data,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes
    ) {

        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("songData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.songData", bindingResult);

            return "redirect:/song-add";
        }

        boolean success = songService.create(data);

        if (!success) {
            redirectAttributes.addFlashAttribute("songData", data);

            return "redirect:/song-add";
        }
        return "redirect:/home";
    }

    @GetMapping ("/home/add-to-myPlaylist/{songId}")
    private String addToMyPlaylist(@PathVariable UUID songId) {

        songService.addToSong(songId, userSession.id()); // търсим ID на песента, и ID на потребителя !!!!!
        return "redirect:/home";
    }
}
