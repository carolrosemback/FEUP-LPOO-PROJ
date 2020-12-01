package defender.mvc.controller;

import com.googlecode.lanterna.screen.TerminalScreen;
import defender.mvc.model.GameModel;
import defender.mvc.model.Position;
import defender.mvc.model.SpaceshipModel;
import defender.mvc.view.GameView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpaceshipControllerTest {

    @Mock
    Position position;

    @Mock
    SpaceshipModel spaceshipModel;

    @InjectMocks
    SpaceshipController spaceshipController;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecuteActionUp() {
        when(spaceshipModel.getPosition()).thenReturn(position);
        spaceshipController.executeAction(GameView.ACTION.UP);
        verify(position, times(1)).up();
    }

    @Test
    public void testExecuteActionDown() {

        when(spaceshipModel.getPosition()).thenReturn(position);
        spaceshipController.executeAction(GameView.ACTION.DOWN);
        verify(position, times(1)).down();
    }

    @Test
    public void testExecuteActionErro() {
        spaceshipController.executeAction(GameView.ACTION.ENTER); // action nao esperada
        verify(spaceshipModel, times(0)).getPosition();
    }


}
