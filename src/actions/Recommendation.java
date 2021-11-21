package actions;

import common.Constants;
import entertainment.Genre;
import entertainment.Video;
import repository.Repository;
import user.User;

import java.util.*;

public class Recommendation extends Action {
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
     *
     * @return mesaj
     */
    public String execute(final Repository repo) {
        User user = repo.findUser(this.username);
        if (user == null) {
            return null;
        }
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
            default -> {
                return null;
            }
        }
    }

    /**
     *
     * @param repo a
     * @param user b
     * @return c
     */
    public String standard(final Repository repo, final User user) {
        for (Video video : repo.getVideos()) {
            if (!user.getHistory().containsKey(video.getTitle())) {
                return "StandardRecommendation result: " + video.getTitle();
            }
        }
        return "StandardRecommendation cannot be applied!";
    }

    /**
     *
     * @param repo a
     * @param user b
     * @return c
     */
    public String bestUnseen(final Repository repo, final User user) {
        List<Video> sortedList = repo.getVideos();
        sortedList.sort((o1, o2) -> Double.compare(o2.getRating(), o1.getRating()));
        for (Video video : sortedList) {
            if (!user.getHistory().containsKey(video.getTitle())) {
                return "BestRatedUnseenRecommendation result: " + video.getTitle();
            }
        }
        return "BestRatedUnseenRecommendation cannot be applied!";
    }

    /**
     * a
     * @param repo b
     * @param user c
     * @return d
     */
    public String popular(final Repository repo, final User user) {
        if (!user.getSubscriptionType().equals(Constants.PREMIUM)) {
            return "PopularRecommendation cannot be applied!";
        }

        LinkedHashMap<String, Integer> genres = new LinkedHashMap<>();
        for (Genre g : Genre.values()) {
            genres.put(g.toString(), 0);
        }
        for (Video video : repo.getVideos()) {
            for (User u : repo.getUsers()) {
                for (Map.Entry<String, Integer> entry : u.getHistory().entrySet()) {
                    if (video.getTitle().equals(entry.getKey())) {
                        for (String g : video.getGenres()) {
                            genres.put(g, 0);
                            genres.put(g, genres.get(g) + entry.getValue());
                        }
                    }
                }
            }
        }

        ArrayList<Map.Entry<String, Integer>> sortedGenres = new ArrayList<>(genres.entrySet());
        sortedGenres.sort((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));

        for (Map.Entry<String, Integer> entry : sortedGenres) {
            for (Video video : repo.getVideos()) {
                if (video.getGenres().contains(entry.getKey())
                        && !user.getHistory().containsKey(video.getTitle())) {
                    return "PopularRecommendation result: " + video.getTitle();
                }
            }
        }
        return "PopularRecommendation cannot be applied!";
    }

    /**
     * a
     * @param repo b
     * @param user c
     * @return d
     */
    public String favorite(final Repository repo, final User user) {
        if (!user.getSubscriptionType().equals(Constants.PREMIUM)) {
            return "FavoriteRecommendation cannot be applied!";
        }

        LinkedHashMap<String, Integer> favorites = new LinkedHashMap<>();
        for (User u : repo.getUsers()) {
            for (String f : u.getFavorites()) {
                favorites.put(f, 0);
                favorites.put(f, favorites.get(f) + 1);
            }
        }

        ArrayList<Map.Entry<String, Integer>> sortedFavorites =
                new ArrayList<>(favorites.entrySet());
        sortedFavorites.sort((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));

        for (Map.Entry<String, Integer> entry : sortedFavorites) {
            if (!user.getHistory().containsKey(entry.getKey()) && entry.getValue() != 0) {
                return "FavoriteRecommendation result: " + entry.getKey();
            }
        }
        return "FavoriteRecommendation cannot be applied!";
    }

    /**
     * a
     * @param repo b
     * @param user c
     * @return d
     */
    public String search(final Repository repo, final User user) {
        if (!user.getSubscriptionType().equals(Constants.PREMIUM)) {
            return "SearchRecommendation cannot be applied!";
        }

        List<Video> genreVideos = new ArrayList<>();
        for (Video video : repo.getVideos()) {
            if (video.getGenres().contains(this.genre)
                    && !user.getHistory().containsKey(video.getTitle())) {
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
