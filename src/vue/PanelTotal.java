package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import controleur.Controleur;
import modele.DateV2;
import modele.Evenement;
import modele.InfoFrise;
import modele.LectureEcriture;
import modele.ModeleTable;

/**
 * <style> body{ margin-left: 15px; } </style>
 * <h1><i>PanelTotal</i></h1>
 * <h2><code>public class PanelTotal</code></h2>
 * <p>Cette classe contient le PanelFrise, le Diaporama, le FormulaireEvenement et le FormulaireFrise.
 * C'est un CardLayout qui gère le changement du panel à afficher.</p>
 * <hr>
 * @author niomb
 * @see CardLayout
 * @see PanelFrise
 * @see Diaporama
 * @see FormulaireEvenement
 * @see FormulaireFrise
 *
 */
public class PanelTotal extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private CardLayout gestionnaire = new CardLayout(5,5);
	private BorderLayout bord = new BorderLayout(8,8);
	private JPanel contenu = new JPanel();
	private JLabel labelTitre;
	
	private boolean estFichierCharge = false;
	private String cheminFichierCharge;
	
	// Contenu du CardLayout
	InfoFrise info;
	Controleur controleur;
	Diaporama diapo;
	PanelFrise frise;
	FormulaireFrise formuFrise ;
	FormulaireEvenement formuEvt ;
	PanelChargement panCharge;
	
	JFileChooser explorateur;
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>PanelTotal</i></h1>
	 * <h2><code>public PanelTotal()</code></h2>
	 * <p>Ce constructeur met en place les formulaires de la fenêtre et affiche le diaporama et la frise.
	 * C'est un <b>CardLayout</b>.</p>
	 */
	public PanelTotal() {
		this.setLayout(gestionnaire);
		this.setBackground(Color.WHITE);
		
		diapo = new Diaporama(info);
		frise = new PanelFrise(info);
		labelTitre = new JLabel("",JLabel.CENTER);
		
		contenu.setLayout(bord);
		contenu.setBackground(Color.WHITE);
		contenu.add(labelTitre,BorderLayout.NORTH);
		contenu.add(diapo,BorderLayout.CENTER);
		contenu.add(frise, BorderLayout.SOUTH);
		
		formuFrise = new FormulaireFrise();
		formuEvt = new FormulaireEvenement();
		panCharge = new PanelChargement();
		controleur = new Controleur(info, diapo, frise, formuFrise, formuEvt);
		
		explorateur = new JFileChooser();
		explorateur.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filtre = new FileNameExtensionFilter("Frise", "chrono");
		explorateur.setFileFilter(filtre);
		explorateur.setAcceptAllFileFilterUsed(false);
		explorateur.setFileHidingEnabled(true);
		explorateur.setMultiSelectionEnabled(false);
		
		formuFrise.ecouteCarte(this);
		formuEvt.ecouteCarte(this);
		panCharge.ecouteCarte(this);
		
		this.add(contenu,"frise");
		this.add(formuFrise,"nouvelle frise");
		this.add(formuEvt, "nouveau evt");
		this.add(panCharge, "chargement");
		
		gestionnaire.show(this, "nouvelle frise");
	}

	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>actionPerformed</i></h1>
	 * <h2><code>public void actionPerformed(ActionEvent evt)</code></h2>
	 * <p>Produit une réaction pour chaque interaction de l'utilisateur avec un élément qu'écoute PanelTotal.</p>
	 * <hr>
	 * @param evt Une action de l'utilisateur dans la fenêtre
	 */
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getActionCommand()=="nouveau")
		// Si l'utilisateur appuie sur le bouton "Nouveau" de la JMenuBar
			gestionnaire.show(this, "nouvelle frise"); // On affiche le FormulaireFrise
			
		else if(evt.getActionCommand()=="creation") {
		// Si l'utilisateur appuie sur le bouton "Créer" du FormulaireFrise 
			info = formuFrise.nouvelleFrise();
			labelTitre.setText("Frise chronologique : "+info.getIntitule());
			labelTitre.setFont(new Font("Verdana",Font.BOLD,18));
			gestionnaire.show(this, "frise"); // On affiche le panel qui contient le Diaporama et le PanelFrise
		}
		
		else if(evt.getActionCommand()=="ajouter") { 
		// Si l'utilisateur appuie sur le bouton "Ajouter un évènement" de la JMenuBar
			if (controleur.getcInfo() != null) {
				gestionnaire.show(this, "nouveau evt"); // On affiche le FormulaireEvenement
			} else {
				JOptionPane.showMessageDialog(this, "Vous devez créer une frise d'abord.", "Frise inexistante", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(evt.getActionCommand()=="voir") {
		// Si l'utilisateur appuie sur le bouton "Voir la frise" du FormulaireEvenement
			if (controleur.getcInfo() == null) {
				JOptionPane.showMessageDialog(this, "Vous devez créer une frise d'abord.", "Frise inexistante", JOptionPane.ERROR_MESSAGE);
			} else if (! controleur.getcInfo().getEvtFrise().isEmpty()) {
				controleur.getcDiapo().getCentre().removeAll();
				ModeleTable nouvelleTable = new ModeleTable(controleur.getcInfo());
				
				// On parcourt la TreeMap de la classe InfoFrise
				Set<DateV2> cles = controleur.getcInfo().getEvtFrise().keySet();
				for (DateV2 cle : cles) {
					TreeSet <Evenement> set = controleur.getcInfo().getEvtFrise().get(cle);
					for (Evenement event : set) {
						controleur.getcDiapo().getCentre().add(new Affiche(event,event.getImage()),event.getImage()); // On ajoute au panel du Diaporama les affiches
						nouvelleTable.iconeTable(event); // On ajoute les images dans la nouvelle table
					}
				}
				
				controleur.getcFrise().getTable().setModel(nouvelleTable);
				
				controleur.getcDiapo().getDiapo().first(controleur.getcDiapo().getCentre()); // Le PanelFrise est chargé avec la nouvelle table
				gestionnaire.show(this, "frise"); // On affiche le panel qui contient le Diaporama et le PanelFrise
			} else {
				JOptionPane.showMessageDialog(this, "Vous n'avez ajouté aucun événement.", "Frise vide", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(evt.getActionCommand()=="a propos") {
		// Si l'utilisateur appuie sur le bouton "?" de la JMenuBar
			JOptionPane.showMessageDialog(this, "Version du projet 1.3 - 2018\nMartin et Léo\nMerci de votre indulgence\n\nEDIT 2019: "
					+ "Version 2.0 - Martin", "A propos", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if(evt.getActionCommand()=="aide") {
			new Aide(); // Affiche une page d'aide généré en html
		}
		
		else if(evt.getActionCommand()=="sauver frise"){
			// Si l'utilisateur appuie sur le bouton "Sauvegarder" du FormulaireEvenement
			if (! estFichierCharge) {
				int sauvegarde = explorateur.showSaveDialog(getParent());
				
				if (sauvegarde == JFileChooser.APPROVE_OPTION) {
					String nomFichier = explorateur.getSelectedFile().getName();
					File fichier = null;
					
					// On vérifie si le fichier à la bonne extension
					if (nomFichier.endsWith(".chrono")) {
						fichier = new File(explorateur.getSelectedFile().getAbsolutePath());
					} else {
						fichier = new File(explorateur.getSelectedFile().getAbsolutePath().concat(".chrono"));
					}
					
					LectureEcriture.ecriture(fichier, controleur.getcInfo()); // On crée un fichier ".chrono" contenant les informations de la frise
					JOptionPane.showMessageDialog(null, "Sauvegarde effectué !", "Sauvegarde", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				LectureEcriture.ecriture(new File(cheminFichierCharge), controleur.getcInfo());
				JOptionPane.showMessageDialog(null, "Sauvegarde effectué !", "Sauvegarde", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		else if(evt.getActionCommand()=="charger")
			gestionnaire.show(this, "chargement"); // Met en avant le PanelChargement
		
		else if(evt.getActionCommand()=="bouton charger") { // Si on appuie sur le bouton "charger" du PanelChargement
			int chargement = explorateur.showOpenDialog(getParent());
			
			if (chargement == JFileChooser.APPROVE_OPTION && explorateur.getSelectedFile().getName().endsWith(".chrono")) {
				info = (InfoFrise) LectureEcriture.lecture(explorateur.getSelectedFile());
				estFichierCharge = true;
				cheminFichierCharge = explorateur.getSelectedFile().getAbsolutePath();
				
				labelTitre.setText("Frise chronologique : "+info.getIntitule());
				labelTitre.setFont(new Font("Verdana",Font.BOLD,18));
				
				diapo.getCentre().removeAll();
				ModeleTable nouvelleTable = new ModeleTable(info);
				Set <DateV2> cles = info.getEvtFrise().keySet();
				for(DateV2 cle : cles) {
					TreeSet <Evenement> set = info.getEvtFrise().get(cle);
					for(Evenement event : set) {
						diapo.getCentre().add(new Affiche(event,event.getImage()),event.getImage());
						nouvelleTable.iconeTable(event);
					}
				}
				
				frise.getTable().setModel(nouvelleTable);
				diapo.getDiapo().first(diapo.getCentre());
				
				controleur.setcInfo(info);
				controleur.setcDiapo(diapo);
				controleur.setcFrise(frise);
				gestionnaire.show(this, "frise");
			}
		}
		
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getContenu</i></h1>
	 * <h2><code>public JPanel getContenu()</code>
	 * <p>Getter qui retourne le panel qui contient le diaporama et la frise.</p>
	 * <hr>
	 * @return contenu Panel qui contient un diaporama et une frise
	 */
	public JPanel getContenu() {
		return contenu;
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getDiapo</i></h1>
	 * <h2><code>public Diaporama getDiapo()</code></h2>
	 * <p>Getter qui retourne le diaporama.</p>
	 * @return diapo Un cardLayout
	 */
	public Diaporama getDiapo() {
		return diapo;
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getFrise</i></h1>
	 * <h2><code>public PanelFrise getFrise()</code></h2>
	 * <p>Getter qui retourne la frise.</p>
	 * <hr>
	 * @return frise Un PanelFrise 
	 */
	public PanelFrise getFrise() {
		return frise;
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getInfoFrise</i></h1>
	 * <h2><code>public InfoFrise getInfoFrise()</code></h2>
	 * <p>Getter qui retourne le modèle de la frise.</p>
	 * <hr>
	 * @return info Modèle de la frise
	 */
	public InfoFrise getInfofrise() {
		return info;
	}
	
	/**
	 * <style> body{ margin-left: 15px; } </style>
	 * <h1><i>getLabelIntitule</i></h1>
	 * <h2><code>public JLabelIntitule()</code></h2>
	 * <p>Getter qui retourne le label contenant le titre de la frise.</p>
	 * <hr>
	 * @return labelTitre Label du titre de la frise
	 */
	public JLabel getLabelIntitule() {
		return labelTitre;
	}

}
