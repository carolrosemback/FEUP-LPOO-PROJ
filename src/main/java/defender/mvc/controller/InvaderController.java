package defender.mvc.controller;

import defender.mvc.model.InvaderModel;
import defender.mvc.model.Position;

import java.util.List;
import java.util.Random;

public class InvaderController {
    private int troopQtd;
    private int maxSpeed;
    private int gameWidth;
    private int gameHeight;

    public InvaderController(int troopQuantity, int maxSpeed, int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.troopQtd = troopQuantity;
        this.maxSpeed = maxSpeed;
    }

    public void invasion(List<InvaderModel> troop) {
        makeInvaderMove(troop);
        removeInvader(troop);
        makeNewTroop(troop);
    }

    protected void makeNewTroop(List<InvaderModel> troop) {
        if (troop.size() < troopQtd) {
            Random random = new Random();
            troop.add(new InvaderModel(new Position(0, random.nextInt(gameHeight)), random.nextInt(maxSpeed) + 1));
        }
    }

    protected void makeInvaderMove(List<InvaderModel> troop) {
        for (InvaderModel invaderModel : troop) {
            invaderModel.setPosition(invaderModel.getPosition().moveLeft(invaderModel.getSpeed()));
        }
    }

    protected void removeInvader(List<InvaderModel> troop) {
        troop.removeIf(invaderModel -> invaderModel.getPosition().getX() >= gameWidth);
    }
}
