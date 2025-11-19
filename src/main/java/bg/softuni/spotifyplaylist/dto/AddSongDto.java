package bg.softuni.spotifyplaylist.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
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
}
