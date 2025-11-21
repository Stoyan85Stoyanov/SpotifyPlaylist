package bg.softuni.spotifyplaylist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "styles")
public class Style extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private StyleName styleName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;


    public Style(StyleName name, String description) {
        this.styleName = name;
        this.description = description;
    }
}
