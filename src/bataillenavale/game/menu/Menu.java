package bataillenavale.game.menu;

import bataillenavale.engine.Cmd;
import bataillenavale.game.Painter;

import java.awt.*;

public abstract class Menu {

    protected String[] menuEntries;
    protected int selectedIndex;

    public void evolve(Cmd cmd) {
        if (cmd == Cmd.UP) {
            selectedIndex--;
            if(selectedIndex == -1) selectedIndex = menuEntries.length - 1;

        } else if (cmd == Cmd.DOWN) {
            selectedIndex++;
            if (selectedIndex == menuEntries.length) selectedIndex = 0;
        } else if (cmd == Cmd.ENTER) {
            handleEnter();
        }
    }

    public void paint(Graphics2D crayon) {
        crayon.setFont(new Font(" Serif ", Font.PLAIN, 25)); // restore font
        for (int i = 0; i < menuEntries.length; i++) {
            if (i == selectedIndex) {
                crayon.setColor(Color.blue);
                crayon.drawString(menuEntries[i], Painter.WIDTH / 2 - 100, Painter.HEIGHT / 2 + i * 30);
            } else {
                crayon.setColor(Color.black);
                crayon.drawString(menuEntries[i], Painter.WIDTH / 2 - 100, Painter.HEIGHT / 2 + i * 30);
            }

        }
    }

    abstract void handleEnter();
}
