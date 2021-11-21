package actions;

import lombok.ToString;
import repository.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString
public class Query extends Action {
    private final String objectType;
    private final int number;
    private final String sortType;
    private final String criteria;
    private final int seasonNumber;
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
        this.seasonNumber = 0;
    }

    /**
     *
     * @return mesaj
     */
    public String execute(final Repository repo) {
        System.out.println(this.toString());
        return "e query";
    }
}
