package controleur;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import modele.DateV2;
import modele.Evenement;
import modele.InfoFrise;
import modele.ModeleTable;
import vue.Affiche;
import vue.Diaporama;
import vue.FormulaireEvenement;
import vue.FormulaireFrise;
import vue.PanelFrise;

public class Controleur implements ActionListener, MouseListener{

	InfoFrise cInfo;
	Diaporama cDiapo;
	PanelFrise cFrise;
	FormulaireFrise cFormuFrise;
	FormulaireEvenement cFormuEvt;
	
	static Point point;
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>Controleur</i></h1>
	 * <h2><code>public Controleur(InfoFrise info, Diaporama diapo, PanelFrise frise, FormulaireFrise formuF, FormulaireEvenement formuE)</code></h2>
	 * <p>Ce constructeur crée un controleur qui met à jour les éléments de la vue et du modèle. Il est à l'écoute des boutons des formulaires.<br>
	 * Il écoute aussi les boutons du Diaporama. Il implémente <b>MouseListener</b> et se met à l'écoute des clics de la souris dans la table.</p>
	 * <hr>
	 * @param info Une InfoFrise contenant les données saisies par l'utilisateur
	 * @param diapo Un Diaporama contenant les Affiches des évènements de l'InfoFrise
	 * @param frise Un PanelFrise contenant la table créée à partir du modèle ModeleTable
	 * @param formuF Le FormulaireFrise du PanelTotal ayant pour modèle InfoFrise
	 * @param formuE Le FormulaireEvenement du PanelTotal ayant pour modèle Evenement
	 * @see MouseListener
	 */
	public Controleur(InfoFrise info, Diaporama diapo, PanelFrise frise, FormulaireFrise formuF, FormulaireEvenement formuE) {
		cInfo = info;
		cDiapo = diapo;
		cFrise = frise;
		cFormuFrise = formuF;
		cFormuEvt = formuE;
		
		// Les panels contenu par le CardLayout du PanelTotal sont mis écouté par le controleur
		cDiapo.enregistreEcouteur(this);
		cFrise.enregistreEcouteur(this);
		cFormuFrise.enregistreEcouteur(this);
		cFormuEvt.enregistreEcouteur(this);
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>actionPerformed</i></h1>
	 * <h2><code>public void actionPerformed(ActionEvent evt)</code></h2>
	 * <p>actionPerformed du controleur.<br>
	 * Réagit selon les actions de l'utilisateur sur les formulaires, la frise ou le diaporama.</p>
	 * <hr>
	 * @param evt Une action de l'utilisateur l'un des panels du PaneTotal
	 */
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getActionCommand().equals("droite")) {
		// Si l'utilisateur appuie sur le bouton de droite
			cDiapo.getDiapo().next(cDiapo.getCentre()); // On montre l'affiche suivante
		}
		
		else if (evt.getActionCommand().equals("gauche")) {
		// Si l'utilisateur appuie sur le bouton de gauche
			cDiapo.getDiapo().previous(cDiapo.getCentre()); // On montre l'affiche précédente
		}
		
		else if (evt.getActionCommand().equals("creation")) {
		// Si l'utilisateur appuie sur le bouton "créer" de la JMenuBar
			cInfo = cFormuFrise.nouvelleFrise();
		}
		
		else if (evt.getActionCommand().equals("ajouter evt")) {
		// Si l'utilisateur appuie sur le bouton "Ajouter" du FormulaireEvenement
			cInfo.ajout(cFormuEvt.getEvenement()); // On ajoute l'évènement à la HashMap de la classe InfoFrise
			cFormuEvt.reset();
		}
		
