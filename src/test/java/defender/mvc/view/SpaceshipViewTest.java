package defender.mvc.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import defender.mvc.model.Position;
import defender.mvc.model.SpaceshipModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpaceshipViewTest {

    @Test
    public void testDraw() {
        SpaceshipView spaceshipView = new SpaceshipView();
        TerminalScreen screen = mock(TerminalScreen.class);
        SpaceshipModel spaceshipModel = mock(SpaceshipModel.class);
        TextGraphics textGraphics = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(textGraphics);
        when(spaceshipModel.getPosition()).thenReturn(new Position(10, 15));

        spaceshipView.draw(screen, spaceshipModel);

        verify(textGraphics).setForegroundColor(any(TextColor.class));
        verify(textGraphics).enableModifiers(any(SGR.class));
        verify(textGraphics).putString(new TerminalPosition(10, 15), "◄");
    }
}
