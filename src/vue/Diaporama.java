package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JPanel;

import controleur.Controleur;
import modele.DateV2;
import modele.Evenement;
import modele.InfoFrise;

public class Diaporama extends JPanel{
	
	private static final long serialVersionUID = 1L;
	final JButton droite = new JButton(">");
	final JButton gauche = new JButton("<");
	JButton [] mesBoutons = {droite, gauche};
	CardLayout diapo = new CardLayout(5,5);
	
	JPanel centre = new JPanel();
	
	/**
	 * Ce constructeur met en place le diaporama.
	 * C'est un BorderLayout dont le centre un panel en cardLayout.
	 * Les cotés EAST et WEST sont pour les boutons qui gère le panorama.
	 * @param info Une InfoFrise contenant les données saisies par l'utilisateur
	 */
	public Diaporama(InfoFrise info) {
		this.setLayout(new BorderLayout(10,10));
		this.setBackground(Color.WHITE);
		
		centre.setBackground(Color.WHITE);
		centre.setLayout(diapo);
		
		if(info==null) {
			String description = "Il serait mieux d'ajouter un évènement, non ?";
			centre.add(new Affiche(new Evenement(new DateV2(), "vide", description, "image"+File.separator+"rien.jpg", 1),"image"+File.separator+"rien.jpg"));
		}
		else {
			centre.removeAll();
			Set<DateV2> cles = info.getEvtFrise().keySet();
			for(DateV2 cle : cles) {
				TreeSet <Evenement> set = info.getEvtFrise().get(cle);
				for(Evenement evt : set) {
					centre.add(new Affiche(evt,evt.getImage()), evt.getImage() + ";" + evt.getNom());
				}
			}
		}
		
		
		// Apparence des boutons
		for (JButton bouton : mesBoutons) {
			bouton.setFont(new Font("Comic sans MS", Font.BOLD, 120));
			bouton.setForeground(Color.RED);
			bouton.setBorderPainted(false);
			bouton.setFocusPainted(false);
			bouton.setBackground(Color.WHITE);
			bouton.setContentAreaFilled(false);
		}
		
		// Tooltip du bouton de droite
		droite.setToolTipText("Aller à l'évènement suivant");
		
		// Tooltip du bouton de gauche
		gauche.setToolTipText("Aller à l'évènement précédent");
		
		this.add(droite, BorderLayout.EAST);
		this.add(centre, BorderLayout.CENTER);
		this.add(gauche,BorderLayout.WEST);
	}
	
	/**
	 * Cette méthode met à l'écoute les boutons du Diaporama	 
	 * @param controleur Un controleur qui gère ce panel
	 */
	public void enregistreEcouteur(Controleur controleur) {
		droite.setActionCommand("droite");
		droite.addActionListener(controleur);
		droite.addMouseListener(controleur);
		
		gauche.setActionCommand("gauche");
		gauche.addActionListener(controleur);
		gauche.addMouseListener(controleur);
	}
	
	/**
	 * Getter qui retourne le cardLayout.
	 * @return diapo Le cardLayout
	 */
	public CardLayout getDiapo() {
		return diapo;
	}
	
	/**
	 * Getter qui retourne le panel situé au centre du Diaporama
	 * @return centre Panel ayant layout cardLayout situé au centre du Diaporama
	 */
	public JPanel getCentre() {
		return centre;
	}
	
	public void setCentre(InfoFrise info){
		this.removeAll();
		Set<DateV2> cles = info.getEvtFrise().keySet();
		for(DateV2 cle : cles) {
			TreeSet <Evenement> set = info.getEvtFrise().get(cle);
			for(Evenement evt : set) {
				this.add(new Affiche(evt,evt.getImage()), evt.getImage() + ";" + evt.getNom());
			}
		}
	}
	
	public void contenuFrise(InfoFrise info) {
		Set<DateV2> cles = info.getEvtFrise().keySet();
		for(DateV2 cle : cles) {
			TreeSet <Evenement> set = info.getEvtFrise().get(cle);
			for(Evenement evt : set) {
				System.out.println(evt.getDate().toString()+" : "+evt.getNom());
			}
		}
	}

}
