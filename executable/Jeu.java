package executable;

import java.util.Scanner;
import state.State;
import mouvement.Move;
import optimisation.IA;

/**
 * <b> Description : </b>Classe permettant d'instancier des parties de jeu en utilisant les algorithmes d'Intelligence
 * Artificielle.
 * 
 * <p>
 * La classe s'exécute en prenant plusieurs paramètres en compte :
 * </p> 
 * 
 * <ul>
	* <li> la longueur du terrain </li>
	* <li> la largeur du terrain </li>
	* <li> le nombre de coups d'avance qu'aura le joueur 2 lors de son premier tour </li>
	* <li> la profondeur de réflexion de l'algorithme du joueur 1 </li>
	* <li> la profondeur de réflexion de l'algorithme du joueur 2 </li>
	* <li> l'algorithme d'optimisation à utiliser, MiniMax ou NégaMax </li>
	* <li> l'utilisation ou non d'un élagage AlphaBêta </li>
	* <li> l'apparition des terrains afin d'observer l'évolution du jeu </li>
	* <li>
 * </ul>
 * 
 * @author Emmanuel Garreau 21700336
 * 
 * @version V1
*/
public class Jeu {
	
	/**
	 * <b>Description : </b>Méthode exécutable de la classe <b>Main</b> qui va appeler la méthode partie.
	 * @param args (type String[]) : la liste des paramètres à utiliser afin de débuter le jeu.
	 * 
	 * @see executable.Jeu#partie(int, int, int, int, int, boolean, boolean, boolean)
	*/
	public static void main(String[] args) {
		
		int n;
		int m;
		int coup_avance_j2;
		int profondeur_j1;
		int profondeur_j2;
		boolean mini_ou_nega;
		boolean elagage;
		boolean terrain;
		
		// Si aucun paramètre n'a été utilisé, les informations seront remplies de façon interactives.
		if (args.length == 0) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Donnez la longueur de terrain : \n");
			n = sc.nextInt();
			System.out.println("Donnez la largeur du terrain : \n");
			m = sc.nextInt();
			System.out.println("Donnez le nombre de coups d'avance qu'aura le joueur 2 lors de son premier tour : \n");
			coup_avance_j2 = sc.nextInt();
			System.out.println("Donnez la profondeur de raisonnement du joueur 1 : \n");
			profondeur_j1 = sc.nextInt();
			System.out.println("Donnez la profondeur de raisonnement du joueur 2 : \n");
			profondeur_j2 = sc.nextInt();
			System.out.println("Voulez vous utiliser Négamax ? true ou false (si false, l'algorithme MiniMax sera "
					+ "utilisé automatiquement) : \n");
			mini_ou_nega = sc.nextBoolean();
			System.out.println("Voulez vous utiliser l'élagage AlphaBêta ? true ou false : \n");
			elagage = sc.nextBoolean();
			System.out.println("Voulez-vous afficher les terrains pour observer l'évolution du jeu ? true ou false \n");
			terrain = sc.nextBoolean();
			Jeu.partie(n, m, coup_avance_j2, profondeur_j1, profondeur_j2, mini_ou_nega, elagage, terrain);
			sc.close();
		}

		// On gère par la suite différents cas possibles en fonction du nombre de paramètres entrés.

		else if (args.length == 6) {

			// args[0] = nombre de ligne (longueur)
			// args[1] = nombre de colonnes (largeur)
			// args[2] = nombre de coup d'avance donné au joueur 2
			// args[3] = profondeur du raisonnement pour le joueur 1 (blanc)
			// args[4] = profondeur du raisonnement pour le joueur 2 (noir)
			// args[5] = utilisation ou non d'un élagage (false ou true)
			// On utilise automatiquement l'algorithme MiniMax

			n = Integer.parseInt(args[0]);
			m = Integer.parseInt(args[1]);
			coup_avance_j2 = Integer.parseInt(args[2]);
			profondeur_j1 = Integer.parseInt(args[3]);
			profondeur_j2 = Integer.parseInt(args[4]);
			mini_ou_nega = false;
			elagage = Boolean.parseBoolean(args[5]);
			terrain = true;
			Jeu.partie(n, m, coup_avance_j2, profondeur_j1, profondeur_j2, mini_ou_nega, elagage, terrain);
		}

