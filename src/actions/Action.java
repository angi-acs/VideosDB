package actions;

import repository.Repository;

public abstract class Action {
    private final int actionId;

    public Action(final int actionId) {
        this.actionId = actionId;
    }

    /**
     * Method implemented in its subclasses
     */
    public abstract String execute(Repository repo);

    public final int getActionId() {
        return actionId;
    }
}
