package defender.mvc.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import defender.mvc.model.GameModel;
import defender.mvc.model.InvaderModel;

import java.io.IOException;
import java.util.Collections;

public class GameView {
    private TerminalScreen screen;
    private GameModel model;
    private SpaceshipView spaceshipView = new SpaceshipView();
    private InvaderView invaderView = new InvaderView();

    public GameView(GameModel model) {
        this.model = model;
    }

    public enum ACTION {UP, DOWN, ENTER}

    public ACTION getAction() throws IOException {
        KeyStroke key = screen.pollInput();
        if (key == null) return null;
        if (key.getKeyType() == KeyType.Enter) return ACTION.ENTER;
        if (key.getKeyType() == KeyType.ArrowUp) return ACTION.UP;
        if (key.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;
        return null;
    }

    public void startTerminalScreen() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(
                new TerminalSize(model.getWidth(), model.getHeight())
        ).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    public void draw() throws IOException {
        screen.clear();
        screen.newTextGraphics().putString(new TerminalPosition(model.getWidth() / 2, 0), String.valueOf(model.getScore()));
        spaceshipView.draw(screen, model.getSpaceshipModel());
        for (InvaderModel invaderModel : model.getTroop()) {
            invaderView.draw(screen, invaderModel);
        }
        screen.refresh();
    }


    public void beginScreen() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(model.getWidth() / 2 - 4, model.getHeight() / 2 - 3), "DEFENDER");
        graphics.setForegroundColor(TextColor.Factory.fromString("#DFFF33"));
        graphics.enableModifiers(SGR.BLINK);
        graphics.putString(new TerminalPosition(model.getWidth() / 2 - 11, model.getHeight() / 2), "PRESS <ENTER> TO BEGIN");
        screen.refresh();
    }

    public void overScreen() throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString("#D00000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(model.getWidth() / 2 - 4, 5), "GAME OVER!");


        model.getRecords().sort(Collections.reverseOrder());
        for (int i = 0; i < model.getRecords().size(); i++) {
            if (model.getRecords().get(i) == model.getScore()) {
                graphics.setForegroundColor(TextColor.Factory.fromString("#DFFF33"));
                graphics.enableModifiers(SGR.BLINK);
                graphics.putString(new TerminalPosition(model.getWidth() / 2 + 4, model.getHeight() / 2 - 3 + i), "NEW RECORD!!!");

            } else {
                graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
                graphics.disableModifiers(SGR.BLINK);
            }
            graphics.putString(new TerminalPosition(model.getWidth() / 2 - 2, model.getHeight() / 2 - 3 + i), String.valueOf(model.getRecords().get(i)));
        }
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BLINK);
        graphics.putString(new TerminalPosition(model.getWidth() / 2 - 11, model.getHeight() / 2 - 3 + 7), "PRESS <ENTER> TO RESTART");


        screen.refresh();
    }

    public GameModel getModel() {
        return model;
    }

    public void setModel(GameModel model) {
        this.model = model;
    }
}
