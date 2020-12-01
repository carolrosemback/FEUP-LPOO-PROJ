package defender.mvc.controller;

import defender.mvc.model.SpaceshipModel;
import defender.mvc.view.GameView;

public class SpaceshipController {
    private SpaceshipModel model;

    public SpaceshipController(SpaceshipModel model) {
        this.model = model;
    }

    public void executeAction(GameView.ACTION action) {
        if (action == GameView.ACTION.UP) {
            model.setPosition(model.getPosition().up());
        }
        if (action == GameView.ACTION.DOWN) {
            model.setPosition(model.getPosition().down());
        }
    }
}
