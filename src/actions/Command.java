package actions;

import common.Constants;
import repository.Repository;
import user.User;

import java.util.ArrayList;

public class Command extends Action {
    private final String type;
    private final String username;
    private final String title;
    private final double grade;
    private final int seasonNumber;

    public Command(final int actionId, final String type, final String username,
                          final String title, final Double grade, final int seasonNumber) {
        super(actionId);
        this.type = type;
        this.grade = grade;
        this.username = username;
        this.title = title;
        this.seasonNumber = seasonNumber;
    }

    /**
     *
     * @param repo The repository that contains the database
     * @return The string
     */
    public String execute(final Repository repo) {
        User user = repo.findUser(this.username);
        if (user == null) {
            return null;
        }

        switch (this.type) {
            case Constants.FAVORITE -> {
                return favorite(user);
            }
            case Constants.VIEW -> {
                return view(user);
            }
            case Constants.RATING -> {
                return rating(repo, user);
            }
            default -> {
                return null;
            }
        }
    }

    /**
     * a
     * @param user a
     * @return a
     */
    public String favorite(final User user) {
        if (!user.getHistory().containsKey(this.title)) {
            return "error -> " + this.title + " is not seen";
        }
        if (user.getFavorites().contains(this.title)) {
            return "error -> " + this.title + " is already in favourite list";
        }
        user.getFavorites().add(this.title);
        return "success -> " + this.title + " was added as favourite";
    }

    /**
     * a
     * @param user a
     * @return a
     */
    public String view(final User user) {
        if (!user.getHistory().containsKey(this.title)) {
            user.getHistory().put(this.title, 0);
        }
        user.getHistory().put(this.title, user.getHistory().get(this.title) + 1);
        return "success -> " + this.title + " was viewed with total views of "
                + user.getHistory().get(this.title);
    }

    /**
     * a
     * @param user a
     * @return a
     */
    public String rating(final Repository repo, final User user) {
        if (!user.getHistory().containsKey(this.title)) {
            return "error -> " + this.title + " is not seen";
        }
        if (user.getRatings().containsKey(this.title)) {
            if (this.seasonNumber == 0) {
                return "error -> " + this.title + " has been already rated";
            } else {
                ArrayList<Integer> seasons = user.getRatings().get(this.title);
                for (Integer i : seasons) {
                    if (i == this.seasonNumber) {
                        return "error -> " + this.title + " has been already rated";
                    }
                }
            }
        }

        ArrayList<Integer> seasons;
        if (!user.getRatings().containsKey(this.title)) {
            seasons = new ArrayList<>();
        } else {
            seasons = user.getRatings().get(this.title);
        }
        seasons.add(this.seasonNumber);
        user.getRatings().put(this.title, seasons);

        repo.addRating(this.title, this.grade, this.seasonNumber);
        return "success -> " + this.title + " was rated with " + this.grade
                + " by " + this.username;
    }
}
