package entertainment;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public final class Show extends Video {
    private final int numberOfSeasons;
    private final ArrayList<Season> seasons;

    public Show(final String title, final ArrayList<String> cast,
                           final ArrayList<String> genres,
                           final int numberOfSeasons, final ArrayList<Season> seasons,
                           final int year) {
        super(title, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }
}
