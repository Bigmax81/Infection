package state;

import java.util.ArrayList;
import mouvement.Move;
import terrain.Board;

/**
 * <b> Description : </b>Classe permettant d'instancier objet représentant un état actuel du jeu.
 * 
 * <p>
 * Un état est caractérisé par :
 * </p> 
 * 
 * <ul>
	* <li> l'identifiant du joueur courant </li>
	* <li> un terrain de jeu </li>
	* <li> deux tableaux de coordonnées spécifiques pour le calcul des déplacements/duplications </li>
 * </ul>
 * 
 * @author Emmanuel Garreau
 * 
 * @version V1
*/
public class State {

	/**
	 * <b>Description : </b>le numéro du joueur courant.
	*/
	private int currentPlayer;
	
	/**
	 * <b>Description : </b>le plateau de jeu actuel.
	*/
	private Board t;
	
	/**
	 * <b>Description : </b>un premier tableau de coordonnées. Ici, il s'agit des duplications.
	 */
	final static private int COO1[] = {1, -1};
	
	/**
	 * <b>Description : </b>un deuxième tableau de coordonnées. Ici, il s'agit des déplacements.
	*/
	final static private int COO2[] = {-2, 2};
	
	/**
	 * <b>Description : </b>Constructeur de la classe <b>State</b>. Il initialise un état de départ et sera utilisé
	 * uniquement au tout début de la partie.
	 * @param n (type int) : la longueur du tableau.
	 * @param m (type int) : la largeur du tableau.
	 * @param j (type int) : l'identifiant du joueur courant.
	*/
	public State(int n, int m, int j) {
		// TODO Auto-generated constructor stub
		this.t = new Board(m,n);
		this.currentPlayer = j;
	}
	
	/**
	 * <b>Description : </b>Deuxième constructeur de la classe <b>State</b>. Il permet d'instancier un nouvel état avec
	 * un terrain déjà créé et un joueur courant. Il sera utilisé dans la fonction play pour mettre à jour la partie.
	 * @param t (type Board) : le terrain actuel.
	 * @param j (type int) : le joueur courant.
	*/
	public State(Board t, int j) {
		this.t = t;
		this.currentPlayer = j;
	}
	
	// Début des Accesseurs et Mutateurs
	
	/**
	 * <b>Description : </b>Accesseur de la variable de classe <b>currentPlayer</b>.
	 * @return <i>int : l'identifiant du joueur courant.</i>
	*/
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * <b>Description : </b>Mutateur de la variable de classe <b>currentPlayer</b>.
	 * @param currentPlayer (type int) : l'identifiant du nouveau joueur courant.
	*/
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * <b>Description : </b>Accesseur de la variable de classe <b>t</b>.
	 * @return <i>Board : le terrain actuel.</i>
	*/
	public Board getT() {
		return this.t;
	}

	/**
	 * <b>Description : </b>Mutateur de la variable de classe <b>t</b>.
	 * @param t (type Board) : le nouveau terrain.
	*/
	public void setT(Board t) {
		this.t = t;
	}
	
	// Fin des Accesseurs et Mutateurs
	
