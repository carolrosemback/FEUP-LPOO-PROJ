package defender;

import defender.mvc.controller.GameController;
import defender.mvc.model.GameModel;
import defender.mvc.view.GameView;
import defender.state.DefenderContext;

public class Main {
    public static void main(String[] args) {

        GameController controller = new GameController();
        DefenderContext context = new DefenderContext(controller);
        while (true) {
            context.execute();
            context.nextState();
        }
    }
}
