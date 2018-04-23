package bataillenavale.game.menu;

import bataillenavale.game.Game;
import bataillenavale.game.GameState;
import bataillenavale.game.menu.Menu;
import bataillenavale.modele.BatailleNavale;

import javax.swing.*;
import java.io.*;

public class ResumeGame extends Menu {
    private Game game;
    private boolean hasChosed = false;
    private boolean fileChooserIsOpen = false;


    public ResumeGame(Game game) {
        this.game = game;
        this.selectedIndex = 0;
        this.menuEntries = new String[2];
        menuEntries[0] = "Choisir un fichier";
        menuEntries[1] = "Retourner au menu";
    }

    @Override
    void handleEnter() {
        String selectedItem = menuEntries[selectedIndex];
        switch (selectedItem) {
            case "Choisir un fichier":
                load();
                break;
            case "Retourner au menu":
                game.setGameState(GameState.MENU);
                hasChosed = false;
                break;
        }
    }

    private void load() {
        if (hasChosed ||fileChooserIsOpen) return;
        Runnable r = () -> {
            JFileChooser jfc = new JFileChooser();
            fileChooserIsOpen = true;
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selected = jfc.getSelectedFile();
                System.out.println(selected);

                // Le nom du fichier select est dans selected
                try {
                    FileInputStream fin = new FileInputStream(selected.getAbsoluteFile());
                    ObjectInputStream ois = new ObjectInputStream(fin);
                    BatailleNavale bn = (BatailleNavale) ois.readObject();
                    game.setBatailleNavale(bn);
                    game.setGameState(GameState.RUNNING);
                    System.out.println("Importation réussie");
                } catch (ClassNotFoundException | IOException ioe) {
                    System.out.println("Impossible de lire dans " + selected);
                } finally {
                    hasChosed = true;
                    fileChooserIsOpen = false;
                }
            } else {
                hasChosed = true;
                fileChooserIsOpen = false;
                System.out.println("Set gs = menu");
            }
        };
        SwingUtilities.invokeLater(r);

    }
}
