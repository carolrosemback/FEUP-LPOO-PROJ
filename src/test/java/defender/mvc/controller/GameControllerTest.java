package defender.mvc.controller;

import com.googlecode.lanterna.screen.TerminalScreen;
import defender.FileUtils;
import defender.mvc.model.GameModel;
import defender.mvc.model.InvaderModel;
import defender.mvc.model.Position;
import defender.mvc.model.SpaceshipModel;
import defender.mvc.view.GameView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {


    @Mock
    GameModel model;

    @Mock
    GameView view;

    @Mock
    SpaceshipController spaceshipController;

    @Mock
    InvaderController invadersController;

    @Mock
    FileUtils fileUtils;

    @InjectMocks
    GameController gameController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testCollisonFalse() {
        List<InvaderModel> list = new ArrayList<>();
        list.add(new InvaderModel(new Position(10, 10), 2));
        when(model.getTroop()).thenReturn(list);
        when(model.getSpaceshipModel()).thenReturn(new SpaceshipModel(new Position(15, 10)));

        Assert.assertEquals(false, gameController.collision());
    }

    @Test
    public void testCollisonTrue() {
        List<InvaderModel> list = new ArrayList<>();
        list.add(new InvaderModel(new Position(10, 10), 2));
        when(model.getTroop()).thenReturn(list);
        when(model.getSpaceshipModel()).thenReturn(new SpaceshipModel(new Position(10, 10)));

        Assert.assertEquals(true, gameController.collision());
    }

    @Test
    public void updateRecordsNoNewRocord() {
        List<Long> records = new ArrayList<>();
        records.add(200L);
        records.add(3505L);
        when(fileUtils.readScore()).thenReturn(records);
        when(model.getScore()).thenReturn(5L);

        gameController.updateRecords();

        verify(model,times(0)).setNewRecord(anyBoolean());

    }

    @Test
    public void updateRecordsWithNewRocord() {
        List<Long> records = new ArrayList<>();
        records.add(200L);
        records.add(3505L);
        when(fileUtils.readScore()).thenReturn(records);
        when(model.getScore()).thenReturn(5000L);

        gameController.updateRecords();

        verify(model,times(1)).setNewRecord(anyBoolean());

    }


    @Test
    public void addScoreTest() {
        when(model.getScore()).thenReturn(300L);
        gameController.addScore(5);
        verify(model,times(1) ).setScore(305L);

    }




}