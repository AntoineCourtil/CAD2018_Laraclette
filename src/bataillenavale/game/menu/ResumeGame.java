package bataillenavale.game.menu;

import bataillenavale.game.Game;
import bataillenavale.game.GameState;
import bataillenavale.game.menu.Menu;

public class ResumeGame extends Menu {
    private Game game;

    public ResumeGame(Game game) {
        this.game = game;
        this.selectedIndex = 0;
        this.menuEntries = new String[2];
        menuEntries[0] = "Aucune partie sauvegardé";
        menuEntries[1] = "Retourner au menu";
    }

    @Override
    void handleEnter() {
        String selectedItem = menuEntries[selectedIndex];
        switch (selectedItem) {
            case "Retourner au menu":
                game.setGameState(GameState.MENU);
        }
    }
}
