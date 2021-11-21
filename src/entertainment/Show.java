package entertainment;

import lombok.Getter;

import java.util.List;

@Getter
public final class Show extends Video {
    private final int numberOfSeasons;
    private final List<Season> seasons;

    public Show(final String title, final List<String> cast, final List<String> genres,
                final int numberOfSeasons, final List<Season> seasons, final int year) {
        super(title, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    /**
     *
     * @return a
     */
    public Double getRating() {
        double ratingShow = 0;
        double ratingSeason = 0;
        for (Season season : seasons) {
            if (!season.getRatings().isEmpty()) {
                for (Double rating : season.getRatings()) {
                    ratingSeason += rating;
                }
                ratingSeason /= season.getRatings().size();
            }
            ratingShow += ratingSeason;
        }
        return ratingShow / numberOfSeasons;
    }
}
