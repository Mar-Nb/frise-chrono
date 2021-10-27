package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Evenement;

public class Affiche extends JPanel{


	private static final long serialVersionUID = 1L;
	/*
	 * Le but de cette classe est de réunir toutes les informations que poss�de un �v�nement et de le rendre graphique,
	 * comme une sorte d'affiche.
	*/
	
	private ImageIcon icone;
	private String date;
	private String titre;
	private String description;
	
	private JLabel texte;
	private JLabel affiche;
	
	/**
	 * Ce constructeur met en place un panel contenant l'image, le titre, et la description d'un évènement
	 * @param evenement Un évènement contenu dans la frise
	 * @param image L'image associée à l'évènement
	 */
	public Affiche(Evenement evenement, String image) {
		this.setLayout(new BorderLayout(30,3));
		this.setBackground(Color.WHITE);
		
		date = evenement.getDate().toString();
		titre = evenement.getNom();
		description = evenement.getDescription();
		
		String txt = TexteIcone(date,titre,description);
		
		icone = new ImageIcon(image);
		icone = new ImageIcon(CelluleRenderer.scaleImage(icone.getImage(),350));
		affiche = new JLabel(icone);
		this.add(affiche, BorderLayout.WEST);
		
		texte = new JLabel(txt);
		this.add(texte, BorderLayout.CENTER);
		
	}
	
	/**
	 * Cette méthode sert à mettre la date, le titre et la description dans un format html.
	 * @param date Date de l'évènement
	 * @param titre Titre de l'évènement
	 * @param descri Description de l'évèment
	 * @return Cela renvoie une chaine contenant les éléments en paramètre sous un format html
	 */
	public String TexteIcone(String date, String titre, String descri) {
		int taille = 0;
		String [] tempo = descri.split("\\s");
		descri = new String();
		for(String s : tempo) {
			if(taille > 60) {
				descri += "<br>";
				taille = 0;
			}
			taille += s.length();
			descri += s+" ";
		}
		
		return "<html>"+"<head><style> body{ font-family: Verdana; }"+"<body>"+
			"<h4>"+date+"</h4>"+
						"<br><br>"+
			"<h2 style='color: #CC0000;'><strong>"+titre+"</strong></h2>"+
						"<br><br>"+
			"<p>"+descri+"</p>"+
						"</body>"+"</html>";
	}
	
	public String toString() {
		return titre;
	}
}