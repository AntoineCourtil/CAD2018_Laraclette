package bataillenavale.game.menu;

import bataillenavale.game.Game;
import bataillenavale.game.GameState;
import bataillenavale.game.menu.Menu;

public class EpochChoose extends Menu {
    private Game game;

    public EpochChoose(Game game) {
        this.game = game;
        this.selectedIndex = 0;
        this.menuEntries = new String[3];
        menuEntries[0] = "XIX";
        menuEntries[1] = "XVIII";
        menuEntries[2] = "Retourner au menu";
    }

    @Override
    void handleEnter() {
        String selectedItem = menuEntries[selectedIndex];
        switch (selectedItem) {
            case "XIX":
                game.setCurrentEpoch("XIX");
                game.setGameState(GameState.RUNNING);
                break;

            case "XVIII":
                game.setCurrentEpoch("XVIII");
                game.setGameState(GameState.RUNNING);
                break;
            case "Retourner au menu":
                game.setGameState(GameState.MENU);
                break;
        }
    }
}
