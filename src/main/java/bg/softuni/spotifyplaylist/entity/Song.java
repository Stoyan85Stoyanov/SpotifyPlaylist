package bg.softuni.spotifyplaylist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
}
