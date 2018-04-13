package bataillenavale.game.menu;

import bataillenavale.game.Game;
import bataillenavale.game.GameState;

public class FinishedGame extends Menu {

    private Game game;

    public FinishedGame(Game game) {
        this.game = game;
        this.selectedIndex = 0;
        this.menuEntries = new String[3];
        menuEntries[0] = "Recommencer";
        menuEntries[1] = "Revenir au menu principal";
        menuEntries[2] = "Quitter";
    }

    @Override
    void handleEnter() {
        String selectedItem = menuEntries[selectedIndex];
        switch (selectedItem) {
            case "Recommencer":
                game.restart();
                game.setGameState(GameState.EPOCH_CHOOSE);
                break;
            case "Revenir au menu principal":
                game.setGameState(GameState.MENU);
                break;
            case "Quitter":
                System.exit(0);
                break;
        }
    }
}
