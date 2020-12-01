package defender.mvc.model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private int width;
    private int height;

    private SpaceshipModel spaceshipModel;
    private List<InvaderModel> troop;
    private long score;
    private List<Long> records;
    private boolean newRecord;

    public GameModel(int width, int height) {
        this.width = width;
        this.height = height;

        this.spaceshipModel = new SpaceshipModel(new Position(width - 4, height / 2));
        this.troop = new ArrayList<>();
    }

    public SpaceshipModel getSpaceshipModel() {
        return spaceshipModel;
    }

    public List<InvaderModel> getTroop() {
        return troop;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public List<Long> getRecords() {
        return records;
    }

    public void setRecords(List<Long> records) {
        this.records = records;
    }

    public boolean isNewRecord() {
        return newRecord;
    }

    public void setNewRecord(boolean newRecord) {
        this.newRecord = newRecord;
    }
}
