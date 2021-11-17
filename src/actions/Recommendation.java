package actions;

import repository.Repository;

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
        return "e recomandare";
    }
}
