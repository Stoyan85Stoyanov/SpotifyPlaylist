package bg.softuni.spotifyplaylist.init;

import bg.softuni.spotifyplaylist.entity.Style;
import bg.softuni.spotifyplaylist.entity.StyleName;
import bg.softuni.spotifyplaylist.repository.StyleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class InitService implements CommandLineRunner {

    private final Map<StyleName, String> careerInformation = Map.of(
            StyleName.POP, "Тhe style is POP",
            StyleName.ROCK, "Тhe style is ROCK",
            StyleName.JAZZ, "Тhe style is JAZZ"
    );

    private final StyleRepository styleRepository;

    public InitService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        long count = this.styleRepository.count();

        if (count > 0) {
            return;
        }

        List<Style> toInsert = Arrays.stream(StyleName.values())
                .map(name -> new Style(name, careerInformation.get(name))).toList();

        this.styleRepository.saveAll(toInsert);
    }
}
