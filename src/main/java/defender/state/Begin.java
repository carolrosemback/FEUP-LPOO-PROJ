package defender.state;

import java.io.IOException;

public class Begin implements State {

    @Override
    public void execute(DefenderContext context) {
        try {
            context.getController().beginController();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void nextState(DefenderContext context) {
        context.setState(new Running());
    }
}
