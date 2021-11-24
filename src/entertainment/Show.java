package entertainment;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class Show extends Video {
    private final int numberOfSeasons;
    private final ArrayList<Season> seasons;

    public Show(final String title, final List<String> cast, final List<String> genres,
                final int numberOfSeasons, final ArrayList<Season> seasons, final int year) {
        super(title, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    /**
     * @return average rating of a show
     */
    public Double getRating() {
        double ratingShow = 0;
        double ratingSeason = 0;
        for (Season season : seasons) {
            if (!season.getRatings().isEmpty()) {
                for (Double rating : season.getRatings()) {
                    ratingSeason += rating / season.getRatings().size();
                }
                ratingShow += ratingSeason / numberOfSeasons;
            }
        }
        return ratingShow;
    }

    @Override
    public int getDuration() {
        int duration = 0;
        for (int i = 0; i < numberOfSeasons; i++) {
            duration += getSeasons().get(i).getDuration();
        }
        return duration;
    }
}
