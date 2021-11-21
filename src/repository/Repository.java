package repository;

import actions.Action;
import actor.Actor;
import entertainment.Movie;
import entertainment.Show;
import entertainment.Video;
import fileio.Input;
import lombok.Getter;
import user.User;
import utils.Helpers;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class Repository {
    private final List<Actor> actors = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<Movie> movies = new ArrayList<>();
    private final List<Show> shows = new ArrayList<>();
    private final List<Video> videos = new ArrayList<>();
    private final List<Action> actions = new ArrayList<>();

    public Repository(final Input input) {
        Helpers helpers = new Helpers();
        helpers.addData(input, this);
        this.addVideos();
    }

    /**
     * Add actors to the repository
     */
    public void addActors(final List<Actor> actorInput) {
        this.actors.addAll(actorInput);
    }

    /**
     * Add users to the repository
     */
    public void addUsers(final List<User> userInput) {
        this.users.addAll(userInput);
    }

    /**
     * Add movies to the repository
     */
    public void addMovies(final List<Movie> movieInput) {
        this.movies.addAll(movieInput);
    }

    /**
     * Add shows to the repository
     */
    public void addShows(final List<Show> showInput) {
        this.shows.addAll(showInput);
    }

    /**
     * Database
     */
    public void addVideos() {
        this.videos.addAll(this.movies);
        this.videos.addAll(this.shows);
    }

    /**
     * Add actions to the repository
     */
    public void addActions(final List<Action> actionInput) {
        this.actions.addAll(actionInput);
    }

    /**
     * Find user by name
     * @param username a
     * @return b
     */
    public User findUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     *
     * @param title a
     * @param grade b
     * @param seasonNumber c
     */
    public void addRating(final String title, final double grade, final int seasonNumber) {
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                movie.getRatings().add(grade);
                return;
            }
        }
        for (Show show : shows) {
            if (show.getTitle().equals(title)) {
                if (seasonNumber < show.getNumberOfSeasons()) {
                    show.getSeasons().get(seasonNumber).getRatings().add(grade);
                }
            }
        }
    }
}
