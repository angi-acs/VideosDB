package actions;

import lombok.Getter;
import repository.Repository;

@Getter
public abstract class Action {
    private final int actionId;

    public Action(final int actionId) {
        this.actionId = actionId;
    }

    /**
     * Method implemented in its subclasses
     */
    public abstract String execute(Repository repo);
}
