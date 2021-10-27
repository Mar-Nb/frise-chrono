package modele;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * <style> body{ margin-left: 15px } </style>
 * <h1> <i>InfoFrise</i> </h1>
 * <h2> <code>public class InfoFrise</code> </h2>
 * <p> Cette classe est le modèle qui contient toutes les informations sur la frise.<br>
 * Elle contient aussi une TreeMap ayant pour clés les dates <i>DateV2</i> des évènements <i>Evenement</i> de la frise
 * et pour valeur des TreeSet contenant un ou plusieurs évènements.</p>
 * <hr>
 * @author niomb
 * @see DateV2
 * @see Evenement
 * @see TreeMap
 * @see TreeSet
 *
 */
public class InfoFrise implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String intitule;
	private int dateDebut;
	private int dateFin;
	private int intervalle;
	
	private TreeMap <DateV2,TreeSet<Evenement>> EvtFrise;
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1> <i>InfoFrise</i> </h1>
	 * <h2> <code> public InfoFrise(String intit, int dateD, int dateF, int inter) </code> </h2>
	 * <p>Ce constructeur initialise ses champs avec les données saisies par l'utilisateur dans le FormulaireFrise.</p>
	 * <hr>
	 * @param intit Le nom de la frise
	 * @param dateD La date de début de la frise
	 * @param dateF La date de fin de la frise
	 * @param inter L'intervalle d'affichage des intitulés dans la table
	 * @see FormulaireFrise
	 */
	public InfoFrise(String intit,int dateD, int dateF, int inter) {
		intitule = intit;
		dateDebut = dateD;
		dateFin = dateF;
		intervalle = inter;
		EvtFrise = new TreeMap <DateV2,TreeSet<Evenement>>();
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1> <i>ajout</i> </h1>
	 * <h2> <code> public void ajout(Evenement evt) </code> </h2>
	 * <p>Méthode qui ajoute un évènement de la classe <i>Evenement</i> dans la TreeMap EvtFrise de cette classe.</p>
	 * <hr>
	 * @param evt Un évènement saisie dans le FormulaireEvenement
	 * @see Evenement
	 */
	public void ajout(Evenement evt) {
		TreeSet<Evenement> set = new TreeSet<Evenement>();
		set.add(evt);
		EvtFrise.put(evt.getDate(), set);
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1> <i>getIntitule</i> </h1>
	 * <h2> <code> public String getIntitule() </code></h2>
	 * <p>Getter qui retourne le nom de la frise.</p>
	 * <hr>
	 * @return intitule String correspondant au nom de la frise
	 */
	public String getIntitule() {
		return intitule;
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1> <i>getDateDebut</i> </h1>
	 * <h2> <code>public int getDateDebut()</code></h2>
	 * <p>Getter qui retourne la date de début de la frise.</p>
	 * <hr>
	 * @return dateDebut int correspondant à la date de début de la frise
	 */
	public int getDateDebut() {
		return dateDebut;
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getDateFin</i></h1>
	 * <h2><code>public int getDateFin()</code></h2>
	 * <p>Getter qui retourne la date de fin de la frise.</p>
	 * <hr>
	 * @return dateFin int correspondant à la date de fin de la frise
	 */
	public int getDateFin() {
		return dateFin;
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getIntervalle</i></h1>
	 * <h2><code>public int getIntervalle()</code></h2>
	 * <p>Getter qui retourne l'intervalle d'affichage des intitulés des colonnes de la JTable.</p>
	 * <hr>
	 * @return intervalle int correspondant à l'intervalle
	 */
	public int getIntervalle() {
		return intervalle;
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getEvtFrise</i></h1>
	 * <h2><code>public Map getEvtFrise</code></h2>
	 * <p>Getter qui retourne la HashMap <DateV2, TreeSet<Evenement>> de cette classe.</p>
	 * <hr>
	 * @return EvtFrise HashMap de cette classe 
	 */
	public Map<DateV2, TreeSet<Evenement>> getEvtFrise() {
		return EvtFrise;
	}

	@Override
	public String toString() {
		return "InfoFrise [intitule=" + intitule + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", intervalle="
				+ intervalle + ", EvtFrise=" + EvtFrise + "]";
	}
	
}
