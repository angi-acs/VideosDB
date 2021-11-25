package entertainment;

import java.util.ArrayList;
import java.util.List;


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
     * @return average rating of a movie
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

    @Override
    public int getDuration() {
        return duration;
    }

    public ArrayList<Double> getRatings() {
        return ratings;
    }
}
