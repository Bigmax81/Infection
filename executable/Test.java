package executable;

import java.util.Scanner;

/**
 * <b> Description : </b>Classe de tests permettant d'effectuer un certain nombre de parties avec des paramètres
 * différents afin d'observer les changements de résultats en fonction des modifications sur les IA et les joueurs.
 * 
 * <p>
 * Les paramètres qui vont varier en règle générale : 
 * </p> 
 * 
 * <ul>
	* <li> la profondeur de réflexion du joueur 1 </li>
	* <li> la profondeur de réflexion du joueur 2 </li>
	* <li> l'utilisation d'un élagage AlphaBêta </li>
	* <li> le nombre de coups d'avance du joueur 2 lors de son premier tour </li>
 * </ul>
 * 
 * <p>
 * Pour la réussite de nos tests, le terrain sera initialisé à 5 par 5.
 * </p>
 * 
 * @author Emmanuel Garreau 21700336
 * 
 * @version V1
 *
 * @see executable.Jeu
*/
public class Test {

	/**
	 * <b>Description : </b>Méthode exécutable de la classe <b>Test</b> qui va appeler la méthode main de la classe <b>Jeu</b>.
	 * Elle permet d'effectuer des expérimentations sur les algorithmes d'Intelligence Artificielle.
	 * @param args (type String[]) : la liste des paramètres.
	 *
	 * @see executable.Test#testsNoeuds(int)
	 *
	 * @see executable.Test#testsProfVSAvance(int, int, int)
	*/
	public static void main(String[] args) {
		
		// RAPPEL : les parties pour les phases de tests seront lancées avec les paramètres suivants :
		
		// args[0] = nombre de ligne (longueur)
		// args[1] = nombre de colonnes (largeur)
		// args[2] = nombre de coup d'avance donné au joueur 2
		// args[3] = profondeur du raisonnement pour le joueur 1 (blanc)
		// args[4] = profondeur du raisonnement pour le joueur 2 (noir)
		// args[5] = utilisation de l'algorithme MiniMax (false) ou de l'algorithme NégaMax (true)
		// args[6] = utilisation ou non d'un élagage (false ou true)
		// args[7] = affichage des terrains (false ou true)

		// Obtention du choix de l'utilisateur :

		Scanner sc = new Scanner(System.in);
		System.out.println("Voulez-vous étudier le nombre de noeuds parcourus\nen fonction des algorithmes (false) ou voulez-vous observer \n"+
			"l'intérêt du raisonnement en profondeur par rapport \nà des coups d'avance (true) ? \n");
		boolean temp = sc.nextBoolean();
		if (temp == false){
			System.out.println("Jusqu'à quelle profondeur voulez-vous aller ? \n");
			int prof = sc.nextInt();
			testsNoeuds(prof);
		}
		else{
			System.out.println("La profondeur de raisonnement du joueur 1 va augmenter tandis que le joueur 2 va obtenir des coups d'avance.\n");
			System.out.println("A quelle profondeur voulez-vous débuter pour les deux joueurs ? \n");
			int prof1 = sc.nextInt();
			System.out.println("Quelle profondeur voulez-vous atteindre pour le joueur 1 ? \n");
			int prof2 = sc.nextInt();
			System.out.println("Voulez-vous que le nombre de coups d'avance soit statique (true) ou progressif (false) ?\n");
			boolean t = sc.nextBoolean();
			int coup_av;
			if (t == true){
				System.out.println("Combien de coups d'avance voulez-vous donner au joueur 2 ? \n");
				coup_av = sc.nextInt();
			}
			else{
				coup_av = -1;
			}
			testsProfVSAvance(prof1, prof2, coup_av);
		}

	}

	/**
	 * <b>Description : </b>Méthode static de la classe <b>Test</b> permettant d'effectuer les tests sur le nombre de noeuds
	 * parcourus par les algorithmes d'Intelligence Artificielle.
	 * 
	 * @param profondeur (type int) : la profondeur choisie pour les deux IA.
	 *
	 * @see executable.Jeu#main(String[])
	*/
	public static void testsNoeuds(int profondeur){
		System.out.println("\n\n\n\n•••••••Phase de test sur le nombre de noeuds parcourus par l'algorithme MiniMax•••••••\n\n");
		for (int i = 1; i<=profondeur; i++){
			System.out.println("\n\n//////////////////// TEST "+i+" ////////////////////\n\n");
			String s = ""+i;
			String[] arg1 = {"5", "5", "0", s, s, "false", "false", "false"};
			Jeu.main(arg1); // Activation de la partie
		}

		System.out.println("\n\n\n\n•••••••Phase de test sur le nombre de noeuds parcourus par l'élagage AlphaBêta•••••••\n\n");
		for (int i = 1; i<=profondeur; i++){
			System.out.println("\n\n//////////////////// TEST "+i+" ////////////////////\n\n");
			String s = ""+i;
			String[] arg1 = {"5", "5", "0", s, s, "false", "true", "false"};
			Jeu.main(arg1); // Activation de la partie
		}
	}

	/**
	 * <b>Description : </b>Méthode static de la classe <b>Test</b> permettant d'effectuer les tests sur l'efficacité de la
	 * profondeur de réflexion par rapport à des coups d'avance.
	 * 
	 * @param profondeurDep (type int) : la profondeur de base choisie pour les deux IA.
	 *
	 * @param profondeurArr (type int) : la profondeur que doit atteindre le joueur 1 tandis que le joueur 2 gagne des coups d'avance.
	 *
	 * @param coup_av (type int) : indique si l'attribution des coups d'avance doit être statique (et le nombre) ou progressif.
	 *
	 * @see executable.Jeu#main(String[])
	*/
	public static void testsProfVSAvance(int profondeurDep, int profondeurArr, int coup_av){
		System.out.println("\n\n\n\n•••••••Phase de test sur l'opposition entre la profondeur de raisonnement et les coups d'avance•••••••\n\n");
		String dep = ""+profondeurDep;
		if (coup_av == -1){
			int c = 0;
			for (int i = profondeurDep; i<=profondeurArr; i++){
				String coup = ""+c;
				System.out.println("\n\n//////////////////// TEST "+(c+1)+" ////////////////////\n\n");
				String arr = ""+i;
				String[] arg1 = {"5", "5", coup, arr, dep, "false", "true", "false"};
				Jeu.main(arg1); // Activation de la partie
				c = c +1;
			}
		}
		else{
			int c = 1;
			String coup = ""+coup_av;
			for (int i = profondeurDep; i<=profondeurArr; i++){
				System.out.println("\n\n//////////////////// TEST "+(c)+" ////////////////////\n\n");
				String arr = ""+i;
				String[] arg1 = {"5", "5", coup, arr, dep, "false", "true", "false"};
				Jeu.main(arg1); // Activation de la partie
				c = c + 1;
			}
		}
		
	}

}
