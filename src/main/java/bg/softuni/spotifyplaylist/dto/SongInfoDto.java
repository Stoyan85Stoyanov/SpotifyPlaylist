package bg.softuni.spotifyplaylist.dto;

import bg.softuni.spotifyplaylist.entity.Song;

import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
