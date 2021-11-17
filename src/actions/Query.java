package actions;

import repository.Repository;

public class Query extends Action {
    /*private final String objectType;
    private final String sortType;
    private final String criteria;
    private final String title;
    private final String genre;
    private final int number;
    private final int seasonNumber;
    private final List<List<String>> filters = new ArrayList<>();*/

    public Query(final int actionId) {
        super(actionId);
    }

    /*public Query(final int actionId, final String objectType, final String genre,
                 final String sortType, final String criteria, final String year,
                 final int number, final List<String> words, final List<String> awards) {
        super(actionId);
        this.objectType = objectType;
        this.sortType = sortType;
        this.criteria = criteria;
        this.number = number;
        this.filters.add(new ArrayList<>(Collections.singleton(year)));
        this.filters.add(new ArrayList<>(Collections.singleton(genre)));
        this.filters.add(words);
        this.filters.add(awards);
        this.title = null;
        this.genre = null;
        this.seasonNumber = 0;
    }*/

    /**
     *
     * @return mesaj
     */
    public String execute(final Repository repo) {
        return "e query";
    }
}
