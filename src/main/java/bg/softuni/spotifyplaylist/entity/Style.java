package bg.softuni.spotifyplaylist.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private StyleName styleName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    public Style() {
    }

    public Style(StyleName name, String description) {
        this.styleName = name;
        this.description = description;
    }

    public StyleName getStyleName() {
        return styleName;
    }

    public void setStyleName(StyleName styleName) {
        this.styleName = styleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
