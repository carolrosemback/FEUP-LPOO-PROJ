package defender.mvc.model;

public abstract class AbstractModel {
    private Position position;

    public AbstractModel(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
