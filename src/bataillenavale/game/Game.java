package bataillenavale.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import bataillenavale.engine.Cmd;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class Game implements bataillenavale.engine.Game {

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public Game() {

	}

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		System.out.println("Execute "+commande);
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
		return false;
	}

}
