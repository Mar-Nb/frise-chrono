package modele;

import java.io.Serializable;

public class Evenement implements Comparable <Evenement>, Serializable{

	private static final long serialVersionUID = 1L;
	private DateV2 chDate;
	private String chNom;
	private String chDescri;
	private String chImage;
	private int chImportance;
	
	/**
	 * Ce constructeur instancie les champs avec les param�tres. 
	 * @param date DateV2 correspondant à la date de l'évènement
	 * @param nom String correspondant au nom de l'évènement
	 * @param descri String correspondant à la description de l'évènement
	 * @param image String correspondant au nom de l'image
	 * @param importance int correspondant à l'importance de l'évènement
	 */
	public Evenement(DateV2 date, String nom, String descri,String image,int importance){
		chDate = date;
		chNom = nom;
		chDescri = descri;
		chImage = image;
		chImportance = importance;
	}
	
	//Les différents accesseurs
	/**
	 * Getter qui retourne la date de l'évènement.
	 * @return chDate DateV2 correspondant à la date de l'évènement
	 */
	public DateV2 getDate(){
		return chDate;
	}
	
	/**
	 * Getter qui retourne le nom de l'évènement.
	 * @return chNom String correspondant au nom de l'évènement
	 */
	public String getNom(){
		return chNom;
	}
	
	/**
	 * Getter qui retourne la description de l'évènement.
	 * @return chDescri String à la description de l'évènement
	 */
	public String getDescription() {
		return chDescri;
	}
	
	/**
	 * Getter qui retourne le nom de l'image associée l'évènement.
	 * @return chImage String correspondant au nom de l'image
	 */
	public String getImage() {
		return chImage;
	}
	
	/**
	 * Getter qui retourne l'importance de l'évènement.
	 * @return chImportance int correspondant à l'importance de l'évènement
	 */
	public int getImportance() {
		return chImportance;
	}
	
	//Les différents modifieurs
	/**
	 * Setter pour la date.
	 * @param date DateV2 correspondant à la nouvelle date
	 */
	public void setDate(DateV2 date){
		chDate = date;
	}
	
	/**
	 * Setter pour le nom.
	 * @param nom String correspondant au nouveau nom
	 */
	public void setNom(String nom){
		chNom = nom;
	}
	
	/**
	 * M�thode de comparaison des évènements.
	 * @param evenement Evènement à comparer
	 * @return 0 si les évènements sont les mêmes
	 */
	public int compareTo(Evenement evenement){
		if(this.chDate==evenement.chDate){
			if(this.chNom==evenement.chNom){
				if(this.chDescri==evenement.chDescri)
					return 0;
				else
					return -1;
			}
			return -1;
		}
		return -1;
	}
	
	//toString
	/**
	 * Affiche la date, le nom et la description de l'évènement
	 * @return chDate+chNom+chDescri
	 */
	public String toString(){
			return chDate+"\n"+chNom+",\n\t"+chDescri;
	}
}
