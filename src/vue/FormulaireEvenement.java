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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controleur;
import modele.DateV2;
import modele.Evenement;

/**
 * <style> body{ margin-left: 15px; } </style>
 * <h1><i>FormulaireEvenement</i></h1>
 * <h2><code>public class FormulaireEvenement</code>
 * <p>Cette classe met en place le formulaire de création d'évènement. Elle hérite de JPanel et est contenu par le <i>PanelTotal</i>.</p>
 * <hr>
 * @see PanelTotal
 * @see JPanel
 * @author niomb
 *
 */
public class FormulaireEvenement extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	// Objets d'interactions 
	private JTextField zoneTitre = new JTextField();
	private JButton parcourir = new JButton("Parcourir");
	private JComboBox<Integer> dateJour = new JComboBox<Integer>();
	private JComboBox<Integer> dateMois = new JComboBox<Integer>();
	private JComboBox<Integer> dateAnnee = new JComboBox<Integer>();
	private JTextArea zoneDescription = new JTextArea();
	private JComboBox <Integer> importance= new JComboBox<Integer>();
	private JButton ajouter = new JButton("Ajouter"); 
	
	// JLabel
	private JLabel labelTitre = new JLabel("Titre", JLabel.CENTER);
	private JLabel labelDate = new JLabel("Date", JLabel.CENTER);
	private JLabel labelImage = new JLabel("Image", JLabel.CENTER);
	private JLabel labelDescription = new JLabel("Description", JLabel.CENTER);
	private JLabel labelImportance = new JLabel("Importance", JLabel.CENTER);
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>FormulaireEvenement</i></h1>
	 * <h2><code>public FormulaireEvenement()</code></h2>
	 * <p>Ce constructeur met en place le formulaire de saisie d'un évènement.<br>
	 * Il s'appuie sur son modèle Evenement.</p>
	 * <hr>
	 * @see Evenement
	 */
	public FormulaireEvenement(){
		this.setBackground(Color.WHITE);
		
		Box conteneur = Box.createVerticalBox();
		conteneur.setPreferredSize(new Dimension(350, 350));
		
		JPanel centre = new JPanel();
		JPanel panelDate = new JPanel();
		panelDate.setBackground(Color.WHITE);
		
		// Ajouter des lignes permet de rétrécir le reste des éléments
		centre.setLayout(new GridLayout(6,2,20,20));
		centre.setBackground(Color.WHITE);
		centre.add(labelTitre);
		centre.add(zoneTitre);
		centre.add(labelDate);
		
		//Remplissage des JComboBox
		panelDate.add(dateJour);
		for(int jour = 1; jour < 32; jour++)
			dateJour.addItem(jour);
		panelDate.add(dateMois);
		for(int mois = 1; mois < 13; mois++)
			dateMois.addItem(mois);
		panelDate.add(dateAnnee);
		for(int an = 1582; an < 2020; an++)
			dateAnnee.addItem(an);
		centre.add(panelDate);
		
		centre.add(labelImage);
		
		parcourir.setMaximumSize(new Dimension(parcourir.getSize().width,parcourir.getSize().height));
		centre.add(parcourir);
		
		centre.add(labelDescription);

		zoneDescription.setLineWrap(true); // Permet le retour à la ligne automatique dans le JTextArea
		JScrollPane scroll = new JScrollPane(zoneDescription);
		scroll.setBackground(Color.WHITE);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		centre.add(scroll);
		
		centre.add(labelImportance);
		for(int poids = 1; poids < 5; poids++)
			importance.addItem(poids);
		
		centre.add(importance);
		conteneur.add(centre);
		
		// Pour mettre un bouton centré et de taille normale !
		Box ligne = Box.createHorizontalBox();
		ligne.add(Box.createRigidArea(new Dimension(10, 5)));
		ligne.add(ajouter);
		ligne.add(Box.createRigidArea(new Dimension(10, 5)));
		conteneur.add(ligne);
		
		this.add(conteneur);
		this.getInsets();
		
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getInsets</i></h1>
	 * <h2><code>public Insets getInsets()</code></h2>
	 * <p>Cette méthode retourne l'espace entre la fenetre et les marges correspondantes respectivement aux
	 * bords haut, gauche, bas, droite.</p>
	 * <hr>
	 * @return Insets(100,100,50,100)
	 */
	public Insets getInsets() {
		return new Insets(100,100,50,100);
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getEvenement</i></h1>
	 * <h2><code>public Evenement getEvenement</code></h2>
	 * <p>Cette méthode retourne un nouvel évènenement en récupérant les données saisies
	 * dans le panel par l'utilsateur.</p>
	 * <hr>
	 * @return Evenement
	 * @see Evenement#Evenement(DateV2, String, String, String, int)
	 */
	
	public Evenement getEvenement() {
		return new Evenement(new DateV2((Integer) dateJour.getSelectedItem(),
				(Integer) dateMois.getSelectedItem(), (Integer) dateAnnee.getSelectedItem()), zoneTitre.getText(),
				zoneDescription.getText(), parcourir.getToolTipText(), (Integer) importance.getSelectedItem());
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>reset</i></h1>
	 * <h2><code>public void reset()</code></h2>
	 * <p>Cette méthode remet les champs de saisie de texte du formulaire à <b>null</b>.</p>
	 * <hr>
	 * @since 2.0
	 */
	public void reset() {
		zoneTitre.setText(null);
		zoneDescription.setText(null);
		parcourir.setText("Parcourir");
	}
	
	/** 
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>enregistreEcouteur(Controleur controleur)</i></h1>
	 * <h2><code>public void enregistreEcouteur(Controleur controleur)</code></h2>
	 * <p>Cette méthode met le controleur passé en paramètre à l'écoute des
	 * boutons du panel.</p>
	 * <hr>
	 * @param controleur Un controleur qui s'occupe du panel
	 */
	public void enregistreEcouteur(Controleur controleur) {
		ajouter.setActionCommand("ajouter evt");
		ajouter.addActionListener(controleur);
		parcourir.setActionCommand("parcourir image");
		parcourir.addActionListener(controleur);
	}
	
	/** 
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>ecouteCarte</i></h1>
	 * <h2><code>public void ecouteCarte(PanelTotal pan)</code></h2>
	 * <p>Cette méthode met le PanelTotal passé en paramètre à l'écoute des boutons
	 * du panel.</p>
	 * <hr>
	 * @param pan Un panelTotal qui contient le formulaire
	 * @see PanelTotal
	 */
	public void ecouteCarte(PanelTotal pan) {
		ajouter.setActionCommand("ajouter evt");
		ajouter.addActionListener(pan);
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getDateJour</i></h1>
	 * <h2><code>public JComboBox getDateJour()</code></h2>
	 * <p>Getter qui renvoie le JComboBox du jour.</p>
	 * <hr>
	 * @return dateJour JComBox contenant des <b>Integer</b>
	 */
	public JComboBox<Integer> getDateJour() {
		return dateJour;
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>setDateJour</i></h1>
	 * <h2><code>public void setDateJour(JComboBox dateJour)</code></h2>
	 * <p>Setter qui permet de modifier le JComboBox du jour.</p>
	 * <hr>
	 * @param dateJour
	 */
	public void setDateJour(JComboBox<Integer> dateJour) {
		this.dateJour = dateJour;
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getDateMois</i></h1>
	 * <h2><code>public JComboBox getDateMois()</code></h2>
	 * <p>Getter qui renvoie le JComboBox du mois.</p>
	 * <hr>
	 * @return dateMois JComboBox contenant des <b>Integer</b>
	 */
	public JComboBox<Integer> getDateMois() {
		return dateMois;
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>setDateMois</i></h1>
	 * <h2><code>public void setDateMois(JComboBox dateMois)</code></h2>
	 * <p>Setter qui permet de modifier le JComboBox du mois.</p>
	 * <hr>
	 * @param dateMois
	 */
	public void setDateMois(JComboBox<Integer> dateMois) {
		this.dateMois = dateMois;
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getDateAnnee</i></h1>
	 * <h2><code>public JComboBox getDateAnnee()</code></h2>
	 * <p>Getter qui renvoie le JComboBox de l'année.</p>
	 * <hr>
	 * @return dateAnnee JComboBox contenant des <b>Integer</b>
	 */
	public JComboBox<Integer> getDateAnnee() {
		return dateAnnee;
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>setDateAnnee</i></h1>
	 * <h2><code>public void setDateAnnee(JComboBox dateAnnee)</code></h2>
	 * <p>Setter qui permet de modifier le JComboBox de l'année.</p>
	 * <hr>
	 * @param dateAnnee JComboBox contenant des <b>Integer</b>
	 */
	public void setDateAnnee(JComboBox<Integer> dateAnnee) {
		this.dateAnnee = dateAnnee;
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getImportance</i></h1>
	 * <h2><code>public JComboBox getImportance()</code></h2>
	 * <p>Getter qui renvoie le JComboBox de l'importance.</p>
	 * <hr>
	 * @return importance JComboBox contenant des <b>Integer</b>
	 */
	public JComboBox<Integer> getImportance() {
		return importance;
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>setImportance</i></h1>
	 * <h2><code>public void setImportance(JComboBox importance)</code></h2>
	 * <p>Setter qui permet de modifier le JComboBox de l'importance.</p>
	 * <hr>
	 * @param importance JComboBox contenant des <b>Integer</b>
	 */
	public void setImportance(JComboBox<Integer> importance) {
		this.importance = importance;
	}
}
