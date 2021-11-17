package utils;

import actions.Action;
import actions.Command;
import actions.Query;
import actions.Recommendation;
import actor.Actor;
import common.Constants;
import entertainment.Movie;
import entertainment.Show;
import fileio.*;
import repository.Repository;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class Helpers {

    /**
     * Add data from input to the repository
     */
    public void addData(final Input input, final Repository repo) {
        addActors(input.getActors(), repo);
        addUsers(input.getUsers(), repo);
        addMovies(input.getMovies(), repo);
        addShows(input.getSerials(), repo);
        addActions(input.getCommands(), repo);
    }

    /**
     * Add actors from input to the repository
     */
    public void addActors(final List<ActorInputData> actorList, final Repository repo) {
        List<Actor> actors = new ArrayList<>();
        for (ActorInputData actorData : actorList) {
            Actor actor = new Actor(actorData.getName(), actorData.getCareerDescription(),
                    actorData.getFilmography(), actorData.getAwards());
            actors.add(actor);
        }
        repo.addActors(actors);
    }

    /**
     * Add users from input to the repository
     */
    public void addUsers(final List<UserInputData> userList, final Repository repo) {
        List<User> users = new ArrayList<>();
        for (UserInputData userData : userList) {
            User user = new User(userData.getUsername(), userData.getSubscriptionType(),
                    userData.getHistory(), userData.getFavoriteMovies());
            users.add(user);
        }
        repo.addUsers(users);
    }

    /**
     * Add movies from input to the repository
     */
    public void addMovies(final List<MovieInputData> movieList, final Repository repo) {
        List<Movie> movies = new ArrayList<>();
        for (MovieInputData movieData : movieList) {
            Movie movie = new Movie(movieData.getTitle(), movieData.getCast(),
                    movieData.getGenres(), movieData.getYear(), movieData.getDuration());
            movies.add(movie);
        }
        repo.addMovies(movies);
    }

    /**
     * Add shows from input to the repository
     */
    public void addShows(final List<SerialInputData> showList, final Repository repo) {
        List<Show> shows = new ArrayList<>();
        for (SerialInputData showData : showList) {
            Show show = new Show(showData.getTitle(), showData.getCast(),
                    showData.getGenres(), showData.getNumberSeason(),
                    showData.getSeasons(), showData.getYear());
            shows.add(show);
        }
        repo.addShows(shows);
    }

    /**
     * Add actions from input to the repository
     */
    public void addActions(final List<ActionInputData> actionList, final Repository repo) {
        List<Action> actions = new ArrayList<>();
        for (ActionInputData actionData : actionList) {

            switch (actionData.getActionType()) {
                case Constants.COMMAND -> {
                    Command command = new Command(actionData.getActionId(),
                            actionData.getType(), actionData.getUsername(), actionData.getTitle(),
                            actionData.getGrade(), actionData.getSeasonNumber());
                    actions.add(command);
                }
                case Constants.QUERY -> {
                    Query query = new Query(actionData.getActionId());
                    actions.add(query);
                }
                case Constants.RECOMMENDATION -> {
                    Recommendation recommendation = new Recommendation(actionData.getActionId(),
                            actionData.getType(), actionData.getUsername(), actionData.getGenre());
                    actions.add(recommendation);
                }
                default -> {
                }
            }
        }
        repo.addActions(actions);
    }
}
