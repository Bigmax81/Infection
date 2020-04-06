Bienvenue sur le projet "Jeu de l'Infection et Intelligence Artificielle" conçu dans le cadre de l'UE Sécurité et Aide à la Décision de la L2 Informatique, à l'Université de Caen Normandie.
Il s'agit d'un projet ayant pour objectif la mise en place du jeu de l'infection ainsi que l'implémentation d'algorithmes d'Intelligence Artificielle tels le MiniMax, l'élagage AlphaBêta ou encore leur version NégaMax.

Note du projet : 20/20


Réalisé par :

GARREAU		Emmanuel	21700336	L2_INFORMATIQUE		Groupe_1B


Classes Exécutables :


	- Jeu : lance une partie du jeu de l'infection


	- Test : lance un certain nombre de parties du jeu de l'infection avec des paramètres différents pour observer l'évolution du jeu en fonction des algorithmes d'optimisation. Les résultats sont reportés dans le rapport.




Pour toutes les commandes suivantes, les exécuter dans le dossier Infection.


Commande pour générer la Javadoc du projet :


	javadoc -html5 -d doc/ */*.java (Mac OS X)

	ou

	javadoc -d doc/ */*.java (Linux)


Commande de compilation :


	javac */*.java


Commandes d'exécution :


	Main sans argument (vous pourrez remplir les paramètres de façon interactive) :

		java executable.Jeu
	
	
	Main avec 6 arguments :

Argument 1 : nombre de lignes (nombre)
Argument 2 : nombre de colonnes (nombre)
Argument 3 : nombre de coups d'avance qu'aura le joueur 2 lors de son premier tour (nombre)
Argument 4 : profondeur de raisonnement du joueur 1 (nombre)
Argument 5 : profondeur de raisonnement du joueur 2 (nombre)
Argument 6 : utilisation ou non d'un élagage AlphaBêta (true ou false)


		java executable.Jeu 1 2 3 4 5 6



	Main avec 7 arguments :

Argument 1 : nombre de lignes (nombre)
Argument 2 : nombre de colonnes (nombre)
Argument 3 : nombre de coups d'avance qu'aura le joueur 2 lors de son premier tour (nombre)
Argument 4 : profondeur de raisonnement du joueur 1 (nombre)
Argument 5 : profondeur de raisonnement du joueur 2 (nombre)
Argument 6 : utilisation de l'algorithme MiniMax (false) ou de l'algorithme NégaMax (true)
Argument 7 : utilisation ou non d'un élagage AlphaBêta (true ou false)


		java executable.Jeu 1 2 3 4 5 6 7



	Main avec 8 arguments :

Argument 1 : nombre de lignes (nombre)
Argument 2 : nombre de colonnes (nombre)
Argument 3 : nombre de coups d'avance qu'aura le joueur 2 lors de son premier tour (nombre)
Argument 4 : profondeur de raisonnement du joueur 1 (nombre)
Argument 5 : profondeur de raisonnement du joueur 2 (nombre)
Argument 6 : utilisation de l'algorithme MiniMax (false) ou de l'algorithme NégaMax (true)
Argument 7 : utilisation ou non d'un élagage AlphaBêta (true ou false)
Argument 8 : affichages des terrains pour observer l'évolution du jeu (true ou false)


		java executable.Jeu 1 2 3 4 5 6 7 8



	Test sans argument (interactif) :


		java executable.Test
