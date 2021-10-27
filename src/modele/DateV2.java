package modele;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateV2 implements Comparable <DateV2>, Serializable{
		
	private static final long serialVersionUID = 1L;
	private int chJour;
	private int chMois;
	private int chAnnee;
	private int chJourSemaine;
	private int chNumSemaine ;
	
	/**
	 * Constructeur ne prenant aucun param�tre et instanciant les champs � la date actuel.
	 */
	public DateV2(){
		GregorianCalendar aujourdhui = new GregorianCalendar();
		
		chAnnee = aujourdhui.get(Calendar.YEAR);
		chMois = aujourdhui.get(Calendar.MONTH)+1; //janvier=0, février=1, ...
		chJour = aujourdhui.get(Calendar.DAY_OF_MONTH);
		chJourSemaine = aujourdhui.get(Calendar.DAY_OF_WEEK);
		chNumSemaine = aujourdhui.get(Calendar.WEEK_OF_YEAR) ;
		
	}
	
	/**
	 * Constructeur instanciant les champs avec les paramètres reçus
	 * @param jour int correspondant au jour
	 * @param mois int correspondant au mois
	 * @param annee int correspondant à l'année
	 */
	public DateV2(int jour, int mois, int annee) {
		
		chJour = jour;
		chMois = mois;
		chAnnee = annee;
		GregorianCalendar aujourdhui = new GregorianCalendar(chAnnee,chMois-1,chJour);
		chJourSemaine = aujourdhui.get(Calendar.DAY_OF_WEEK);
		chNumSemaine = aujourdhui.get(Calendar.WEEK_OF_YEAR) ;
	}
	
	/**
	 * Méthode qui retourne le dernier jour du mois en fonction du mois et de l'année passés en paramètre
	 * @param parMois int correspondant au mois
	 * @param parAnnee int correspondant à l'année
	 * @return Un int correspondant au dernier jour du mois
	 */
	public static int dernierJourDuMois(int parMois, int parAnnee){
		switch(parMois){
			case 2: if(estBissextile(parAnnee)){
				return 29;
			}
			else{
				return 28;
			}
				
			case 4: case 6: case 9: case 11: return 30;
			default: return 31;
		}
	}
	
	/**
	 * Méthode vérifiant si le l'année est bissextile
	 * @param parAnnee int correspondant à l'année
	 * @return true si l'ann�e est bissextile
	 */
	public static boolean estBissextile(int parAnnee){
		return (parAnnee%4 == 0 && parAnnee%100 != 0) || (parAnnee%400 == 0);
	}
	
	/**
	 * Méthode qui vérifie si la date est valide
	 * @return true si la date est valide
	 */
	public boolean estValide(){
		int dernierJour = dernierJourDuMois(chMois,chAnnee);
		boolean bissextile = estBissextile(chAnnee);
		if(dernierJour > 30){
			return(chAnnee > 1582 && chMois >= 1 && chMois <= 12 && chJour < 31);	
		}
		else if(chMois == 2){
			if(bissextile){
				return(chAnnee > 1582 && chJour <= 29);
			}	
			else{
				return(chAnnee > 1582 && chJour <=28);
			}
		}
		return false;
	}
	
	/**
	 * Méthode de comparaison des dates.
	 * @param date Une date à comparer
	 * @return 0 si les dates sont les mêmes, -1 sinon
	 */
	public int compareTo(DateV2 date){
		if(this.chAnnee==date.chAnnee){
			if(this.chMois==date.chMois){
				if(this.chJour==date.chJour)
					return 0;
				else if(this.chJour>date.chJour)
					return 1;
				else
					return-1;
			}
			else if(this.chMois>date.chMois)
				return 1;
			else
				return -1;
		}
		else if(this.chAnnee>date.chAnnee)
			return 1;
		else
			return -1;
	}
	
	/**
	 * Méthode qui renvoie la date du lendemain de la date appelant cette méthode.
	 * @return la date suivant celle appelant cette méthode
	 */
	public DateV2 dateDuLendemain ()   {	
	    if (this.chJour < dernierJourDuMois(this.chMois,this.chAnnee))
			     return  new DateV2 (this.chJour+1,this.chMois,this.chAnnee);
			else if (this.chMois < 12)
					return new DateV2 (1,this.chMois+1,this.chAnnee);
				 else return new DateV2 (1,1,this.chAnnee+1);	
	  } 
	
	/**
	 * Méthode qui renvoie le premier jour de la semaine contenant la date appelant cette méthode.
	 * @return datePrem DateV2 correspondant au premier jour de la semaine
	 */
	public DateV2 premierJourSemaine() {
		DateV2 datePrem = this;
		while (datePrem.getJourSemaine()!=2){
			datePrem = datePrem.dateDeLaVeille();
		}
		return datePrem;
	}
	
	/**
	 * Méthode qui retourne la date précédent celle qui a appelé cette méthode.
	 * @return Une date correspondant à la veille
	 */
	public DateV2 dateDeLaVeille(){
		if(this.chJour>1)
			return new DateV2(this.chJour-1,this.chMois,this.chAnnee);
		else if(this.chMois > 1)
			return new DateV2(DateV2.dernierJourDuMois(this.chMois-1,this.chAnnee),this.chMois-1,this.chAnnee);
		else
			return new DateV2(31,12,this.chAnnee-1);
	}
	
	/**
	 * Getter qui retourne le jour de la date
	 * @return chJour int correspondant au jour
	 */
	public int getJour(){
		return this.chJour;
	}
	
	/**
	 * Getter qui retourne le mois de la date
	 * @return chMois int correspondant au mois
	 */
	public int getMois(){
		return this.chMois;
	}
	
	/**
	 * Getter qui retourne l'année de la date
	 * @return chAnnee int correspondant à l'année
	 */
	public int getAnnee(){
		return this.chAnnee;
	}
	
	/**
	 * Getter qui retourne le numéro de la semaine de la date
	 * @return chNumSemaine int correspondant au numéro de la semaine
	 */
	public int getNumSemaine() {
		return this.chNumSemaine ;
	}
	
	/**
	 * Getter qui retourne le numéro du jour dans la semaine
	 * @return chJourSemaine int correspondant au numéro du jour dans la semaine
	 */
	public int getJourSemaine(){
		return this.chJourSemaine;
	}
	
	/**
	 * Affiche la date d'aujourdhui au format jj/mm/aaaa.
	 */
	public String toString(){
		//String [] strJours = {"Dimanche","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
		//String [] strMois = {"janvier","février","mars","avril","mai","juin","juillet","août","septembre","octobre","novembre","décembre"};
		
		//return strJours[chJourSemaine-1]+" "+chJour+" "+strMois[chMois-1]+" "+chAnnee;
		return chJour+"/"+chMois+"/"+chAnnee;
	}
}
