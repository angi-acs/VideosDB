package actions;

import common.Constants;
import entertainment.Video;
import repository.Repository;
import user.User;
import utils.Sort;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class Recommendation extends Action implements Sort {
    private final String type;
    private final String username;
    private final String genre;

    public Recommendation(final int actionId, final String type,
                          final String username, final String genre) {
        super(actionId);
        this.type = type;
        this.username = username;
        this.genre = genre;
    }

    /**
     * Method that switches between the Recommendation's types
     * @param repo The repository containing the database which
     *             will be transmitted to all the methods
     */
    public String execute(final Repository repo) {
        User user = repo.findUser(this.username);
        assert user != null;
        switch (this.type) {
            case Constants.STANDARD -> {
                return standard(repo, user);
            }
            case Constants.BEST_UNSEEN -> {
                return bestUnseen(repo, user);
            }
            case Constants.POPULAR -> {
                return popular(repo, user);
            }
            case Constants.FAVORITE -> {
                return favorite(repo, user);
            }
            case Constants.SEARCH -> {
                return search(repo, user);
            }
            default -> throw new IllegalStateException("Unexpected value: " + this.type);
        }
    }

    /**
     * @param user The user for whom the recommendation is applied
     * @return Message for Standard Recommendation
     */
    private String standard(final Repository repo, final User user) {
        for (Video video : repo.getVideos()) {
            if (!user.getHistory().containsKey(video.getTitle())) {
                return "StandardRecommendation result: " + video.getTitle();
            }
        }
        return "StandardRecommendation cannot be applied!";
    }

    /**
     * @param user The user for whom the recommendation is applied
     * @return Message for Best Unseen Recommendation
     */
    private String bestUnseen(final Repository repo, final User user) {
        ArrayList<Video> sortedList = new ArrayList<>(repo.getVideos());
        sortedList.sort((o1, o2) -> Double.compare(o2.getRating(), o1.getRating()));

        for (Video video : sortedList) {
            if (!user.getHistory().containsKey(video.getTitle())) {
                return "BestRatedUnseenRecommendation result: " + video.getTitle();
            }
        }
        return "BestRatedUnseenRecommendation cannot be applied!";
    }

    /**
     * @param user The user for whom the recommendation is applied
     * @return Message for Popular Recommendation
     */
    private String popular(final Repository repo, final User user) {
        if (!user.getSubscriptionType().equals(Constants.PREMIUM)) {
            return "PopularRecommendation cannot be applied!";
        }

        LinkedHashMap<String, Double> genres = new LinkedHashMap<>();
        for (User u : repo.getUsers()) {
            for (Map.Entry<String, Integer> entry : u.getHistory().entrySet()) {
                Video video = repo.findVideo(entry.getKey());
                assert video != null;
                for (String g : video.getGenres()) {
                    if (!genres.containsKey(g)) {
                        genres.put(g, (double) 0);
                    }
                    genres.put(g, genres.get(g) + entry.getValue());
                }
            }
        }
        ArrayList<String> result = sort(genres, "desc", genres.size());

        for (String s : result) {
            for (Video video : repo.getVideos()) {
                if (video.hasGenre(s) && !user.getHistory().containsKey(video.getTitle())) {
                    return "PopularRecommendation result: " + video.getTitle();
                }
            }
        }
        return "PopularRecommendation cannot be applied!";
    }

    /**
     * @param user The user for whom the recommendation is applied
     * @return Message for Favorite Recommendation
     */
    private String favorite(final Repository repo, final User user) {
        if (!user.getSubscriptionType().equals(Constants.PREMIUM)) {
            return "FavoriteRecommendation cannot be applied!";
        }

        LinkedHashMap<String, Double> favorites = repo.getFavorites(repo.getVideos());
        ArrayList<Map.Entry<String, Double>> sortedFavorites =
                new ArrayList<>(favorites.entrySet());
        sortedFavorites.sort((item1, item2) -> Double.compare(item2.getValue(), item1.getValue()));

        for (Map.Entry<String, Double> entry : sortedFavorites) {
            if (!user.getHistory().containsKey(entry.getKey())) {
                return "FavoriteRecommendation result: " + entry.getKey();
            }
        }
        return "FavoriteRecommendation cannot be applied!";
    }

    /**
     * @param user The user for whom the recommendation is applied
     * @return Message for Search Recommendation
     */
    private String search(final Repository repo, final User user) {
        if (!user.getSubscriptionType().equals(Constants.PREMIUM)) {
            return "SearchRecommendation cannot be applied!";
        }

        ArrayList<Video> genreVideos = new ArrayList<>();
        for (Video video : repo.getVideos()) {
            if (video.hasGenre(this.genre) && !user.getHistory().containsKey(video.getTitle())) {
                genreVideos.add(video);
            }
        }
        genreVideos.sort((o1, o2) -> {
            if (o1.getRating().equals(o2.getRating())) {
                return o1.getTitle().compareTo(o2.getTitle());
            } else {
                return Double.compare(o1.getRating(), (o2.getRating()));
            }
        });

        ArrayList<String> result = new ArrayList<>();
        for (Video video : genreVideos) {
            result.add(video.getTitle());
        }
        if (!genreVideos.isEmpty()) {
            return "SearchRecommendation result: " + result;
        }
        return "SearchRecommendation cannot be applied!";
    }
}
