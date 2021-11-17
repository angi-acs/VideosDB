package repository;

import actions.Action;
import actor.Actor;
import entertainment.Movie;
import entertainment.Show;
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
    private final List<Action> actions = new ArrayList<>();

    public Repository(final Input input) {
        Helpers helpers = new Helpers();
        helpers.addData(input, this);
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
}
