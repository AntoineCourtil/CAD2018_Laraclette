package bataillenavale.game.menu;

import bataillenavale.engine.Cmd;
import bataillenavale.game.ImageFactory;

import java.awt.*;

public abstract class Menu {

    private static final int OFFSET_LEFT = 50;
    private static final int OFFSET_TOP = 300;
    // Offset entre les éléments en fonction du nombre de choix dans le menu, à compléter
    private static final int[] OFFSET_BETWEEN = {0, 0, 120, 65};
    private static final Color DEFAULT_COLOR = new Color(0xff161B21);
    private static final Color SELECTED_COLOR = new Color(0xff394B61);

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

        crayon.drawImage(ImageFactory.getInstance().getBackground(), 0, 0, null);

        crayon.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        crayon.setFont(new Font(" Serif ", Font.PLAIN, 20)); // restore font
        for (int i = 0; i < menuEntries.length; i++) {
            if (i == selectedIndex) crayon.setColor(SELECTED_COLOR);
            else crayon.setColor(DEFAULT_COLOR);
            crayon.drawString(menuEntries[i], OFFSET_LEFT, OFFSET_TOP + i * OFFSET_BETWEEN[menuEntries.length]);

        }
    }

    abstract void handleEnter();
}
