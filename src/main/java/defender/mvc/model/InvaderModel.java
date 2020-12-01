package defender.mvc.model;

public class InvaderModel extends AbstractModel {
    private int speed;

    public InvaderModel(Position position, int speed) {
        super(position);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
