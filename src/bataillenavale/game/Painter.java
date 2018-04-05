package bataillenavale.game;

import java.awt.*;
import java.awt.image.BufferedImage;

import bataillenavale.engine.GamePainter;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class Painter implements GamePainter {

    private Game game;

    public static final int WIDTH = 600;
	public static final int HEIGHT = 400;

	public Painter(Game game) {
	    this.game = game;
	}

	/**
	 * Méthode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
        switch (game.getGameState()) {
            case MENU:
                drawMenu(crayon);
                break;
            case EPOCH_CHOOSE:
                drawEpochChoose(crayon);
                break;
            case RESUME_GAME:
                drawResumeGame(crayon);
                break;
        }

	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

	private void drawMenu(Graphics2D crayon) {
        game.getMainMenu().paint(crayon);
    }

    private void drawEpochChoose(Graphics2D crayon) {
        game.getEpochChoose().paint(crayon);
    }

    private void drawResumeGame(Graphics2D crayon) {
        game.getResumeGame().paint(crayon);
    }


    private void drawRunning(Graphics2D crayon) {

    }

}
