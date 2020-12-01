package defender.mvc.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import defender.mvc.model.AbstractModel;


public class SpaceshipView implements IView {

    public void draw(TerminalScreen screen, AbstractModel model) {
        TextGraphics graphics = getTextGraphics(screen);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(model.getPosition().getX(), model.getPosition().getY()), "◄");
    }

    protected TextGraphics getTextGraphics(TerminalScreen screen) {
        return screen.newTextGraphics();
    }
}