		else if (args.length == 7){

			// args[0] = nombre de ligne (longueur)
			// args[1] = nombre de colonnes (largeur)
			// args[2] = nombre de coup d'avance donné au joueur 2
			// args[3] = profondeur du raisonnement pour le joueur 1 (blanc)
			// args[4] = profondeur du raisonnement pour le joueur 2 (noir)
			// args[5] = utilisation de l'algorithme MiniMax (false) ou de l'algorithme NégaMax (true)
			// args[6] = utilisation ou non d'un élagage (false ou true)
			
			n = Integer.parseInt(args[0]);
			m = Integer.parseInt(args[1]);
			coup_avance_j2 = Integer.parseInt(args[2]);
			profondeur_j1 = Integer.parseInt(args[3]);
			profondeur_j2 = Integer.parseInt(args[4]);
			mini_ou_nega = Boolean.parseBoolean(args[5]);
			elagage = Boolean.parseBoolean(args[6]);
			terrain = true;
			Jeu.partie(n, m, coup_avance_j2, profondeur_j1, profondeur_j2, mini_ou_nega, elagage, terrain);
		}

		else if (args.length == 8) {

			// args[0] = nombre de ligne (longueur)
			// args[1] = nombre de colonnes (largeur)
			// args[2] = nombre de coup d'avance donné au joueur 2
			// args[3] = profondeur du raisonnement pour le joueur 1 (blanc)
			// args[4] = profondeur du raisonnement pour le joueur 2 (noir)
			// args[5] = utilisation de l'algorithme MiniMax (false) ou de l'algorithme NégaMax (true)
			// args[6] = utilisation ou non d'un élagage (false ou true)
			// args[7] = affichage des terrains (false ou true)

			n = Integer.parseInt(args[0]);
			m = Integer.parseInt(args[1]);
			coup_avance_j2 = Integer.parseInt(args[2]);
			profondeur_j1 = Integer.parseInt(args[3]);
			profondeur_j2 = Integer.parseInt(args[4]);
			mini_ou_nega = Boolean.parseBoolean(args[5]);
			elagage = Boolean.parseBoolean(args[6]);
			terrain = Boolean.parseBoolean(args[7]);
			Jeu.partie(n, m, coup_avance_j2, profondeur_j1, profondeur_j2, mini_ou_nega, elagage, terrain);
		}

