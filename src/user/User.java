package user;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
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
}
