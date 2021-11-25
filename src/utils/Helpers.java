package utils;

import actions.Action;
import actions.Command;
import actions.Query;
import actions.Recommendation;
import actor.Actor;
import common.Constants;
import entertainment.Movie;
import entertainment.Show;

import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.Input;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
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
     * Add actors from Actors' input to the repository
     */
    public void addActors(final List<ActorInputData> actorList, final Repository repo) {
        ArrayList<Actor> actors = new ArrayList<>();
        for (ActorInputData actorData : actorList) {
            Actor actor = new Actor(actorData.getName(), actorData.getCareerDescription(),
                    actorData.getFilmography(), actorData.getAwards());
            actors.add(actor);
        }
        repo.addActors(actors);
    }

    /**
     * Add users from Users' input to the repository
     */
    public void addUsers(final List<UserInputData> userList, final Repository repo) {
        ArrayList<User> users = new ArrayList<>();
        for (UserInputData userData : userList) {
            User user = new User(userData.getUsername(), userData.getSubscriptionType(),
                    userData.getHistory(), userData.getFavoriteMovies());
            users.add(user);
        }
        repo.addUsers(users);
    }

    /**
     * Add movies from Movies' input to the repository
     */
    public void addMovies(final List<MovieInputData> movieList, final Repository repo) {
        ArrayList<Movie> movies = new ArrayList<>();
        for (MovieInputData movieData : movieList) {
            Movie movie = new Movie(movieData.getTitle(), movieData.getCast(),
                    movieData.getGenres(), movieData.getYear(), movieData.getDuration());
            movies.add(movie);
        }
        repo.addMovies(movies);
    }

    /**
     * Add shows from Serials' input to the repository
     */
    public void addShows(final List<SerialInputData> showList, final Repository repo) {
        ArrayList<Show> shows = new ArrayList<>();
        for (SerialInputData showData : showList) {
            Show show = new Show(showData.getTitle(), showData.getCast(),
                    showData.getGenres(), showData.getNumberSeason(),
                    showData.getSeasons(), showData.getYear());
            shows.add(show);
        }
        repo.addShows(shows);
    }

    /**
     * Add actions from Actions' input to the repository
     * Create new Action subclasses based on ActionType
     */
    public void addActions(final List<ActionInputData> actionList, final Repository repo) {
        ArrayList<Action> actions = new ArrayList<>();
        for (ActionInputData actionData : actionList) {

            switch (actionData.getActionType()) {
                case Constants.COMMAND -> {
                    Command command = new Command(actionData.getActionId(),
                            actionData.getType(), actionData.getUsername(), actionData.getTitle(),
                            actionData.getGrade(), actionData.getSeasonNumber());
                    actions.add(command);
                }
                case Constants.QUERY -> {
                    Query query = new Query(actionData.getActionId(),
                            actionData.getObjectType(), actionData.getNumber(),
                            actionData.getSortType(), actionData.getCriteria(),
                            actionData.getFilters().get(Constants.YEAR_FILTER).get(0),
                            actionData.getFilters().get(Constants.GENRE_FILTER).get(0),
                            actionData.getFilters().get(Constants.WORDS_FILTER),
                            actionData.getFilters().get(Constants.AWARDS_FILTER));
                    actions.add(query);
                }
                case Constants.RECOMMENDATION -> {
                    Recommendation recommendation = new Recommendation(actionData.getActionId(),
                            actionData.getType(), actionData.getUsername(), actionData.getGenre());
                    actions.add(recommendation);
                }
                default -> throw new
                        IllegalStateException("Unexpected value: " + actionData.getActionType());
            }
        }
        repo.addActions(actions);
    }
}
