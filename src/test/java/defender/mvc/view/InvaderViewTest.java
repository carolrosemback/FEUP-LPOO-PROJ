package defender.mvc.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import defender.mvc.model.InvaderModel;
import defender.mvc.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class InvaderViewTest {
    @Test
    public void testDraw() {


        InvaderView invaderView = new InvaderView();
        InvaderModel invaderModel = mock(InvaderModel.class);
        TerminalScreen screen = mock(TerminalScreen.class);
        TextGraphics textGraphics = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(textGraphics);
        when(invaderModel.getPosition()).thenReturn(new Position(10, 15));

        invaderView.draw(screen, invaderModel);

        verify(textGraphics).setForegroundColor(any(TextColor.class));
        verify(textGraphics).enableModifiers(any(SGR.class));
        verify(textGraphics).putString(new TerminalPosition(10, 15), "◎");
    }
}
