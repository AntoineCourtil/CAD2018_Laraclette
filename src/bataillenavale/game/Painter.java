package bataillenavale.game;

import java.awt.*;
import java.awt.image.BufferedImage;

import bataillenavale.boatFactory.abstractBoat.Bateau;
import bataillenavale.engine.GamePainter;
import bataillenavale.modele.BatailleNavale;
import bataillenavale.modele.Player;
import bataillenavale.modele.Point2D;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class Painter implements GamePainter {

    private final static int NB_CASES = BatailleNavale.WIDTH;
    private final static int TAILLE_CASES = 30;
    private final static int OFFSET_SIDE = 15;
    private final static int OFFSET_MIDDLE = 60;
    private final static int BOTTOM_SIZE = 150;
    private final static int OFFSET_SIDE_TEXT = 40;
    private final boolean DEBUG = true;

    private final static Color GRID_COLOR = new Color(0xffb8e3ee);
    private static final Color TEXT_COLOR = new Color(0xff161B21);

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

	    crayon.drawImage(ImageFactory.getInstance().getSea(), 0, 0, null);

	    drawRunningGrid(crayon);
	    drawRunningBoats(crayon);
        drawRunningText(crayon);

        // Debug
        if (isClickOnLeftGrid(Controller.getLastClickPos())) {
            crayon.setColor(Color.green);
            Point2D pos = clickPosToPosForLeftGrid(Controller.getLastClickPos());
            crayon.drawRect(pos.getX() * TAILLE_CASES + OFFSET_SIDE, pos.getY() * TAILLE_CASES + OFFSET_SIDE, TAILLE_CASES, TAILLE_CASES);
        }
    }

    private void drawRunningGrid(Graphics2D crayon) {
        crayon.setColor(GRID_COLOR);


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
    }

    private void drawRunningText(Graphics2D crayon) {
        crayon.setColor(TEXT_COLOR);
        crayon.setFont(new Font(" Serif ", Font.PLAIN, 20));
        crayon.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        crayon.drawString("Epoque : " + game.getCurrentEpoch(), OFFSET_SIDE_TEXT, HEIGHT - 100);
        crayon.drawString("Sauvegarder (S)", OFFSET_SIDE_TEXT, HEIGHT - 70);
        crayon.drawString("Quitter (Q)", OFFSET_SIDE_TEXT, HEIGHT - 40);
    }

    private void drawRunningBoatLeftGrid(Graphics2D crayon, Bateau bateau, Color color) {
	    crayon.setColor(color);
        switch (bateau.getOrientation()) {
            case EST:
                crayon.drawRect(OFFSET_SIDE + bateau.getPosition().getX() * TAILLE_CASES,
                        OFFSET_SIDE + bateau.getPosition().getY() * TAILLE_CASES, TAILLE_CASES * bateau.getSize(), TAILLE_CASES);
                break;
            case OUEST:
                crayon.drawRect(OFFSET_SIDE + bateau.getPosition().getX() * TAILLE_CASES - TAILLE_CASES * (bateau.getSize() - 1),
                        OFFSET_SIDE + bateau.getPosition().getY() * TAILLE_CASES, TAILLE_CASES * bateau.getSize(), TAILLE_CASES);
                break;
            case NORD:
                crayon.drawRect(OFFSET_SIDE + bateau.getPosition().getX() * TAILLE_CASES,
                        OFFSET_SIDE + bateau.getPosition().getY() * TAILLE_CASES - TAILLE_CASES  * (bateau.getSize() - 1), TAILLE_CASES, TAILLE_CASES * bateau.getSize());
                break;
            case SUD:
                crayon.drawRect(OFFSET_SIDE + bateau.getPosition().getX() * TAILLE_CASES,
                        OFFSET_SIDE + bateau.getPosition().getY() * TAILLE_CASES, TAILLE_CASES, TAILLE_CASES * bateau.getSize());
                break;
        }
    }

    private void drawRunningBoatRightGrid(Graphics2D crayon, Bateau bateau, Color color) {
        crayon.setColor(color);
        switch (bateau.getOrientation()) {
            case EST:
                crayon.drawRect(OFFSET_MIDDLE + NB_CASES * TAILLE_CASES + OFFSET_SIDE + bateau.getPosition().getX() * TAILLE_CASES,
                        OFFSET_SIDE + bateau.getPosition().getY() * TAILLE_CASES, TAILLE_CASES * bateau.getSize(), TAILLE_CASES);
                break;
            case OUEST:
                crayon.drawRect(OFFSET_MIDDLE + NB_CASES * TAILLE_CASES +OFFSET_SIDE + bateau.getPosition().getX() * TAILLE_CASES - TAILLE_CASES * (bateau.getSize() - 1),
                        OFFSET_SIDE + bateau.getPosition().getY() * TAILLE_CASES, TAILLE_CASES * bateau.getSize(), TAILLE_CASES);
                break;
            case NORD:
                crayon.drawRect(OFFSET_MIDDLE + NB_CASES * TAILLE_CASES +OFFSET_SIDE + bateau.getPosition().getX() * TAILLE_CASES,
                        OFFSET_SIDE + bateau.getPosition().getY() * TAILLE_CASES - TAILLE_CASES  * (bateau.getSize() - 1), TAILLE_CASES, TAILLE_CASES * bateau.getSize());
                break;
            case SUD:
                crayon.drawRect(OFFSET_MIDDLE + NB_CASES * TAILLE_CASES +OFFSET_SIDE + bateau.getPosition().getX() * TAILLE_CASES,
                        OFFSET_SIDE + bateau.getPosition().getY() * TAILLE_CASES, TAILLE_CASES, TAILLE_CASES * bateau.getSize());
                break;
        }
    }

    private void drawRunningBoats(Graphics2D crayon) {
        Player humain = game.getBatailleNavale().getHumain();
        Player ia = game.getBatailleNavale().getIa();

        for (int i = 0; i < humain.getBoatList().size(); i++) {
            Bateau bateau = humain.getBoatList().get(i);
            if (i == humain.getCurrentBoatIndex()) this.drawRunningBoatLeftGrid(crayon, bateau, Color.yellow);
            else this.drawRunningBoatLeftGrid(crayon, bateau, Color.red);
        }

        // Si mode débug
        if (DEBUG) {
            for (Bateau bateau : ia.getBoatList()) {
                this.drawRunningBoatRightGrid(crayon, bateau, Color.pink);
            }
        }
    }

    public static boolean isClickOnLeftGrid(Point2D clickPos) {
	    if (clickPos == null) return false;
	    if (clickPos.getX() > OFFSET_SIDE && clickPos.getX() < NB_CASES * TAILLE_CASES + OFFSET_SIDE) {
	        if (clickPos.getY() > OFFSET_SIDE && clickPos.getY() < NB_CASES * TAILLE_CASES + OFFSET_SIDE) {
	            return true;
            }
        }
        return false;
    }

    public static boolean isClickOnRightGrid(Point2D clickPos) {
        if (clickPos == null) return false;
        if (clickPos.getX() > OFFSET_SIDE + OFFSET_MIDDLE + NB_CASES * TAILLE_CASES
                && clickPos.getX() < 2 * NB_CASES * TAILLE_CASES + OFFSET_SIDE + OFFSET_MIDDLE) {
            if (clickPos.getY() > OFFSET_SIDE && clickPos.getY() < NB_CASES * TAILLE_CASES + OFFSET_SIDE) {
                return true;
            }
        }
        return false;
    }

    public static Point2D clickPosToPosForLeftGrid(Point2D clickPos) {
        int clickX = clickPos.getX();
        int clickY = clickPos.getY();

        //System.out.println("Click : " + clickPos);

        clickX -= OFFSET_SIDE;
        clickY -= OFFSET_SIDE;

        clickX /= TAILLE_CASES;
        clickY /= TAILLE_CASES;

        Point2D res = new Point2D(clickX, clickY);

        //System.out.println("Res : " + res);

        return res;
    }

    public static Point2D clickPosToPosForRightGrid(Point2D clickPos) {
        return null;
    }

}
