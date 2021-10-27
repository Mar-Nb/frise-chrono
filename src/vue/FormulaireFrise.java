package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.Controleur;
import modele.InfoFrise;

/**
 * <style> body{ margin-left: 15px; } </style>
 * <h1> <i>FormulaireFrise</i> </h1>
 * <h2> <code>public class Formulaire</code> </h2>
 * <p> Cette classe est pour le formulaire de création de la frise. Toute les informations permettant de
 * de faire une frise sont recueillis par ce formulaire.<br>A la fin, on obtient un objet de type <i>InfoFrise</i></p>
 * <hr>
 * @author niomb
 * @see InfoFrise
 */
public class FormulaireFrise extends JPanel{
	
	private static final long serialVersionUID = 1L;
	// Objets d'interactions
	JTextField zoneIntitule = new JTextField(50);
	JComboBox <Integer> dateDebut = new JComboBox<Integer>();
	JComboBox <Integer> dateFin = new JComboBox<Integer>();
	JComboBox <Integer> intervalle = new JComboBox<Integer>();
	JButton creation = new JButton("Créer");
	
	// Labels
	JLabel labelIntitule = new JLabel("Intitulé", JLabel.CENTER);
	JLabel labelDateDebut = new JLabel("Date de début", JLabel.CENTER);
	JLabel labelDateFin = new JLabel("Date de fin", JLabel.CENTER);
	JLabel labelIntervalle = new JLabel("Intervalle", JLabel.CENTER);
	
	// Autres
	int debut, fin;
	
	/**
	 * <style> body{ margin-left: 15px ; } </style>
	 * <h1> <i>FormulaireFrise</i> </h1>
	 * <h2> <code>public FormulaireFrise()</code> </h2> 
	 * <p>Ce constructeur met en place le formulaire de saisie d'une frise. Il contient :
	 * <ul> <li> Un TextArea pour le nom de la frise </li>
	 * <li> Un JComboBox pour la date de début de la frise </li>
	 * <li> Un JComboBox pour la date de fin de la frise </li>
	 * <li> Un JComboBox pour l'intervalle d'affichage des dates dans la frise </li>
	 * <li> Un bouton qui appelle la fonction <code>nouvelleFrise()</code> de cette classe</li></ul>
	 * <br>C'est un <strong>BorderLayout</strong>.</p>
	 * <hr>
	 * @see FormulaireFrise#nouvelleFrise()
	 */
	public FormulaireFrise(){
		this.setBackground(Color.WHITE);
		
		Box conteneur = Box.createVerticalBox();
		conteneur.setPreferredSize(new Dimension(300, 300));
		// conteneur.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.ORANGE,2, true), "Création d'une frise"));
		
		JPanel centre = new JPanel();
		
		// Ajouter des lignes permet de rétrécir le reste des éléments
		centre.setLayout(new GridLayout(6,2,20,20)); // ligne,colonne,espace haut, espace bas
		centre.setBackground(Color.WHITE);
		
		centre.add(labelIntitule);
		centre.add(zoneIntitule);
		centre.add(labelDateDebut);
		
		for(int tour = 1582;tour<2020;tour++)
			dateDebut.addItem(tour);
		
		centre.add(dateDebut);
		centre.add(labelDateFin);
		
		for(int tour = 2019;tour>1581;tour--)
			dateFin.addItem(tour);
		
		centre.add(dateFin);
		centre.add(labelIntervalle);
		
		for(int tour = 10;tour>0;tour--)
			intervalle.addItem(tour);
		
		centre.add(intervalle);
		conteneur.add(centre);
		
		// Pour mettre un bouton centré et de taille normale !
		Box ligne = Box.createHorizontalBox();
		ligne.add(Box.createRigidArea(new Dimension(10, 5)));
		ligne.add(creation);
		ligne.add(Box.createRigidArea(new Dimension(10, 5)));
		conteneur.add(ligne);
		
		this.add(conteneur);
		this.getInsets();
	}
	
	/**
	 * <style> body{ margin-left: 15px ; } </style>
	 * <h1> <i>getInsets</i> <h1>
	 * <h2> <code>public Insets getInsets()</code> </h2>
	 * <p>Cette méthode retourne l'espace entre la fenêtre et les bords du panel.</p>
	 * <hr>
	 * @return Insets(100,100,50,100)
	 */
	public Insets getInsets() {
		return new Insets(100,100,50,100);
	}
	
	/**
	 * <style> body{ margin-left: 15px ; } </style>
	 * <h1> <i>nouvelleFrise</i> </h1>
	 * <h2> <code>public InfoFrise nouvelleFrise() </code> </h2>
	 * <p>Cette méthode retourne une nouvelle <i>InfoFrise</i> avec les données saisies par
	 * l'utilisateur.</p>
	 * <hr>
	 * @return InfoFrise
	 */
	public InfoFrise nouvelleFrise() {
		debut = (Integer) dateDebut.getSelectedItem();
		fin = (Integer)dateFin.getSelectedItem();
		return new InfoFrise(zoneIntitule.getText(), (Integer) dateDebut.getSelectedItem(), (Integer)dateFin.getSelectedItem(),
				(Integer)intervalle.getSelectedItem()); //s zoneAdresse.getText());
	}
	
	/**
	 * <style> body{ margin-left: 15px ; } </style>
	 * <h1> <i>enregistreEcouteur</i> </h1>
	 * <h2>  <code>public void enregistreEcouteur(Controleur controleur)</code> </h2>
	 * <p>Cette méthode met le controleur passé en paramètre à l'écoute du bouton du formulaire.</p>
	 * <hr>
	 * @param controleur Un controleur qui gère ce formulaire.
	 */
	public void enregistreEcouteur(Controleur controleur) {
		creation.setActionCommand("creation");
		creation.addActionListener(controleur);
	}
	
	/**
	 * <style> body{ margin-left: 15px ; } </style>
	 * <h1> <i>ecouteCarte</i> </h1>
	 * <h2> <code>public void ecouteCarte(PanelTotal pan)</code> </h2>
	 * <p>Cette méthode met le PanelTotal passé en paramètre à l'écoute
	 * du bouton du formulaire.</p>
	 * <hr>
	 * @param pan Un PanelTotal qui contient ce formulaire
	 */
	public void ecouteCarte(PanelTotal pan) {
		creation.setActionCommand("creation");
		creation.addActionListener(pan);
	}
	
	/**
	 * <style> body{ margin-left: 15px ; } </style>
	 * <h1> <i>getDebut</i> </h1>
	 * <h2> <code>public int getDebut()</code> </h2>
	 * <hr>
	 * @return debut Un int représentant la date de début de la frise
	 */
	public int getDebut() {
		return debut;
	}
	
	/**
	 * <style> body{ margin-left: 15px ; } </style>
	 * <h1> <i> getFin </i> </h1>
	 * <h2> <code> public int getFin()</code> </h2>
	 * <hr>
	 * @return fin Un int représentant la date de fin de la frise
	 */
	public int getFin() {
		return fin;
	}
	
}
