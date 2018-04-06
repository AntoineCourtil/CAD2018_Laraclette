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

    private final static int NB_CASES = 10;
    private final static int TAILLE_CASES = 30;
    private final static int OFFSET_SIDE = 15;
    private final static int OFFSET_MIDDLE = 60;
    private final static int BOTTOM_SIZE = 150;

    private Game game;

    public static final int WIDTH = 2 * OFFSET_SIDE + 2 * TAILLE_CASES * NB_CASES + OFFSET_MIDDLE;
	public static final int HEIGHT = OFFSET_SIDE + TAILLE_CASES * NB_CASES + BOTTOM_SIZE;

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
            case RUNNING:
                drawRunning(crayon);
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
	    crayon.setColor(Color.black);


	    // On dessine la grille de gauche
	    for (int i = 0; i < NB_CASES; i++) {
	        for (int j = 0; j < NB_CASES; j++) {
                crayon.drawRect(OFFSET_SIDE + i * TAILLE_CASES,OFFSET_SIDE + j * TAILLE_CASES, TAILLE_CASES, TAILLE_CASES);
            }
        }

        // Puis la grille de droite
        for (int i = 0; i < NB_CASES; i++) {
            for (int j = 0; j < NB_CASES; j++) {
                crayon.drawRect(NB_CASES * TAILLE_CASES + OFFSET_SIDE + OFFSET_MIDDLE + i * TAILLE_CASES,OFFSET_SIDE + j * TAILLE_CASES, TAILLE_CASES, TAILLE_CASES);
            }
        }

        crayon.setFont(new Font(" Serif ", Font.PLAIN, 20));
        crayon.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        crayon.drawString("Epoch : " + game.getCurrentEpoch(), OFFSET_SIDE, HEIGHT - 100);
        crayon.drawString("Sauvegarder (S)", OFFSET_SIDE, HEIGHT - 70);
        crayon.drawString("Quitter (Q)", OFFSET_SIDE, HEIGHT - 40);

    }

}