	/**
	 * <b>Description : </b>Méthode de la classe <b>State</b> vérifiant si la partie est terminée.
	 * @return <i>boolean : indique si l'état actuel est un état final du jeu.</i>
	*/
	public boolean isFinished() {
		if (this.getMoves().size()==0) {
			
			// S'il n'y a plus de mouvement à effectuer, la partie est terminée.
			
			return true;
		}
		else {
			for (int i = 0; i<this.t.getN(); i++) {
				for (int j = 0; j<this.t.getM(); j++) {
					if (this.t.getTerrain()[i][j]==0) {
						
						// Si un élément du terrain n'appartient à aucun des deux joueurs et que des mouvements sont
						// toujours possible, la partie n'est pas terminée.
						
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * <b>Description : </b>Méthode de la classe <b>State</b> permettant d'obtenir un nouvel état après qu'un joueur ait
	 * effectué un mouvement.
	 * @param m (type mouvement) : le mouvement à effectuer.
	 * @return <i>State : le nouvel état après application du mouvement.</i>
	*/
	public State play(Move m) {
		Board te = new Board(this.t.getTerrain());
		int a = m.getStart()[0];
		int b = m.getStart()[1];
		int c = m.getEnd()[0];
		int d = m.getEnd()[1];

		// On effectue un déplacement.
		if (m.isDep_ou_dup()==false) {
			te.getTerrain()[a][b] = 0;
			te.getTerrain()[c][d] = this.currentPlayer;
		}

		// On effectue une duplication.
		else {

			// Ajout du nouveau pion du joueur.
			te.getTerrain()[c][d] = this.currentPlayer;
			
			// On vérifie si les cases adjacentes comportent des pions adverses.
			for (int i = 0; i<COO1.length;i++) {
				if ((c+COO1[i]>=0 && c+COO1[i]<this.t.getN()) && this.t.getTerrain()[c+COO1[i]][d]==this.otherPlayer()) {

					// Les pions adverses changent de camp.
					te.getTerrain()[c+COO1[i]][d] = this.currentPlayer;
				}

				if ((d+COO1[i]>=0 && d+COO1[i]<this.t.getM()) && this.t.getTerrain()[c][d+COO1[i]]==this.otherPlayer()) {

					// Les pions adverses changent de camp.
					te.getTerrain()[c][d+COO1[i]] = this.currentPlayer;
				}
			}
		}
		State e = new State(te, this.otherPlayer());
		return e;
	}
	
	/**
	 * <b>Description : </b>Méthode de la classe <b>State</b> permettant de renvoyer l'identifiant du joueur en attente.
	 * @return <i>int : le joueur en attente.</i>
	*/
	public int otherPlayer() {
		if (this.currentPlayer == 1){
			return 2;
		}
		else {
			return 1;
		}
	}
	
	/**
	 * <b>Description : </b>Méthode de la classe <b>State</b> permettant de renvoyer tous les mouvements possibles pour le joueur courant.
	 *
	 * @return <i>ArrayList : la liste des mouvements que le joueur actuel peut effectuer.</i>
	*/
	public ArrayList<Move> getMoves() {
		ArrayList<Move> m = new ArrayList<Move>();

		// On parcourt toutes les cases du terrain jusqu'à en trouver une possédant un pion du joueur.
		for (int i = 0; i<this.t.getN(); i++) {
			for (int j = 0; j<this.t.getM(); j++) {
				if (this.t.getTerrain()[i][j] == this.currentPlayer) {
					// Si une case appartient au joueur courant, on liste ses mouvements possibles :
					
					// Vérification pour les duplications
					
					for (int a = 0; a<COO1.length;a++) {
						if ((i+COO1[a]>=0 && i+COO1[a]<this.t.getN()) && this.t.getTerrain()[i+COO1[a]][j]==0) {
							int[] co1 = {i, j};
							int[] co2 = {i+COO1[a],j};
							m.add(new Move(co1, co2));
						}
						if ((j+COO1[a]>=0 && j+COO1[a]<this.t.getM()) && this.t.getTerrain()[i][j+COO1[a]]==0) {
							int[] co1 = {i, j};
							int[] co2 = {i,j+COO1[a]};
							m.add(new Move(co1, co2));
						}
					}
					
					// Vérification pour les déplacements
					
					for (int a = 0; a<COO2.length;a++) {
						if ((i+COO2[a]>=0 && i+COO2[a]<this.t.getN()) && this.t.getTerrain()[i+COO2[a]][j]==0) {
							int[] co1 = {i, j};
							int[] co2 = {i+COO2[a],j};
							m.add(new Move(co1, co2));
						}
						if ((j+COO2[a]>=0 && j+COO2[a]<this.t.getM()) && this.t.getTerrain()[i][j+COO2[a]]==0) {
							int[] co1 = {i, j};
							int[] co2 = {i,j+COO2[a]};
							m.add(new Move(co1, co2));
						}
					}
				}
			}
		}
		return m;
	}
	
	/**
	 * <b>Description : </b>Méthode de la classe <b>State</b> permettant d'obtenir le score actuel d'un joueur.
	 * @param currentPlayer (type int) : l'identifiant du joueur dont on veut vérifier le score.
	 * @return <i>float : score du joueur passé en paramètre</i>
	*/
	public float getScore(int currentPlayer) {
		float nb1=0;
		float nbtot=0;

		// On calcule le nombre de pions du joueur et le nombre de pions au total.
		for (int i = 0; i<this.t.getN(); i++) {
			for (int j = 0; j<this.t.getM(); j++) {

				if (this.t.getTerrain()[i][j]!=0) {
					nbtot = nbtot + 1;
				}

				if (this.t.getTerrain()[i][j]==currentPlayer) {
					nb1 = nb1 + 1;
				}
			}
		}
		return nb1/nbtot;
	}

}
