package defender.mvc.controller;

import defender.mvc.model.InvaderModel;
import defender.mvc.model.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InvaderControllerTest {

    int troopQtd = 5;
    int maxSpeed = 3;
    int gameWidth = 60;
    int gameHeight = 60;

    List<InvaderModel> troop = new ArrayList<>();
    InvaderController invaderController;

    @Before
    public void init() {
        troop.add(new InvaderModel(new Position(5, 5), 2));
        troop.add(new InvaderModel(new Position(10, 10), 3));
        troop.add(new InvaderModel(new Position(10, 15), 4));
        invaderController = new InvaderController(troopQtd, maxSpeed, gameWidth, gameHeight);
    }

    @Test
    public void invasionTest() {
        int coordX1 = troop.get(0).getPosition().getX() + troop.get(0).getSpeed();
        int coordX2 = troop.get(1).getPosition().getX() + troop.get(1).getSpeed();
        int coordX3 = troop.get(2).getPosition().getX() + troop.get(2).getSpeed();

        invaderController.invasion(troop);

        Assert.assertEquals(coordX1, troop.get(0).getPosition().getX());
        Assert.assertEquals(coordX2, troop.get(1).getPosition().getX());
        Assert.assertEquals(coordX3, troop.get(2).getPosition().getX());
        Assert.assertEquals(4, troop.size());
        Assert.assertEquals(0, troop.get(3).getPosition().getX());
        Assert.assertTrue(troop.get(3).getPosition().getY() <= gameHeight);
        Assert.assertTrue(troop.get(3).getSpeed() <= maxSpeed);
    }

    @Test
    public void makeNewTroopTest() {
        invaderController.makeNewTroop(troop);
        Assert.assertEquals(4, troop.size());
    }

    // quando o limite da tropa e atigindo nao adiciona
    @Test
    public void makeNewTroopMaxQuantityTest() {
        troop.add(new InvaderModel(new Position(11, 10), 2));
        troop.add(new InvaderModel(new Position(12, 15), 2));
        invaderController.makeNewTroop(troop);
        Assert.assertEquals(5, troop.size());

    }

    @Test
    public void makeInvaderMoveTest() {
        int coordX1 = troop.get(0).getPosition().getX() + troop.get(0).getSpeed();
        int coordX2 = troop.get(1).getPosition().getX() + troop.get(1).getSpeed();
        int coordX3 = troop.get(2).getPosition().getX() + troop.get(2).getSpeed();
        invaderController.makeInvaderMove(troop);

        Assert.assertEquals(coordX1, troop.get(0).getPosition().getX());
        Assert.assertEquals(coordX2, troop.get(1).getPosition().getX());
        Assert.assertEquals(coordX3, troop.get(2).getPosition().getX());
    }

    // N há necessidade de tirar um invaderModel
    @Test
    public void removeInvaderDontRemoveTest() {
        invaderController.removeInvader(troop);
        Assert.assertEquals(3, troop.size());
    }

    @Test
    public void removeInvaderRemoveTest() {
        troop.get(0).setPosition(new Position(gameWidth + 1, 5));
        invaderController.removeInvader(troop);
        Assert.assertEquals(2, troop.size());
    }


}