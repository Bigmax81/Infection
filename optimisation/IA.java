package optimisation;

import state.State;
import mouvement.Move;

/**
 * <b> Description : </b>Classe permettant d'instancier une Intelligence Artificielle qui pourra utiliser différents
 * types d'algorithmes d'optimisation.
 * 
 * <p>
 * Une IA est caractérisée par :
 * </p> 
 * 
 * <ul>
	* <li> l'identifiant du joueur </li>
	* <li> la profondeur de réflexion de l'algorithme qu'elle utilisera </li>
	* <li> un compteur par instance </li>
	* <li> l'algorithme à utiliser, MiniMax ou Négamax </li>
	* <li> l'utilisation d'un élagage AlphaBêta ou non </li>
 * </ul>
 * 
 * <p>
 * Les différents types d'algorithmes d'optimisation mis en place sont :
 * </p>
 * <ul>
 	* <li> L'algorithme MiniMax </li>
 	* <li> L'élagage AlphaBêta de la version MiniMax </li>
 	* <li> L'algorithme NégaMax </li>
 	* <li> L'élagage AlphaBêta de la version Négamax </li>
 * </ul>
 * 
 * @author Emmanuel Garreau
 * 
 * @version V1
 * 
 * @see optimisation.Evaluation
*/
public class IA {
	
	/**
	 * <b>Description : </b>Identifiant du joueur.
	*/
	private int num_joueur;
	
	/**
	 * <b>Description : </b>Profondeur de réflexion de l'algorithme. 
	*/
	private int profondeur;
	
	/**
	 * <b>Description : </b>Compteur permettant de comptabiliser le nombre de noeuds visités par chaque joueur.
	*/
	private int compteur_joueur = 0;
	
	/**
	 * <b>Description : </b>Variable indiquant quel algorithme utiliser, MiniMax (false) ou Négamax (true)
	*/
	private boolean min_ou_neg;
	
	/**
	 * <b>Description : </b>Variable indiquant si un élagage AlphaBêta doit être utilisé ou non. 
	*/
	private boolean elagage;

	/**
     * <b>Description : </b>Constructeur de la classe <b>IA</b> avec paramètres. Ce constructeur initialise
     * un objet IA qui permettra ensuite d'utiliser l'algorithme demandé.
     * @param num_joueur (type int) : identifiant du joueur.
     * @param profondeur (type int) : profondeur de réflexion de l'algorithme.
     * @param min_ou_neg (type booleen) : MiniMax ou Negamax ?
     * @param elagage (type booleen) : avec AlphaBêta ?
    */
	public IA(int num_joueur, int profondeur, boolean min_ou_neg, boolean elagage) {
		this.num_joueur = num_joueur;
		this.profondeur = profondeur;
		this.min_ou_neg = min_ou_neg;
		this.elagage = elagage;
	}

	// Début des Accesseurs et Mutateurs
	
	/**
	 * <b>Description : </b>Accesseur de la variable de classe <b>num_joueur</b>.
	 * @return <i>int : l'identifiant du joueur.</i>
	*/
	public int getNum_joueur() {
		return this.num_joueur;
	}

	/**
	 * <b>Description : </b>Mutateur de la variable de classe <b>num_joueur</b>.
	 * @param num_joueur (type int) : le nouvel identifiant du joueur.
	*/
	public void setNum_joueur(int num_joueur) {
		this.num_joueur = num_joueur;
	}

	/**
	 * <b>Description : </b>Accesseur de la variable de classe <b>profondeur</b>.
	 * @return <i>int : profondeur de réflexion de l'algorithme.</i>
	*/
	public int getProfondeur() {
		return this.profondeur;
	}

