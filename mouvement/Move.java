package mouvement;

import java.util.Arrays;

/**
 * <b> Description : </b>Classe permettant d'instancier un objet représentant un mouvement.
 * 
 * <p>
 * Un mouvement est caractérisé par :
 * </p> 
 * 
 * <ul>
	* <li> un tableau start contenant les coordonnées de départ </li>
	* <li> un tableau end contenant les coordonnées d'arrivée </li>
 * </ul>
 * 
 * @author Emmanuel Garreau 21700336
 * 
 * @version V1
*/
public class Move {
	
	/**
	 * <b>Description : </b>le tableau start contenant les coordonnées de départ.
	*/
	private int start[];
	
	/**
	 * <b>Description : </b>le tableau end contenant les coordonnées d'arrivée.
	*/
	private int end[];
	
	/**
	 * <b>Description : </b>booléen permettant d'indiquer s'il s'agit d'un déplacement (false) ou d'une
	 * duplication (true).
	*/
	private boolean dep_ou_dup;

	/**
     * <b>Description : </b>Constructeur de la classe <b>Move</b> avec paramètres. Ce constructeur initialise
     * un mouvement avec des coordonnées de départ et des coordonnées d'arrivée.
     * @param start (type int[]) : coordonnées de départ.
     * @param end (type int[]) : coordonnées d'arrivée.
    */
	public Move(int start[], int end[]) {
		this.start = start;
		this.end = end;
		if ((start[0]+start[1])-(end[0]+end[1]) == 2 || (start[0]+start[1])-(end[0]+end[1]) == -2) {
			this.dep_ou_dup = false; // déplacement
		}
		else {
			this.dep_ou_dup = true; // duplication
		}
	}
	
	// Début des Accesseurs, Mutateurs et Descripteur
	
	/**
     * <b>Description : </b>Accesseur de la variable de classe <b>start</b>.
     * @return <i>int[] : le tableau des coordonnées de départ.</i>
    */
	public int[] getStart() {
		return start;
	}

	/**
     * <b>Description : </b>Mutateur de la variable de classe <b>start</b>.
     * @param start (type int[]) : les nouvelles coordonnées de départ.
    */
	public void setStart(int[] start) {
		this.start = start;
	}

	/**
     * <b>Description : </b>Accesseur de la variable de classe <b>end</b>.
     * @return <i>int[] : le tableau des coordonnées d'arrivée.</i>
    */
	public int[] getEnd() {
		return end;
	}

	/**
     * <b>Description : </b>Mutateur de la variable de classe <b>end</b>.
     * @param end (type int[]) : les nouvelles coordonnées d'arrivée.
    */
	public void setEnd(int[] end) {
		this.end = end;
	}

	/**
     * <b>Description : </b>Accesseur de la variable de classe <b>dep_ou_dup</b>.
     * @return <i>booleen : indicateur.</i>
    */
	public boolean isDep_ou_dup() {
		return dep_ou_dup;
	}

	/**
     * <b>Description : </b>Mutateur de la variable de classe <b>dep_ou_dup</b>.
     * @param dep_ou_dup (type booleen) : le nouvel indicateur.
    */
	public void setDep_ou_dup(boolean dep_ou_dup) {
		this.dep_ou_dup = dep_ou_dup;
	}

	/**
     * <b>Description : </b>Descripteur d'un objet de la classe <b>Move</b>.
     * @return <i>String : représentation d'un objet de type <b>Move</b></i>.
    */
	@Override
	public String toString() {
		if (this.dep_ou_dup == false) {
			return "Move [start=" + Arrays.toString(start) + ", end=" + Arrays.toString(end) + ", déplacement]";
		}
		else {
			return "Move [start=" + Arrays.toString(start) + ", end=" + Arrays.toString(end) + ", duplication]";
		}
	}
	
	// Fin des Accesseurs, Mutateurs et Descripteur

}
