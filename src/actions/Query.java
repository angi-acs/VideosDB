package actions;

import actor.Actor;
import common.Constants;
import entertainment.Video;
import repository.Repository;
import user.User;
import utils.Utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Query extends Action implements Sort {
    private final String objectType;
    private final int number;
    private final String sortType;
    private final String criteria;
    private final List<List<String>> filters = new ArrayList<>();

    public Query(final int actionId, final String objectType, final int number,
                 final String sortType, final String criteria, final String year,
                 final String genre, final List<String> words, final List<String> awards) {
        super(actionId);
        this.objectType = objectType;
        this.sortType = sortType;
        this.criteria = criteria;
        this.number = number;
        this.filters.add(new ArrayList<>(Collections.singleton(year)));
        this.filters.add(new ArrayList<>(Collections.singleton(genre)));
        this.filters.add(words);
        this.filters.add(awards);
    }

    /**
     *
     * @return mesaj
     */
    public String execute(final Repository repo) {
        switch (this.objectType) {
            case Constants.ACTORS -> {
                switch (this.criteria) {
                    case Constants.AVERAGE -> {
                        return "Query result: " + actorsAverage(repo);
                    }
                    case Constants.AWARDS -> {
                        return "Query result: " + actorsAwards(repo);
                    }
                    case Constants.FILTER_DESCRIPTIONS -> {
                        return "Query result: " + actorWords(repo);
                    }
                    default ->
                            throw new IllegalStateException("Unexpected value: " + this.criteria);
                }
            }
            case Constants.MOVIES, Constants.SHOWS -> {
                return "Query result: " + videos(repo);
            }
            case Constants.USERS -> {
                return "Query result: " + users(repo);
            }
            default -> {
                return null;
            }
        }
    }

    /**
     * a
     * @param repo b
     * @return c
     */
    public ArrayList<String> actorsAverage(final Repository repo) {
        LinkedHashMap<String, Double> ratedActors = new LinkedHashMap<>();
        for (Actor actor : repo.getActors()) {
            ratedActors.put(actor.getName(), (double) 0);
            int ratedVideos = 0;
            for (String title : actor.getFilmography()) {
                Video video = repo.findVideo(title);
                if (video != null && video.getRating() != 0) {
                    ratedActors.put(actor.getName(),
                            ratedActors.get(actor.getName()) + video.getRating());
                    ratedVideos++;
                }
            }
            if (ratedVideos != 0) {
                ratedActors.put(actor.getName(), ratedActors.get(actor.getName()) / ratedVideos);
            }
        }
        return sort(ratedActors, this.sortType, this.number);
    }

    /**
     * a
     * @param repo b
     * @return c
     */
    public ArrayList<String> actorsAwards(final Repository repo) {
        List<String> awardsMust = filters.get(Constants.AWARDS_FILTER);
        LinkedHashMap<String, Double> awards = new LinkedHashMap<>();

        for (Actor actor : repo.getActors()) {
            awards.put(actor.getName(), (double) 0);
            for (String award : awardsMust) {
                if (!actor.getAwards().containsKey(Utils.stringToAwards(award))) {
                    awards.put(actor.getName(), (double) 0);
                    break;
                } else {
                    awards.put(actor.getName(), awards.get(actor.getName())
                            + actor.getAwardsNumber());
                }
            }
        }
        return sort(awards, this.sortType, awards.size());
    }

    /**
     * a
     * @param repo b
     * @return c
     */
    public ArrayList<String> actorWords(final Repository repo) {
        List<String> words = filters.get(Constants.WORDS_FILTER);
        LinkedHashMap<String, Double> actors = new LinkedHashMap<>();

        for (Actor actor : repo.getActors()) {
            for (String word : words) {
                String patternString = "[ -]" + word.toLowerCase() + "[ .,]";
                Pattern pattern = Pattern.compile(patternString);
                Matcher matcher = pattern.matcher(actor.getCareerDescription().toLowerCase());
                if (!matcher.find()) {
                    actors.put(actor.getName(), (double) 0);
                    break;
                } else {
                    actors.put(actor.getName(), (double) 1);
                }
            }
        }
        return sort(actors, this.sortType, actors.size());
    }

    /**
     * a
     * @param repo b
     * @return c
     */
    public ArrayList<String> videos(final Repository repo) {
        List<Video> videos = new ArrayList<>();
        switch (this.objectType) {
            case Constants.MOVIES -> videos.addAll(repo.getMovies());
            case Constants.SHOWS -> videos.addAll(repo.getShows());
            default -> throw new IllegalStateException("Unexpected value: " + this.objectType);
        }

        videos = filter(videos, this.filters);
        LinkedHashMap<String, Double> mapToSort = new LinkedHashMap<>();
        if (videos.isEmpty()) {
            return sort(mapToSort, this.sortType, this.number);
        }

        switch (this.criteria) {
            case Constants.RATINGS -> {
                for (Video video : videos) {
                    mapToSort.put(video.getTitle(), video.getRating());
                }
            }
            case Constants.FAVORITE -> mapToSort = repo.getFavorites(videos);
            case Constants.LONGEST -> {
                for (Video video : videos) {
                    mapToSort.put(video.getTitle(), (double) video.getDuration());
                }
            }
            case Constants.MOST_VIEWED -> mapToSort = repo.getViews(videos);
            default -> throw new IllegalStateException("Unexpected value: " + this.criteria);
        }
        return sort(mapToSort, this.sortType, this.number);
    }

    /**
     * a
     * @param repo b
     * @return c
     */
    public ArrayList<String> users(final Repository repo) {
        LinkedHashMap<String, Double> ratingUsers = new LinkedHashMap<>();
        for (User user : repo.getUsers()) {
            ratingUsers.put(user.getUsername(), (double) user.getRatings().size());
        }
        return sort(ratingUsers, this.sortType, this.number);
    }
}
