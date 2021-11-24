package entertainment;

import lombok.Getter;

import java.util.List;

@Getter
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
     *
     * @return a
     */
    public abstract Double getRating();

    /**
     * a
     * @return b
     */
    public abstract int getDuration();

    /**
     * a
     * @param genre b
     * @return c
     */
    public String findGenre(final String genre) {
        for (String g : genres) {
            if (g.equals(genre)) {
                return g;
            }
        }
        return null;
    }
}