		else {
			System.err.println("Mauvais paramètrage. Le programme doit être relancé. \n");
		}
		
		
	}
	
	/**
	 * <b>Description : </b>Méthode static de la classe <b>Main</b> permettant de gérer une partie complète du jeu
	 * de l'infection. Elle indique également qui a gagné, le nombre de noeuds parcourus et le nombre de tour.
	 * 
	 * @param n (type int) : la longueur du terrain.
	 * @param m (type int) : la largeur du terrain.
	 * @param coup_avance_j2 (type int) : le nombre de coups d'avance du joueur 2 lors de son premier tour.
	 * @param profondeur_j1 (type int) : la profondeur de réflexion du joueur 1.
	 * @param profondeur_j2 (type int) : la profondeur de réflexion du joueur 2.
	 * @param mini_ou_nega (type booleen) : le choix de l'algorithme.
	 * @param elagage (type booleen) : avec un élagage ou pas ?
	 * @param terrain (type booleen) : affichage des terrains ?
	 * 
	 * @see executable.Jeu#winner(State)
	 * 
	 * @see executable.Jeu#appelIA(int, IA, IA, State)
	*/
	static public void partie(int n,
		int m,
		int coup_avance_j2,
		int profondeur_j1,
		int profondeur_j2,
		boolean mini_ou_nega,
		boolean elagage,
		boolean terrain) {
		
		// Un compteur de tour.
		int tour = 0;

		// Création des deux IA.
		IA ia_J1 = new IA(1, profondeur_j1, mini_ou_nega, elagage);
		IA ia_J2 = new IA(2, profondeur_j2, mini_ou_nega, elagage);
		
		// Création d'un premier état.
		State e = new State(n, m, 1);

		// Initialisation d'une variable qui prendra, au fur et à mesure que la partie avance, les différents mouvements à jouer.
		Move mo = null;	

		// Si les terrains doivent être affichée.	
		if (terrain) {
			System.out.println("Terrain de jeu : \n");
			System.out.println(e.getT());

			// On débute la partie normalement si le joueur 2 n'a pas de coup d'avance.
			if (coup_avance_j2 == 0) {

				// Tant que la partie n'est pas finie, le jeu continue.
				while (e.isFinished()==false) {
					// Incrémentation du compteur de tour.
					tour = tour + 1;

					System.out.println("Tour du joueur "+e.getCurrentPlayer()+" :\n");

					// Utilisation de l'algorithme d'Intelligence Artificielle pour la récupération du meilleur mouvement.
					mo = Jeu.appelIA(e.getCurrentPlayer(), ia_J1, ia_J2, e);
					System.out.println("Son action : "+mo+" \n");

					// On effectue le mouvement. La partie évolue.
					e = e.play(mo);
					System.out.println(e.getT());
				}
			}

			// Si le joueur 2 a des coups d'avance.
			else {

				// Incrémentation du compteur de tour.
				tour = tour + 1;
				System.out.println("Tour du joueur "+e.getCurrentPlayer()+" :\n");

				// Le joueur 1 joue son premier tour normalement.

				// Utilisation de l'algorithme d'Intelligence Artificielle pour le joueur 1.
				mo = Jeu.appelIA(e.getCurrentPlayer(), ia_J1, ia_J2, e);
				System.out.println("Son action : "+mo+" \n");

				// Action du joueur 1. La partie évolue.
				e = e.play(mo);
				System.out.println(e.getT());

				// Le joueur effectue ses coups d'avance.
				int i = 0;
				while (i <= coup_avance_j2) {
					tour = tour + 1;
					System.out.println("Tour du joueur "+e.getCurrentPlayer()+" :\n");
					mo = Jeu.appelIA(e.getCurrentPlayer(), ia_J1, ia_J2, e);
					System.out.println("Son action : "+mo+" \n");
					e = e.play(mo);
					System.out.println(e.getT());
					e.setCurrentPlayer(2);
					i = i + 1;
				}

				// Reprise de la partie de façon normale.

				e.setCurrentPlayer(1);
				while (e.isFinished()==false) {
					tour = tour + 1;
					System.out.println("Tour du joueur "+e.getCurrentPlayer()+" :\n");
					mo = Jeu.appelIA(e.getCurrentPlayer(), ia_J1, ia_J2, e);
					System.out.println("Son action : "+mo+" \n");
					e = e.play(mo);
					System.out.println(e.getT());
				}
			}
		}

		// Si les terrains ne doivent pas être affichés.
		else {
			if (coup_avance_j2 == 0) {
				while (e.isFinished()==false) {
					tour = tour + 1;
					mo = Jeu.appelIA(e.getCurrentPlayer(), ia_J1, ia_J2, e);
					e = e.play(mo);
				}
			}
			else {
				tour = tour + 1;
				mo = Jeu.appelIA(e.getCurrentPlayer(), ia_J1, ia_J2, e);
				e = e.play(mo);
				int i = 0;
				while (i <= coup_avance_j2) {
					tour = tour + 1;
					mo = Jeu.appelIA(e.getCurrentPlayer(), ia_J1, ia_J2, e);
					e = e.play(mo);
					e.setCurrentPlayer(2);
					i = i + 1;
				}
				e.setCurrentPlayer(1);
				while (e.isFinished()==false) {
					tour = tour + 1;
					mo = Jeu.appelIA(e.getCurrentPlayer(), ia_J1, ia_J2, e);
					e = e.play(mo);
				}
			}
		}
		
		// On affiche les informations sur la partie.
		
		if (mini_ou_nega && elagage) {
			System.out.println("Vous avez choisi un algorithme Négamax avec élagage AlphaBeta. \n\n");
			System.out.println("La profondeur de raisonnement du joueur 1 est : "+profondeur_j1+". \n");
			System.out.println("La profondeur de raisonnement du joueur 2 est : "+profondeur_j2+". \n");
			System.out.println("Le nombre de coups d'avance du joueur 2 lors de son premier tour est de"
					+" : "+coup_avance_j2+". \n");
		}
		else if (mini_ou_nega && !elagage ) {
			System.out.println("Vous avez choisi un algorithme Négamax sans élagage AlphaBeta. \n\n");
			System.out.println("La profondeur de raisonnement du joueur 1 est : "+profondeur_j1+". \n");
			System.out.println("La profondeur de raisonnement du joueur 2 est : "+profondeur_j2+". \n");
			System.out.println("Le nombre de coups d'avance du joueur 2 lors de son premier tour est de"
					+" : "+coup_avance_j2+". \n");
		}
		else if (!mini_ou_nega && elagage) {
			System.out.println("Vous avez choisi un algorithme MiniMax avec élagage AlphaBeta. \n\n");
			System.out.println("La profondeur de raisonnement du joueur 1 est : "+profondeur_j1+". \n");
			System.out.println("La profondeur de raisonnement du joueur 2 est : "+profondeur_j2+". \n");
			System.out.println("Le nombre de coups d'avance du joueur 2 lors de son premier tour est de"
					+" : "+coup_avance_j2+". \n");
		}
		else if (!mini_ou_nega && !elagage) {
			System.out.println("Vous avez choisi un algorithme MiniMax sans élagage AlphaBeta. \n\n");
			System.out.println("La profondeur de raisonnement du joueur 1 est : "+profondeur_j1+". \n");
			System.out.println("La profondeur de raisonnement du joueur 2 est : "+profondeur_j2+". \n");
			System.out.println("Le nombre de coups d'avance du joueur 2 lors de son premier tour est de"
					+" : "+coup_avance_j2+". \n");
		}

		// On affiche les résultats de la partie.

		System.out.println("Résultats :");
		System.out.println("\nNombre de noeuds visités par le joueur 1 : "+(ia_J1.getCompteur_joueur()));
		System.out.println("\nNombre de noeuds visités par le joueur 2 : "+(ia_J2.getCompteur_joueur()));
		System.out.println("\nNombre de noeuds visités au total : "+(ia_J1.getCompteur_joueur() + ia_J2.getCompteur_joueur())+"");
		System.out.println("\nNombre de tours : "+tour+" \n");
		Jeu.winner(e);
		
	}
	
	/**
	 * <b>Description : </b>Méthode static de la classe <b>Main</b> qui permet de choisir quelle IA activer en fonction
	 * du joueur courant.
	 * 
	 * @param num (type int) : l'identifiant du joueur qui joue.
	 * @param j1 (type IA) : l'IA du joueur 1.
	 * @param j2 (type IA) : l'IA du joueur 2.
	 * @param e (type State) : l'état actuel.
	 * @return <i>Move : le mouvement à jouer.</i>
	*/
	static public Move appelIA(int num, IA j1, IA j2, State e) {
		if (num == 1) {
			return j1.decide(e);
		}
		else {
			return j2.decide(e);
		}
	}
	
	/**
	 * <b>Description : </b>Méthode static de la classe <b>Main</b> qui indique les scores des deux joueurs ainsi que
	 * le vainqueur.
	 * @param e (type State) : l'état final.
	*/
	static public void winner(State e) {
		System.out.println("\nScore du joueur 1 : "+e.getScore(1));
		System.out.println("\nScore du joueur 2 : "+e.getScore(2));
		if (e.getScore(1)>e.getScore(2)) {
			System.out.println("\nLe vainqueur est le joueur 1 !");
		}
		else if (e.getScore(1)<e.getScore(2)) {
			System.out.println("\nLe vainqueur est le joueur 2 !");
		}
		else {
			System.out.println("Egalité !\n");
		}
	}
	
}
