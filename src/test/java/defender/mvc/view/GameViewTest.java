package defender.mvc.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import defender.mvc.model.GameModel;
import defender.mvc.model.Position;
import defender.mvc.model.SpaceshipModel;
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
public class GameViewTest {

    @Mock
    GameModel model;

    @Mock
    SpaceshipView spaceshipView;

    @Mock
    TerminalScreen screen;

    @Mock
    InvaderView invadersTroopView;

    @InjectMocks
    GameView gameView;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDraw() throws IOException {
//        when(model.getInvaderModel()).thenReturn(new InvadersTroopModel());
        TextGraphics textGraphics = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(textGraphics);
        when(model.getWidth()).thenReturn(60);
        when(model.getScore()).thenReturn(1500L);
        when(model.getSpaceshipModel()).thenReturn(new SpaceshipModel(new Position(2, 3)));

        gameView.draw();
        verify(screen, times(1)).clear();
        verify(textGraphics,times(1)).putString(any(TerminalPosition.class), anyString());
//        verify(invadersTroopView, times(1)).draw(any(TerminalScreen.class), any(InvadersTroopModel.class));
        verify(spaceshipView, times(1)).draw(any(TerminalScreen.class), any(SpaceshipModel.class));
        verify(screen, times(1)).refresh();
    }
}
