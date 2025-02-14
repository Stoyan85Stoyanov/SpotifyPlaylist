package bg.softuni.spotifyplaylist.dto;

import jakarta.validation.constraints.*;


import java.time.LocalDate;

public class AddSongDto {

    @NotBlank
    @Size(min = 3, max = 20)
    private String performer;

    @NotBlank
    @Size(min = 3, max = 20)
    private String title;

    @NotNull
    @Min(0)
    private Integer duration;

    @PastOrPresent
    private LocalDate releaseDate;

    @NotBlank
    private String style;

    public AddSongDto() {
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
