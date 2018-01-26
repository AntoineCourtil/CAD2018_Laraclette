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
* Un compteur de tirs (dans l’eau et dans un bateau adverse).

## Dossier de conception

### Comportement général

![Diagramme de séquence : comportement général](../blob/master/conception/seq_comportement_general.png?raw=true)

 
