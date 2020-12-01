package defender.mvc.controller;

import defender.FileUtils;
import defender.mvc.model.GameModel;
import defender.mvc.model.InvaderModel;
import defender.mvc.view.GameView;

import java.io.IOException;
import java.util.List;

public class GameController {

    private GameView view;
    private GameModel model;
    private SpaceshipController spaceshipController;
    private InvaderController invadersController;
    private FileUtils fileUtils = new FileUtils();

    public GameController() {
        this.model = new GameModel(60, 30);
        this.view = new GameView(model);
    }

    public GameView getView() {
        return view;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public GameModel getModel() {
        return model;
    }

    public void setModel(GameModel model) {
        this.model = model;
    }

    public SpaceshipController getSpaceshipController() {
        if (spaceshipController == null) {
            spaceshipController = new SpaceshipController(model.getSpaceshipModel());
        }
        return spaceshipController;
    }

    public InvaderController getInvadersController() {
        if (invadersController == null) {
            invadersController = new InvaderController(20, 5, model.getWidth(), model.getHeight());
        }
        return invadersController;
    }

    public void addScore(int value) {
        model.setScore(model.getScore() + value);
    }

    void updateRecords() {
        List<Long> records = fileUtils.readScore();
        for (Long record : records) {
            if (model.getScore() > record) {
                model.setNewRecord(true);
                break;
            }
        }
        records.add(model.getScore());
        records.sort(null);
        records.remove(0);
        model.setRecords(records);
        fileUtils.writeScore(records);
    }

    public void beginController() throws IOException {
        view.startTerminalScreen();
        view.beginScreen();
        GameView.ACTION action = null;
        while (action != GameView.ACTION.ENTER) {
            action = view.getAction();
        }
    }

    public void overController() throws IOException {
        updateRecords();
        view.overScreen();
        GameView.ACTION action = null;
        while (action != GameView.ACTION.ENTER) {
            action = view.getAction();
        }
        reset();
    }

    private void reset() {
        model = new GameModel(model.getWidth(), model.getHeight());
        view.setModel(model);
        spaceshipController = null;
        invadersController = null;
    }

    public void runnigController() {

        while (!collision()) {
            addScore(5);
            try {
                getInvadersController().invasion(model.getTroop());
                GameView.ACTION action = getView().getAction();
                if (action != null) {
                    getSpaceshipController().executeAction(action);
                }
                view.draw();
                Thread.sleep(180);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean collision() {
        for (InvaderModel invaderModel : model.getTroop()) {
            int spaceship_x = model.getSpaceshipModel().getPosition().getX();
            int spaceship_y = model.getSpaceshipModel().getPosition().getY();
            int invader_x = invaderModel.getPosition().getX();
            int invader_y = invaderModel.getPosition().getY();
            if ((invader_y == spaceship_y) && (invader_x + invaderModel.getSpeed() >= spaceship_x)) {
                return true;
            }
        }
        return false;
    }


}











