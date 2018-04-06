package bataillenavale.game.menu;

import bataillenavale.game.Game;
import bataillenavale.game.GameState;

public class MainMenu extends Menu {

    private Game game;

    public MainMenu(Game game) {
        this.game = game;
        this.selectedIndex = 0;
        this.menuEntries = new String[3];
        menuEntries[0] = "Nouvelle partie";
        menuEntries[1] = "Reprendre une partie";
        menuEntries[2] = "Quitter";
    }

    @Override
    void handleEnter() {
        String selectedItem = menuEntries[selectedIndex];
        switch (selectedItem) {
            case "Nouvelle partie":
                game.setGameState(GameState.EPOCH_CHOOSE);
                break;
            case "Reprendre une partie":
                game.setGameState(GameState.RESUME_GAME);
                break;
            case "Quitter":
                System.exit(0);
                break;
        }
    }
}
