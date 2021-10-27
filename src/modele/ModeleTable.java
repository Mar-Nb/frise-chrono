package modele;

import javax.swing.table.DefaultTableModel;

import modele.Evenement;

/**
 * <style> body{ margin-left: 15px; } </style>
 * <h1><i>ModeleTable</i></h1>
 * <h2><code>public class ModeleTable</code></h2>
 * <p>Cette classe sert de modèle pour la frise <i>PanelFrise</i>. Elle définit les l'emplacement des entêtes des colonnes et les noms des images dans les 
 * cases de la frise.<br>Elle hérite de <b>DefaultTableModel</b>.</p>
 * <hr>
 * @see PanelFrise
 * @see DefaultTableModel
 * @author niomb
 *
 */
public class ModeleTable extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	String [] intituleCol;
 	String [] intituleColFinal;
 	int nbCol;
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>ModeleTable</i></h1>
	 * <h2><code>public ModeleTable(InfoFrise info)</code></h2>
	 * <p>Ce constructeur met en place une table qui servira de modèle pour la JTable de la frise <i>PanelFrise</i>.</p>
	 * <hr>
	 * @see InfoFrise
	 * @see PanelFrise
	 * @param info Une InfoFrise contenant les données saisies par l'utilisateur
	 */
 	public ModeleTable(InfoFrise info) {
		if(info != null) {
			nbCol = (info.getDateFin() - info.getDateDebut())+1;
			intituleCol = new String[nbCol];
			intituleColFinal = new String[nbCol];
			for(int tour = 0;tour < nbCol; tour++)
				intituleCol[tour] = Integer.toString(info.getDateDebut()+tour);
			
			for(int tour = 0; tour < nbCol;tour++) {
				if( (info.getDateDebut()+tour)%info.getIntervalle()==0 )
					intituleColFinal[tour] = Integer.toString(info.getDateDebut()+tour);
				else if(tour==0)
					intituleColFinal[0] = Integer.toString(info.getDateDebut());
				else if( (info.getDateDebut()+tour)==info.getDateFin() )
					intituleColFinal[tour] = Integer.toString(info.getDateFin());
				else
					intituleColFinal[tour] = "";
			}
		}
		else {
			nbCol = 3;
			intituleColFinal = new String[nbCol];
			for(int i = 0; i< nbCol; i++)
				intituleColFinal[i] = "vide";
		}
			
		this.setColumnCount(nbCol);
		this.setRowCount(4);
		
		this.setColumnIdentifiers(intituleColFinal);
		
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>isCellEditable</i></h1>
	 * <h2><code>public boolean isCellEditable(int indiceLigne, int indiceColonne)</code></h2>
	 * </p>Cette méthode renvoie false pour chaque cellule du ModeleTable.<br>
	 * Ainsi, les cellules ne sont pas modifiables.</p>
	 * <hr>
	 * @param indiceLigne indice de la ligne
	 * @param indiceColonne indice de la colonne 
	 */
 	public boolean isCellEditable (int indiceLigne, int indiceColonne){
		return false;
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getColumnClass</i></h1>
	 * <h2><code>public Class getColumnClass(int num)</code></h2>
	 * <p>Getter qui retourne la classe de la colonne d'indice num.<br>
	 * Ici, toutes les colonnes sont de types String.</p>
	 * <hr>
	 * @param num indice de la colonne
	 */
 	public Class<?> getColumnClass(int num){
		return String.class;
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getIntituleColonne</i></h1>
	 * <h2><code>public String [] getIntituleColonne()</code></h2>
	 * <p>Getter qui retourne le tableau de String contenant les intitulés des colonnes de la table.</p>
	 * <hr>
	 * @return intituleCol Tableau de String contenant les intitulés des colonnes de la table.
	 */
 	public String [] getIntituleColonne() {
		return intituleCol;
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>iconeTable</i></h1>
	 * <h2><code>public void iconeTable(Evenement evt)</code></h2>
	 * <p>Cette méthode permet d'afficher le nom des images correspondant à chaque évènement dans la table.
	 * Les noms apparaissent sous l'année de leur évènement et selon leur importance.</p>
	 * <hr>
	 * @param evt Evenement dont le nom de l'image sera mis dans la frise
	 * @see Evenement
	 */
 	public void iconeTable(Evenement evt) {
		int tour = 0;
		for(String t : this.getIntituleColonne()) {
			if(t.equals(Integer.toString(evt.getDate().getAnnee())))
				this.setValueAt(evt.getImage() + ";" + evt.getNom(), evt.getImportance()-1, tour);
			tour++;
		}
	}
	
}
