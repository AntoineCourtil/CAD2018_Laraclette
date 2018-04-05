package bataillenavale.game;

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
            case "Retourner au menu":
                game.setGameState(GameState.MENU);
                break;
        }
    }
}
