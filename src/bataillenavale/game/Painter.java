package bataillenavale.game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import bataillenavale.boatFactory.abstractBoat.Bateau;
import bataillenavale.engine.GamePainter;
import bataillenavale.modele.BatailleNavale;
import bataillenavale.modele.Player;
import bataillenavale.modele.Point2D;
import bataillenavale.modele.Strategie;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class Painter implements GamePainter {

    private final static int NB_CASES = BatailleNavale.WIDTH;
    private final static int TAILLE_CASES = 45;
    private final static int OFFSET_SIDE = 20;
    private final static int OFFSET_MIDDLE = 60;
    private final static int BOTTOM_SIZE = 150;
    private final static int OFFSET_SIDE_TEXT = 60;
    private final static int OFFSET_MIDDLE_TEXT = 400;
    private final static int OFFSET_RIGHT_TEXT = 730;
    private final boolean DEBUG = true;

    private final static Color GRID_COLOR = new Color(0xffb8e3ee);
    private static final Color TEXT_COLOR = new Color(0xff161B21);
    private static final Color CLICK_COLOR = new Color(0xff2c3e50);
    private static final Color SELECTED_COLOR = new Color(0xff16a085);

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
                break;
            case FINISHED:
                drawFinished(crayon);
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

    private void drawFinished(Graphics2D crayon) {
        game.getFinishedGame().paint(crayon);
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
        drawClick(crayon);

        drawRunningText(crayon);
        drawRunningCurrentBoatStats(crayon);
        drawRunningStrategies(crayon);

        drawRunningFailedShoot(crayon);
        drawRunningBoats(crayon);
        drawRunningSuccessfulShoot(crayon);


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

        if (game.getBatailleNavale().playerIsReady()) {
            crayon.drawString("Epoque : " + game.getBatailleNavale().getEpoque(), OFFSET_SIDE_TEXT, HEIGHT - 90);
            crayon.drawString("Sauvegarder (S)", OFFSET_SIDE_TEXT, HEIGHT - 60);
            crayon.drawString("Quitter (Q)", OFFSET_SIDE_TEXT, HEIGHT - 30);
        } else {
            crayon.drawString("Positionnez vos bateaux", OFFSET_SIDE_TEXT, HEIGHT - 90);
            crayon.drawString("Sauvegarder (S)", OFFSET_SIDE_TEXT, HEIGHT - 60);
            crayon.drawString("Quitter (Q)", OFFSET_SIDE_TEXT, HEIGHT - 30);
        }

    }

    /**
     * Dessine les bateaux en fonction de l'époque, de leurs points de vies et de leur position.
     * @param crayon
     * @param bateau
     * @param x
     * @param y
     */
    private void drawRunningBoatGrid(Graphics2D crayon, Bateau bateau, int x, int y) {

	    BufferedImage bout;
	    BufferedImage milieu;

        if (game.getBatailleNavale().getEpoque() == "XIX") {
            if (bateau.getHP() > 0) {
                bout = ImageFactory.getInstance().getBoutBateauXIX();
                milieu = ImageFactory.getInstance().getMilieuBateauXIX();
            } else {
                bout = ImageFactory.getInstance().getBoutBreakBateauXIX();
                milieu = ImageFactory.getInstance().getMilieuBreakBateauXIX();
            }

        } else {
            if (bateau.getHP() > 0) {
                bout = ImageFactory.getInstance().getBoutBateauXVIII();
                milieu = ImageFactory.getInstance().getMilieuBateauXVIII();
            } else {
                bout = ImageFactory.getInstance().getBoutBreakBateauXVIII();
                milieu = ImageFactory.getInstance().getMilieuBreakBateauXVIII();
            }
        }

        AffineTransform at;

        switch (bateau.getOrientation()) {
            case EST:
                at = new AffineTransform();
                at.translate(x + TAILLE_CASES, y + TAILLE_CASES);
                at.rotate(Math.PI);
                crayon.drawImage(bout, at, null);

                for (int i = 1; i < bateau.getSize()-1; i++) {
                    x += TAILLE_CASES;
                    crayon.drawImage(milieu, x, y, null);
                }
                x += TAILLE_CASES;
                crayon.drawImage(bout, x, y, null);

                break;
            case OUEST:
                crayon.drawImage(bout, x, y, null);

                for (int i = 1; i < bateau.getSize() - 1; i++) {
                    x -= TAILLE_CASES;
                    at = new AffineTransform();
                    at.translate(x + TAILLE_CASES, y + TAILLE_CASES);
                    at.rotate(Math.PI);
                    crayon.drawImage(milieu, at, null);
                }
                x -= TAILLE_CASES;
                at = new AffineTransform();
                at.translate(x + TAILLE_CASES, y + TAILLE_CASES);
                at.rotate(Math.PI);
                crayon.drawImage(bout, at, null);
                break;
            case NORD:
                at = new AffineTransform();
                at.translate(x + TAILLE_CASES, y);
                at.rotate(Math.PI/2);
                crayon.drawImage(bout, at, null);

                for (int i = 1; i < bateau.getSize() - 1; i++) {
                    y -= TAILLE_CASES;
                    at = new AffineTransform();
                    at.translate(x, y + TAILLE_CASES);
                    at.rotate(-Math.PI / 2);
                    crayon.drawImage(milieu, at, null);
                }
                y -= TAILLE_CASES;
                at = new AffineTransform();
                at.translate(x, y + TAILLE_CASES);
                at.rotate(-Math.PI/2);
                crayon.drawImage(bout, at, null);
                break;
            case SUD:
                at = new AffineTransform();
                at.translate(x, y + TAILLE_CASES);
                at.rotate(-Math.PI/2);
                crayon.drawImage(bout, at, null);
                for (int i = 1; i < bateau.getSize()-1; i++) {
                    y += TAILLE_CASES;
                    at = new AffineTransform();
                    at.translate(x + TAILLE_CASES, y);
                    at.rotate(Math.PI/2);
                    crayon.drawImage(milieu, at, null);
                }
                y += TAILLE_CASES;
                at = new AffineTransform();
                at.translate(x + TAILLE_CASES, y);
                at.rotate(Math.PI/2);
                crayon.drawImage(bout, at, null);
                break;
        }
    }

    private void drawRunningBoatLeftGrid(Graphics2D crayon, Bateau bateau) {

	    int x = bateau.getPosition().getX() * TAILLE_CASES + OFFSET_SIDE;
        int y = bateau.getPosition().getY() * TAILLE_CASES + OFFSET_SIDE;

        drawRunningBoatGrid(crayon, bateau, x, y);
    }

    private void drawRunningBoatRightGrid(Graphics2D crayon, Bateau bateau) {

        int x = bateau.getPosition().getX() * TAILLE_CASES + OFFSET_SIDE + OFFSET_MIDDLE + TAILLE_CASES * NB_CASES;
        int y = bateau.getPosition().getY() * TAILLE_CASES + OFFSET_SIDE;

        drawRunningBoatGrid(crayon, bateau, x, y);
    }

    private void drawRunningBoatRightGridDebug(Graphics2D crayon, Bateau bateau) {
        crayon.setColor(Color.pink);
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

    private void drawRunningSelectedBoat(Graphics2D crayon, Bateau bateau) {
        crayon.setColor(SELECTED_COLOR);
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

    private void drawRunningBoats(Graphics2D crayon) {
        Player humain = game.getBatailleNavale().getHumain();
        Player ia = game.getBatailleNavale().getIa();

        for (int i = 0; i < humain.getBoatList().size(); i++) {
            Bateau bateau = humain.getBoatList().get(i);
            this.drawRunningBoatLeftGrid(crayon, bateau);
            if (i == humain.getCurrentBoatIndex()) {
                this.drawRunningSelectedBoat(crayon, bateau);
            }
        }

        // Si mode débug

        for (Bateau bateau : ia.getBoatList()) {
            if (bateau.getHP() <= 0) this.drawRunningBoatRightGrid(crayon, bateau);
            else this.drawRunningBoatRightGridDebug(crayon, bateau);
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

        return res;
    }

    public static Point2D clickPosToPosForRightGrid(Point2D clickPos) {
        int clickX = clickPos.getX();
        int clickY = clickPos.getY();

        clickX = clickX - OFFSET_SIDE - OFFSET_MIDDLE - (NB_CASES * TAILLE_CASES);
        clickY = clickY - OFFSET_SIDE;

        clickX /= TAILLE_CASES;
        clickY /= TAILLE_CASES;

        Point2D res = new Point2D(clickX, clickY);

        return res;
    }

    private void drawClick(Graphics2D crayon) {
	    crayon.setColor(CLICK_COLOR);
        if (isClickOnLeftGrid(Controller.getLastClickPos())) {
            Point2D pos = clickPosToPosForLeftGrid(Controller.getLastClickPos());
            crayon.drawRect(pos.getX() * TAILLE_CASES + OFFSET_SIDE, pos.getY() * TAILLE_CASES + OFFSET_SIDE, TAILLE_CASES, TAILLE_CASES);
        }

        if (isClickOnRightGrid(Controller.getLastClickPos())) {
            Point2D pos = clickPosToPosForRightGrid(Controller.getLastClickPos());
            crayon.drawRect(OFFSET_MIDDLE + TAILLE_CASES * NB_CASES + pos.getX() * TAILLE_CASES + OFFSET_SIDE, pos.getY() * TAILLE_CASES + OFFSET_SIDE, TAILLE_CASES, TAILLE_CASES);
        }
    }

    /**
     * Pendant la partie (état running), affiche les stats du boat select
     * @param crayon
     */
    private void drawRunningCurrentBoatStats(Graphics2D crayon) {
        crayon.setColor(TEXT_COLOR);
        crayon.setFont(new Font(" Serif ", Font.PLAIN, 20));
        crayon.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Player humain = game.getBatailleNavale().getHumain();
        Bateau currentBoat = humain.getCurrentBoat();

        if (!game.getBatailleNavale().playerIsReady()) {
            crayon.drawString("Bouger : Fleches", OFFSET_MIDDLE_TEXT, HEIGHT - 90);
            crayon.drawString("Rotation : R", OFFSET_MIDDLE_TEXT, HEIGHT - 60);
            crayon.drawString("Commencer : SPACE", OFFSET_MIDDLE_TEXT, HEIGHT - 30);
        } else if (currentBoat != null) {
            crayon.drawString("HP : " + currentBoat.getHP(), OFFSET_MIDDLE_TEXT, HEIGHT - 90);
            if (currentBoat.getMunitions() <= 0) crayon.setColor(new Color(0xffaa1923));
            else crayon.setColor(TEXT_COLOR);
            crayon.drawString("Munitions : " + currentBoat.getMunitions(), OFFSET_MIDDLE_TEXT, HEIGHT - 60);
            crayon.setColor(TEXT_COLOR);
            crayon.drawString("Dégats : " + currentBoat.getDegat(), OFFSET_MIDDLE_TEXT, HEIGHT - 30);
        } else {
            crayon.drawString("Choisissez un bateau", OFFSET_MIDDLE_TEXT, HEIGHT - 90);
        }

    }

    private void drawRunningFailedShoot(Graphics2D crayon) {
        // On déssine les tirs échoués pour la grille de droite
        Player humain = game.getBatailleNavale().getHumain();
        crayon.setColor(Color.red);
        for (Point2D tir : humain.getTirsEchoues()) {
            int x = tir.getX() * TAILLE_CASES + OFFSET_SIDE + OFFSET_MIDDLE + TAILLE_CASES * NB_CASES;
            int y = tir.getY() * TAILLE_CASES + OFFSET_SIDE;
            crayon.drawImage(ImageFactory.getInstance().getFail(), x, y, null);
        }

        // On déssine les tirs échoués pour la grille de gauche
        Player ia = game.getBatailleNavale().getIa();
        crayon.setColor(Color.orange);
        for (Point2D tir : ia.getTirsEchoues()) {
            int x = tir.getX() * TAILLE_CASES + OFFSET_SIDE;
            int y = tir.getY() * TAILLE_CASES + OFFSET_SIDE;
            crayon.drawImage(ImageFactory.getInstance().getFail(), x, y, null);
        }
    }

    private void drawRunningSuccessfulShoot(Graphics2D crayon) {
        // On déssine les tirs réussis sur les bateaux de l'humain
        Player humain = game.getBatailleNavale().getHumain();
        crayon.setColor(Color.black);
        for (Bateau boat : humain.getBoatList()) {
            for (Point2D tir : boat.getPointsTouches()) {
                int x = tir.getX() * TAILLE_CASES + OFFSET_SIDE;
                int y = tir.getY() * TAILLE_CASES + OFFSET_SIDE;
                crayon.drawImage(ImageFactory.getInstance().getExplosion(), x, y, null);
            }
        }

        // On déssine les tirs réussis sur les bateaux de l'ia
        Player ia = game.getBatailleNavale().getIa();
        crayon.setColor(Color.black);
        for (Bateau boat : ia.getBoatList()) {
            for (Point2D tir : boat.getPointsTouches()) {
                int x = tir.getX() * TAILLE_CASES + OFFSET_SIDE + OFFSET_MIDDLE + TAILLE_CASES * NB_CASES;
                int y = tir.getY() * TAILLE_CASES + OFFSET_SIDE;
                crayon.drawImage(ImageFactory.getInstance().getExplosion(), x, y, null);
            }
        }
    }

    private void drawRunningStrategies(Graphics2D crayon) {
        crayon.setColor(TEXT_COLOR);
        crayon.setFont(new Font(" Serif ", Font.PLAIN, 20));
        crayon.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Player ia = game.getBatailleNavale().getIa();
        Strategie s = ia.getStrategie();
        crayon.drawString("Stratégie : " + s.toString(), OFFSET_RIGHT_TEXT, HEIGHT - 90);
        crayon.drawString("Changer de stratégie (C)", OFFSET_RIGHT_TEXT, HEIGHT - 60);

    }

}
