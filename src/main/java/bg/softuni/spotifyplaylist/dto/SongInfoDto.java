package bg.softuni.spotifyplaylist.dto;

import bg.softuni.spotifyplaylist.entity.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SongInfoDto {


    private UUID id;

    private String performer;

    private String title;

    private Integer duration;

    public SongInfoDto(Song song) {
        this.id = song.getId();
        this.performer = song.getPerformer();
        this.title = song.getTitle();
        this.duration = song.getDuration();
    }
}
