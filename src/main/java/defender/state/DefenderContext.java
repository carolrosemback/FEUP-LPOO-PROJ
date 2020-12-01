package defender.state;

import defender.mvc.controller.GameController;

public class DefenderContext {
    GameController controller;
    State state;

    public DefenderContext(GameController controller) {
        this.controller = controller;
        this.state = new Begin();
    }

    public GameController getController() {
        return controller;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void execute() {
        state.execute(this);
    }

    public void nextState() {
        state.nextState(this);
    }
}
