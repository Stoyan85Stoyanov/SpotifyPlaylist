package bg.softuni.spotifyplaylist.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "songs")
public class Song extends BaseEntity{

    @Column(nullable = false)
    private String performer;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Integer duration;

    private LocalDate releaseDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Style style;

    public Song() {
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
