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

    private static final int WIDTH = 600;
	private static final int HEIGHT = 400;

	public Painter(Game game) {
	    this.game = game;
	}

	/**
	 * Méthode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
        drawMenu(crayon);
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
        crayon.setFont(new Font(" Serif ", Font.PLAIN, 25)); // restore font


        for (int i = 0; i < game.getMenuEntries().length; i++) {
            if (i == game.getSelectedMenuIndex()) {
                crayon.setColor(Color.blue);
                crayon.drawString(game.getMenuEntries()[i], WIDTH / 2 - 50, HEIGHT / 2 + i * 30);
            } else {
                crayon.setColor(Color.black);
                crayon.drawString(game.getMenuEntries()[i], WIDTH / 2 - 50, HEIGHT / 2 + i * 30);
            }

        }
    }

    private void drawEpochChoose() {

    }

    private void drawRunning() {

    }

    private void drawResumeGame() {

    }

}
