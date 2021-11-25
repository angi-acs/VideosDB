package entertainment;

import java.util.List;

public abstract class Video {
    private final String title;
    private final int year;
    private final List<String> cast;
    private final List<String> genres;

    public Video(final String title, final int year, final List<String> cast,
                 final List<String> genres) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
    }

    /**
     * Method implemented in its subclasses
     */
    public abstract Double getRating();

    /**
     * Method that returns the duration of a video
     * In class Movie is represented by the getter of the field duration
     * In class Show is overridden
     */
    public abstract int getDuration();

    /**
     * Method that checks if a video belongs to a specific genre
     * @param genre to be checked
     */
    public boolean hasGenre(final String genre) {
        for (String g : genres) {
            if (g.equals(genre)) {
                return true;
            }
        }
        return false;
    }

    public final String getTitle() {
        return title;
    }

    public final int getYear() {
        return year;
    }

    public final List<String> getCast() {
        return cast;
    }

    public final List<String> getGenres() {
        return genres;
    }
}
