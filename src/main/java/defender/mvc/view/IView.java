package defender.mvc.view;
import com.googlecode.lanterna.screen.TerminalScreen;

import defender.mvc.model.AbstractModel;

public interface IView {
    void draw(TerminalScreen screen, AbstractModel model);
}
