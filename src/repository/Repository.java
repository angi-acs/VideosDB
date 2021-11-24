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
     * Add videos to the repository
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
     * Method that returns the instance of a user with a given username
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
     * Method that returns the instance of a video with a given title
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
     * After Rating Command, the grade should also be added to the repository
     * @param title of video for which the rating was given
     * @param seasonNumber (only the case for shows)
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
     * @return a map containing the title of a video and
     * the number of times it has been added to a favorite list
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
     * @return a map containing the title of a video and
     * the number of times it has been viewed (by all users)
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
