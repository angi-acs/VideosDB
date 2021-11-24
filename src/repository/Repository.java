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

import java.util.*;

@Getter
public final class Repository {
    private final ArrayList<Actor> actors = new ArrayList<>();
    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Movie> movies = new ArrayList<>();
    private final ArrayList<Show> shows = new ArrayList<>();
    private final ArrayList<Video> videos = new ArrayList<>();
    private final ArrayList<Action> actions = new ArrayList<>();

    public Repository(final Input input) {
        Helpers helpers = new Helpers();
        helpers.addData(input, this);
        this.addVideos();
    }

    /**
     * Add actors to the repository
     */
    public void addActors(final ArrayList<Actor> actorInput) {
        this.actors.addAll(actorInput);
    }

    /**
     * Add users to the repository
     */
    public void addUsers(final ArrayList<User> userInput) {
        this.users.addAll(userInput);
    }

    /**
     * Add movies to the repository
     */
    public void addMovies(final ArrayList<Movie> movieInput) {
        this.movies.addAll(movieInput);
    }

    /**
     * Add shows to the repository
     */
    public void addShows(final ArrayList<Show> showInput) {
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
    public void addActions(final ArrayList<Action> actionInput) {
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
     * a
     * @param title b
     * @return c
     */
    public Video findVideo(final String title) {
        for (Video video : videos) {
            if (video.getTitle().equals(title)) {
                return video;
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
                show.getSeasons().get(seasonNumber - 1).getRatings().add(grade);
                return;
            }
        }
    }

    /**
     * a
     * @return b
     */
    public LinkedHashMap<String, Double> getFavorites(final List<Video> videoList) {
        LinkedHashMap<String, Double> favorites = new LinkedHashMap<>();
        for (Video video : videoList) {
            favorites.put(video.getTitle(), (double) 0);
            for (User user : users) {
                if (user.getFavorites().contains(video.getTitle())) {
                    favorites.put(video.getTitle(), favorites.get(video.getTitle()) + 1);
                }
            }
        }
        return favorites;
    }

    /**
     * a
     * @param videoList b
     * @return c
     */
    public LinkedHashMap<String, Double> getViews(final List<Video> videoList) {
        LinkedHashMap<String, Double> views = new LinkedHashMap<>();
        for (Video video : videoList) {
            views.put(video.getTitle(), (double) 0);
            for (User user : users) {
                if (user.getHistory().containsKey(video.getTitle())) {
                    views.put(video.getTitle(),
                            views.get(video.getTitle()) + user.getHistory().get(video.getTitle()));
                }
            }
        }
        return views;
    }
}
