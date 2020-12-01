package defender.state;

public class Running implements State {

    @Override
    public void execute(DefenderContext context) {
        context.getController().runnigController();
    }

    @Override
    public void nextState(DefenderContext context) {
        context.setState(new Over());
    }


}
