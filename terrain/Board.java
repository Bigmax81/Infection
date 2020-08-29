package terrain;

/**
 * <b> Description : </b>Classe permettant d'instancier un objet représentant le terrain.
 * 
 * <p>
 * Un terrain est caractérisé par :
 * </p> 
 * 
 * <ul>
	* <li> le nombre de lignes n </li>
	* <li> le nombre de colonnes m </li>
	* <li> un tableau double entrée d'int, terrain, de taille n x m </li>
 * </ul>
 * 
 * @author Emmanuel Garreau
 * 
 * @version V1
*/
public class Board {
	
    	/**
	* <b>Description : </b>le nombre de lignes n.
	*/
	private int n;
	

   	/**
	* <b>Description : </b>La nombre de colonnes m.
	*/
	private int m;
	

    	/**
	* <b>Description : </b>Le terrain, tableau double entrée d'int.
	*/
	private int terrain[][];

	/**
     	* <b>Description : </b>Constructeur de la classe <b>Terrain</b> avec paramètres. Ce constructeur initialise
     	* un terrain de départ pour débuter une partie.
     	* @param n (type int) : la longueur.
     	* @param m (type int) : la largeur.
    	*/
	public Board(int n, int m) {
		this.n = n;
		this.m = m;
		this.terrain = new int[this.n][this.m];
		for (int i = 0; i<this.n; i++) {
			for (int j = 0; j<this.m; j++) {
				this.terrain[i][j] = 0;
			}
		}
		// placement des joueurs
		this.terrain[0][0] = 2;
		this.terrain[this.n-1][this.m-1] = 1;
	}
	
	/**
     	* <b>Description : </b>Deuxième constructeur de la classe <b>Terrain</b> avec paramètres. Ce constructeur permet
     	* d'initialiser un nouveau terrain ayant pour tableau un clone en profondeur d'un autre tableau.
     	* @param tab (type int[][]) : un tableau double entrée d'int.
    	*/
	public Board(int[][]tab) {
		this.n = tab.length;
		this.m = tab[0].length;
		this.terrain = new int[this.n][this.m];

		// On effectue le clonage en profondeur.
		for (int i = 0; i<n; i++) {
			for (int j = 0; j<m; j++) {
				this.terrain[i][j] = tab[i][j];
			}
		}
	}
	
	// Début des Accesseurs, Mutateurs et Descripteur

	/**
     	* <b>Description : </b>Accesseur de la variable de classe <b>n</b>.
     	* @return <i>int : la longueur n.</i>
    	*/
	public int getN() {
		return this.n;
	}

	/**
     	* <b>Description : </b>Mutateur de la variable de classe <b>n</b>.
     	* @param n (type int) : la nouvelle longueur.
    	*/
	public void setN(int n) {
		this.n = n;
	}

	/**
     	* <b>Description : </b>Accesseur de la variable de classe <b>m</b>.
     	* @return <i>int : la largeur m.</i>
    	*/
	public int getM() {
		return this.m;
	}

	/**
     	* <b>Description : </b>Mutateur de la variable de classe <b>m</b>.
     	* @param m (type int) : la nouvelle largeur.
    	*/
	public void setM(int m) {
		this.m = m;
	}
	
	/**
     	* <b>Description : </b>Accesseur de la variable de classe <b>terrain</b>.
     	* @return <i>int[][] : le tableau terrain.</i>
    	*/
	public int[][] getTerrain() {
		return this.terrain;
	}

	/**
     	* <b>Description : </b>Mutateur de la variable de classe <b>terrain</b>.
     	* @param terrain (type int[][]) : le nouveau terrain.
    	*/
	public void setTerrain(int[][] terrain) {
		this.terrain = terrain;
	}
	
	/**
     	* <b>Description : </b>Descripteur d'un objet de la classe <b>Terrain</b>.
     	* @return <i>String : représentation d'un objet de type <b>Terrain</b></i>.
    	*/
	@Override
	public String toString() {
		String str = "     ";
		for (int a = 0; a<this.terrain[0].length; a++) {
			if ((a+1) < 10){
				str = str + (a)+"   ";
			}
			else {
				str = str + (a)+"  ";
			}
		}
		str = str +"\n\n";
		for (int i = 0; i<this.terrain.length; i++) {
			if ((i) < 10){
				str = str + (i)+"    ";
			}
			else {
				str = str + (i)+"   ";
			}
			for (int j = 0; j<this.terrain[i].length; j++) {
				str = str + this.terrain[i][j]+"   ";
			}
			str = str + "\n";
		}
		return "\n" + str + "\n\n";
	}
	
	// Fin des Accesseurs, Mutateurs et Descripteur
	
}
