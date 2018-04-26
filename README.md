# Bataille navale

## Sujet

### Résumé des fonctionnalités demandées

* Jeu mono-utilisateur : le joueur joue contre une IA.  
* Le joueur peut sauvgarder sa partie pour la reprendre plus tard.
* Le joueur peut choisir plusieurs époque au lancement de la partie. L'époque modifie l'aspect et certaines propriétés 
des bateaux.  
* Il faut au moins deux époques. Il doit être simple d'en rajouter d'avantage.  
* L'ordinateur a plusieurs tactiques de tir. Elles peuvent être choisies en début de partie et modifiées pendant la partie.

### Interface

Le joueur peut voir la grille avec ses bateaux ainsi que la grille de son adversaire avec ses tirs.

### Fonctionnalités bonus ("Envisageable")

* Une version où le joueur choisit le bateau qui effectue le tir courant; la force des tirs dépendra dans ce cas du 
type de bateau (et de son époque). Dans cette version le projectiles sont distribués de la même façon sur les bateaux 
du joueur et de l’ordinateur, et la partie est terminée quand il n’y a plus de projectiles disponibles.


## Comment jouer

### Compilation et lancement

* Se placer à la racine du projet puis exectuer dans un terminal "ant run" Le jar généré devrait se lancer automatiquement
* Necessite l'utilisation de java 8

### Déroulement d'une partie

* Nouvelle partie puis choisir l'époque
* Phase de placement des bateaux : Cliquer sur un bateau, un contour vert devrait apparaître, vous pouvez bouger
le bateau ou vous le souhaitez avec les flèches du clavier et la touche "R" pour appliquer une rotation au bateau
Le repeter autant de fois que vous voulez.
* Vous pouvez ensuite appuyer sur la touche "ESPACE" pour confirmer votre placement qui doit être valide
* Séléctionner ensuite un bateau puis cliquer sur la case où vous voulez tirer jusqu'à la fin de la partie


### ANNEXE

* Pour activer le mode debug (affichage des bateaux adverses), il faut décommenter la ligne 334 de la classe Painter

