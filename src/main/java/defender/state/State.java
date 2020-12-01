package defender.state;

public interface State {
    void execute(DefenderContext context);

    void nextState(DefenderContext context);
}
