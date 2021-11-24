package entertainment;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class Movie extends Video {
    private final int duration;
    private final ArrayList<Double> ratings;

    public Movie(final String title, final List<String> cast,
                 final List<String> genres, final int year,
                 final int duration) {
        super(title, year, cast, genres);
        this.duration = duration;
        this.ratings = new ArrayList<>();
    }

    /**
     *
     * @return a
     */
    public Double getRating() {
        double rating = 0;
        if (ratings.isEmpty()) {
            return rating;
        }
        for (Double i : ratings) {
            rating += i;
        }
        return rating / ratings.size();
    }

}
