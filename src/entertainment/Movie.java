package entertainment;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public final class Movie extends Video {
    private final int duration;

    public Movie(final String title, final ArrayList<String> cast,
                 final ArrayList<String> genres, final int year,
                 final int duration) {
        super(title, year, cast, genres);
        this.duration = duration;
    }
}
