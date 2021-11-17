package actions;

import lombok.Getter;
import repository.Repository;

@Getter
public class Action {
    private final int actionId;

    public Action(final int actionId) {
        this.actionId = actionId;
    }

    /**
     *
     * @return mesaj
     */
    public String execute(final Repository repo) {
        return null;
    }
}