	/**
	 * <b>Description : </b>Mutateur de la variable de classe <b>profondeur</b>. 
	 * @param profondeur (type int) : la nouvelle profondeur de réflexion.
	*/
	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}

	/**
	 * <b>Description : </b>Accesseur de la variable de classe <b>compteur_joueur</b>.
	 * @return <i> int : le compteur spécifique à une unique instance.</i>
	*/
	public int getCompteur_joueur() {
		return this.compteur_joueur;
	}
	/**
	 * <b>Description : </b>Mutateur de la variable de classe <b>compteur_joueur</b>.
	 * @param compteur_joueur (type int) : le nouveau compteur.
	*/
	public void setCompteur_joueur(int compteur_joueur) {
		this.compteur_joueur = compteur_joueur;
	}
	
	/**
	 * <b>Description : </b>Accesseur de la variable de classe <b>min_ou_neg</b>. 
	 * @return <i> booleen : indique l'algorithme à utiliser.</i>
	*/
	public boolean isMin_ou_neg() {
		return this.min_ou_neg;
	}

	/**
	 * <b>Description : </b>Mutateur de la variable de classe <b>min_ou_neg</b>. 
	 * @param min_ou_neg (type booleen) : changement d'algorithme.
	*/
	public void setMin_ou_neg(boolean min_ou_neg) {
		this.min_ou_neg = min_ou_neg;
	}

	/**
	 * <b>Description : </b>Accesseur de la variable de classe <b>elagage</b>.
	 * @return <i>booleen : indique s'il faut utiliser un élagage.</i>
	 */
	public boolean isElagage() {
		return this.elagage;
	}

	/**
	 * <b>Description : </b>Mutateur de la variable de classe <b>elagage</b>. 
	 * @param elagage (type booleen) : changement de décision.
	*/
	public void getElagage(boolean elagage) {
		this.elagage = elagage;
	}
	
	// Fin des Accesseurs et Mutateurs
	
	/**
	 * <b>Description : </b>Fonction qui va décider en fonction des variables de classe quel algorithme utiliser
	 * pour l'obtention du meilleur coup à jouer.
	 * @param e (type State) : l'état actuel.
	 * @return Move : le mouvement à jouer.
	 * 
	 * @see optimisation.IA#minimax(State, int)
	 * @see optimisation.IA#alphabetaMini(State, float, float, int)
	 * @see optimisation.IA#negamax(State, int)
	 * @see optimisation.IA#alphabetaNega(State, float, float, int)
	*/
	public Move decide(State e){
		int d = this.profondeur;
		Evaluation res = null;
		if (this.min_ou_neg == false){
			if (this.elagage == false){
				res = minimax(e, d);
				
			}
			else{
				float a = -999999;
				float b = 999999;
				res = alphabetaMini(e, a, b, d);
			}
		}
		else{
			if (this.elagage == false){
				res = negamax(e, d);
			}
			else{
				float a = -999999;
				float b = 999999;
				res = alphabetaNega(e, a, b, d);
			}
		}
		return res.getM();	
	}
	
	//••••••••••••••••••••••••••••••••••••••••••••••••••••PARTIE MiniMax••••••••••••••••••••••••••••••••••••••••••••••••••••

	/**
	 * <b>Description : </b>Fonction minimax implémentant l'algorithme d'optimisation MiniMax.
	 * <p>
	 * Il va parcourir l'arbre des états jusqu'à une certaine profondeur en évaluant les noeuds afin de 
	 * proposer le mouvement le plus efficace pour le joueur courant.
	 * Il s'agit d'une fonction récursive.
	 * </p>
	 * @param e (type State) : l'état courant.
	 * @param d (type int) : la profondeur de réflexion.
	 * @return <i>Evaluation : le mouvement à jouer et son évaluation.</i>
	*/
	public Evaluation minimax(State e, int d) {

		// Incrémentation du compteur.
		this.compteur_joueur = this.compteur_joueur + 1;

		if (d == 0 || e.isFinished()) {
			return new Evaluation(null, e.getScore(this.num_joueur));
		}
		float v2;
		Move m;
		if (e.getCurrentPlayer()==this.num_joueur) {
			v2 = -999999;
			m = null;
			for (int i = 0; i < e.getMoves().size(); i++) {
				State s = e.play(e.getMoves().get(i));
				Evaluation temp = minimax(s, d-1);
				if (temp.getF()>v2) {
					v2 = temp.getF();
					m = e.getMoves().get(i);
				}
			}
		}
		else {
			v2 = 999999;
			m = null;
			for (int j = 0; j < e.getMoves().size(); j++) {
				State s = e.play(e.getMoves().get(j));
				Evaluation temp = minimax(s, d-1);
				if (temp.getF()<v2) {
					v2 = temp.getF();
					m = e.getMoves().get(j);
				}
			}
		}

		return new Evaluation(m, v2);
	}
	
	/**
	 * <b>Description : </b>Version AlphaBêta de l'algorithme MiniMax.
	 * <p>
	 * Il implémente l'algorithme MiniMax couplé à une technique d'élagage qui va parcourir l'arbre des états jusqu'à
	 * une certaine profondeur en évaluant les noeuds afin de proposer le mouvement le plus efficace pour le joueur
	 * courant. L'algorithme ignorera les branches inutiles en fonction de deux variables d'encadrement, alpha et
	 * bêta, ce qui lui permettra de gagner en vitesse de calcul et en nombre de noeuds parcourus.
	 * Il s'agit d'une fonction récursive.
	 * </p>
	 * @param e (type State) : l'état courant.
	 * @param a (type float) : variable d'encadrement alpha.
	 * @param b (type float) : variable d'encadrement bêta.
	 * @param d (type int) : la profondeur de réflexion.
	 * @return <i>Evaluation : le mouvement à jouer et son évaluation.</i>
	*/
	public Evaluation alphabetaMini(State e, float a, float b, int d) {

		// Incrémentation du compteur.
		this.compteur_joueur = this.compteur_joueur + 1;

		if (d==0 || e.isFinished()) {
			return new Evaluation(null, e.getScore(this.num_joueur));
		}
		if (e.getCurrentPlayer()==this.num_joueur) {
			Move m = null;
			for (int i = 0; i<e.getMoves().size(); i++) {
				State etat = e.play(e.getMoves().get(i));
				Evaluation v = alphabetaMini(etat, a, b, d-1);
				if (v.getF() > a) {
					a = v.getF();
					m = e.getMoves().get(i);
				}
				if (a >= b) {
					return new Evaluation(m, a);
				}
			}
			return new Evaluation(m, a);
		}
		else {
			Move m = null;
			for (int j = 0; j<e.getMoves().size(); j++) {
				State etat = e.play(e.getMoves().get(j));
				Evaluation v = alphabetaMini(etat, a, b, d-1);
				if (v.getF() < b) {
					b = v.getF();
					m = e.getMoves().get(j);
				}
				if (b <= a) {
					return new Evaluation(m, b);
				}
			}
			return new Evaluation(m, b);
		}
	}
	
	
	//••••••••••••••••••••••••••••••••••••••••••••••••••••PARTIE NégaMax••••••••••••••••••••••••••••••••••••••••••••••••••••
	
	/**
	 * <b>Description : </b>Fonction d'évaluation spécifique à l'algorithme NégaMax et dont le résultat sera modifié
	 * en fonction du joueur courant.
	 * @param e (type State) : l'état courant.
	 * @return <i>Evaluation : un mouvement vide et l'évaluation d'une feuille de l'arbre des états.</i>
	*/
	public Evaluation g(State e) {
		if (e.getCurrentPlayer() == this.num_joueur) {
			return new Evaluation(null, e.getScore(this.num_joueur));
		}
		else {
			return new Evaluation(null, -e.getScore(this.num_joueur));
		}
	}
	
	/**
	 * <b>Description : </b>Fonction negamax implémentant l'algorithme d'optimisation NégaMax dérivé du MiniMax.
	 * <p>
	 * Il va parcourir l'arbre des états jusqu'à une certaine profondeur en évaluant les noeuds afin de 
	 * proposer le mouvement le plus efficace pour le joueur courant. Cet algorithme n'a pas de différence
	 * avec le MiniMax en terme de résultat. La principale nuance réside dans son implémentation.
	 * Il s'agit d'une fonction récursive.
	 * </p>
	 * @param e (type State) : l'état courant.
	 * @param d (type int) : la profondeur de réflexion.
	 * @return <i>Evaluation : le mouvement à jouer et son évaluation.</i>
	*/
	public Evaluation negamax(State e, int d) {

		// Incrémentation du compteur.
		this.compteur_joueur = this.compteur_joueur + 1;

		if (d==0 || e.isFinished()) {
			return this.g(e);
		}
		else {
			float v = -999999;
			Move m = null;
			for (int i = 0; i<e.getMoves().size(); i++) {
				Evaluation temp = negamax(e.play(e.getMoves().get(i)), d-1);
				if (v < -temp.getF()) {
					v = -temp.getF();
					m = e.getMoves().get(i);
				}
			}
			Evaluation res = new Evaluation(m, v);
			return res;
		}
	}
	
	/**
	 * <b>Description : </b>Version AlphaBêta de l'algorithme NégaMax.
	 * <p>
	 * Il implémente l'algorithme NégaMax couplé à une technique d'élagage qui va parcourir l'arbre des états jusqu'à
	 * une certaine profondeur en évaluant les noeuds afin de proposer le mouvement le plus efficace pour le joueur
	 * courant. L'algorithme ignorera les branches inutiles en fonction de deux variables d'encadrement, alpha et
	 * bêta, ce qui lui permettra de gagner en vitesse de calcul.
	 * Il s'agit d'une fonction récursive et presque équivalente à la version MiniMax de l'AlphaBêta.
	 * </p>
	 * @param e (type State) : l'état courant.
	 * @param a (type float) : variable d'encadrement alpha.
	 * @param b (type float) : variable d'encadrement bêta.
	 * @param d (type int) : la profondeur de réflexion.
	 * @return <i>Evaluation : le mouvement à jouer et son évaluation.</i>
	*/
	public Evaluation alphabetaNega(State e, float a, float b, int d) {
		this.compteur_joueur = this.compteur_joueur + 1;
		if (d==0 || e.isFinished()) {
			return this.g(e);
		}
		else {
			Move m = null;
			for (int i = 0; i<e.getMoves().size(); i++) {
				State temp = e.play(e.getMoves().get(i));
				Evaluation v = alphabetaNega(temp, -b, -a, d-1);
				if (-v.getF()>a) {
					a = -v.getF();
					m = e.getMoves().get(i);
				}
				if (a>=b) {
					return new Evaluation(m, a);
				}
			}
			return new Evaluation(m, a);
		}
	}
	
}
