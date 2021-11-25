package user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class User {
    private final String username;
    private final String subscriptionType;
    private final Map<String, Integer> history;
    private final ArrayList<String> favorites;
    private final Map<String, ArrayList<Integer>> ratings;

    public User(final String username, final String subscriptionType,
                         final Map<String, Integer> history,
                         final ArrayList<String> favorites) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.favorites = favorites;
        this.history = history;
        this.ratings = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public ArrayList<String> getFavorites() {
        return favorites;
    }

    public Map<String, ArrayList<Integer>> getRatings() {
        return ratings;
    }
}
