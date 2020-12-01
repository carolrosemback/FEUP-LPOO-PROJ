package defender.state;

import defender.mvc.controller.GameController;

import java.io.IOException;

public class Over implements State {

    @Override
    public void execute(DefenderContext context)  {
        try {
            context.getController().overController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nextState(DefenderContext context) {
        context.setState(new Running());
    }
}
