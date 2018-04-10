package bataillenavale.game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import bataillenavale.engine.Cmd;
import bataillenavale.engine.GameController;
import bataillenavale.modele.Point2D;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 * 
 */
public class Controller implements GameController {


	/**
	 * commande en cours
	 */
	private Cmd commandeEnCours;
	private static Point2D lastClickPos;
	
	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public Controller() {
		this.commandeEnCours = Cmd.IDLE;
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
	public Cmd getCommand() {
		return this.commandeEnCours;
	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q:
                this.commandeEnCours = Cmd.QUIT;
                break;
            case KeyEvent.VK_S:
                this.commandeEnCours = Cmd.SAVE;
                break;
            case KeyEvent.VK_UP:
                this.commandeEnCours = Cmd.UP;
                break;
            case KeyEvent.VK_DOWN:
                this.commandeEnCours = Cmd.DOWN;
                break;
            case KeyEvent.VK_ENTER:
                this.commandeEnCours = Cmd.ENTER;
                break;
        }
	}

	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
        this.commandeEnCours = Cmd.IDLE;
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        commandeEnCours = Cmd.CLICK;
        lastClickPos = new Point2D(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        commandeEnCours = Cmd.IDLE;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static Point2D getLastClickPos() {
	    return lastClickPos;
    }
}