		else if (evt.getActionCommand().equals("parcourir image")) {
		// On ouvre un explorateur pour obtenir le chemin
			JFileChooser exploPourImage = new JFileChooser();
			exploPourImage.setCurrentDirectory(new File(System.getProperty("user.home")));
			FileNameExtensionFilter filtre = new FileNameExtensionFilter("Format image", "bmp", "gif", "jpg", "jpeg", "png");
			exploPourImage.setFileFilter(filtre);
			exploPourImage.setAcceptAllFileFilterUsed(false);
			exploPourImage.setFileHidingEnabled(true);
			exploPourImage.setMultiSelectionEnabled(false);
			
			// On regarde si l'utilisateur a bien choisi un fichier
			int resultat = exploPourImage.showDialog(null, "Choisir");
			
			if (resultat == JFileChooser.APPROVE_OPTION) {
				
				// On affiche seulement le nom de l'image
				String nomImage = exploPourImage.getSelectedFile().getAbsolutePath().substring(exploPourImage.getSelectedFile().getAbsolutePath().lastIndexOf(File.separator) + 1);
				if (nomImage.length() > 12) {
					nomImage = nomImage.substring(0, 9) + "...";
				}
				((JButton) evt.getSource()).setText(nomImage);
				
				// Astuce pour récupérer facilement le chemin absolu vers l'image
				((JButton) evt.getSource()).setToolTipText(exploPourImage.getSelectedFile().getAbsolutePath());
				
			} else if (resultat == JFileChooser.ERROR_OPTION) {
				exploPourImage.cancelSelection();
				exploPourImage.setVisible(false);
				JOptionPane.showMessageDialog(null, "Erreur, mauvais fichier sélectionné","Erreur",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>mouseClicked</i></h1>
	 * <h2><code>public void mouseClicked</code></h2>
	 * <p>Méthode à surcharger de MouseListener.<br>
	 * Affiche dans le Diaporama l'évènement correspondant à l'image dans la table.</p>
	 * <hr>
	 * @param mouse MouseEvent, l'utilisateur a cliqué
	 */
	public void mouseClicked(MouseEvent mouse) {
		// TODO Auto-generated method stub
		
		// Si l'utilisateur clique dans le PanelFrise
		if (mouse.getSource() instanceof JTable) {
			JTable table = (JTable) mouse.getSource();
			point = mouse.getPoint();
			int ligne = table.rowAtPoint(point);
			int colonne = table.columnAtPoint(point);
			String image = (String) table.getValueAt(ligne, colonne);
			cDiapo.getDiapo().show(cDiapo.getCentre(), image.substring(0, image.lastIndexOf(";")));
		}
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>mouseEntered</i></h1>
	 * <h2><code>public void mouseEntered(MouseEvent e)</code></h2>
	 * <p>Méthode à surcharger de MouseListener.</p>
	 * <hr>
	 * @param e MouseEvent
	 */
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof JButton) {
			((JButton) e.getSource()).setForeground(Color.ORANGE);
		}
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>mouseExited</i></h1>
	 * <h2><code>public void mouseExited(MouseEvent e)</code></h2>
	 * <p>Méthode à surcharger de MouseListener.</p>
	 * <hr>
	 * @param e MouseEvent
	 */
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof JButton) {
			((JButton) e.getSource()).setForeground(Color.RED);
		}
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>mousePressed</i></h1>
	 * <h2><code>public void mousePressed(MouseEvent e)</code></h2>
	 * <p>Méthode à surcharger de MouseListener.</p>
	 * <hr>
	 * @param e MouseEvent
	 */
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>mouseReleased</i></h1>
	 * <h2><code>public void mouseReleased(MouseEvent e)</code></h2>
	 * <p>Méthode à surcharger de MouseListener.</p>
	 * <hr>
	 * @param e MouseEvent
	 */
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getcInfo</i></h1>
	 * <h2><code>public InfoFrise getcInfo()</code></h2>
	 * <p>Getter qui retourne l'objet <i>InfoFrise</i> appartenant au contrôleur.</p>
	 * <hr>
	 * @return cInfo Modèle de la frise appartenant au contrôleur
	 * @see InfoFrise
	 */
	public InfoFrise getcInfo() {
		return cInfo;
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>setcInfo</i></h1>
	 * <h2><code>public void setcInfo(InfoFrise cInfo)</code></h2>
	 * <p>Setter qui va modifier l'objet <i>InfoFrise</i> du contrôleur.</p>
	 * <hr>
	 * @param cInfo L'objet <i>InfoFrise</i> qui va remplacer celui du contrôleur
	 */
	public void setcInfo(InfoFrise cInfo) {
		this.cInfo = cInfo;
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getcDiapo</i></h1>
	 * <h2><code>public Diaporama getcDiapo()</code></h2>
	 * <p>Getter qui retourne l'objet <i>Diaporama</i> appartenant au contrôleur.</p>
	 * <hr>
	 * @return cDiapo Diaporama appartenant au contrôleur
	 * @see Diaporama
	 */
	public Diaporama getcDiapo() {
		return cDiapo;
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>setcDiapo</i></h1>
	 * <h2><code>public void setcDiapo(Diaporama cDiapo)</code></h2>
	 * <p>Setter qui va modifier l'objet <i>Diaporama</i> du contrôleur.</p>
	 * <hr>
	 * @param cDiapo L'objet <i>Diaporama</i> qui va remplacer celui du contrôleur
	 */
	public void setcDiapo(Diaporama cDiapo) {
		this.cDiapo = cDiapo;
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getcFrise</i></h1>
	 * <h2><code>public PanelFrise getcFrise()</code></h2>
	 * <p>Getter qui retourne l'objet <i>PanelFrise</i> appartenant au contrôleur.</p>
	 * <hr>
	 * @return cFrise PanelFrise appartenant au contrôleur
	 * @see PanelFrise
	 */
	public PanelFrise getcFrise() {
		return cFrise;
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>setcFrise</i></h1>
	 * <h2><code>public void setcFrise(PanelFrise cFrise)</code></h2>
	 * <p>Setter qui va modifier l'objet <i>PanelFrise</i> du contrôleur.</p>
	 * <hr>
	 * @param cFrise L'objet <i>PanelFrise</i> qui va remplacer celui du contrôleur
	 */
	public void setcFrise(PanelFrise cFrise) {
		this.cFrise = cFrise;
	}
}
