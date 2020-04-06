package optimisation;

import mouvement.Move;

/**
 * <b> Description : </b>Classe tampon permettant d'instancier un espace de stockage pour les deux types d'objets nécessaires à
 * la réussite de l'algorithme d'Intelligence Artificielle.
 * 
 * <p>
 * Une Evaluation est caractérisée par :
 * </p> 
 * 
 * <ul>
	* <li> un mouvement à évaluer </li>
	* <li> une valeur flottante qui lui est attribuée </li>
 * </ul>
 * 
 * @author Emmanuel Garreau 21700336
 * 
 * @version V1
 * 
 * @see optimisation.IA
*/
public class Evaluation {
	
	/**
	 * <b> Description : </b>Un mouvement à évaluer.
	*/
	private Move m;
	
	/**
	 * <b> Description : </b>Evaluation du mouvement.
	*/
	private float f;

	/**
     * <b>Description : </b>Constructeur de la classe <b>Evaluation</b> avec paramètres. Ce constructeur initialise
     * un objet Evaluation qui sera ensuite utilisé au sein de l'architecture des algorithmes d'optimisation.
     * @param m (type Move) : le mouvement à évaluer.
     * @param f (type float) : la valeur du mouvement.
    */
	public Evaluation(Move m, float f) {
		this.m = m;
		this.f = f;
	}

	/**
	 * <b>Description : </b>Accesseur de la variable de classe <b>m</b>.
	 * @return <i>Move : le mouvement à évaluer. </i>
	*/
	public Move getM() {
		return m;
	}

	/**
	 * <b>Description : </b>Mutateur de la variable de classe <b>m</b>.
	 * @param m (type Move) : le nouveau mouvement à évaluer.
	*/
	public void setM(Move m) {
		this.m = m;
	}

	/**
	 * <b>Description : </b>Accesseur de la variable de classe <b>f</b>.
	 * @return <i>float : la valeur du mouvement.</i>
	*/
	public float getF() {
		return f;
	}

	/**
	 * <b>Description : </b>Mutateur de la variable de classe <b>f</b>.
	 * @param f (type float) : la nouvelle valeur du mouvement.
	*/
	public void setF(float f) {
		this.f = f;
	}
	
}
