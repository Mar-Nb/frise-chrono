package vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FenetreFrise extends JFrame{
	
	private static final long serialVersionUID = 1L;

	File sauvegarde = new File("sauvegarde"+File.separator);
	
	PanelTotal contentPane = new PanelTotal();
	JMenuBar barDeMenu = new JMenuBar();
	
	JMenu fichier = new JMenu("Fichier");
	JMenu frise = new JMenu("Frise");
	JMenu fermer = new JMenu("Quitter");
	JMenu aide = new JMenu("?");
	
	JMenuItem nouveau = new JMenuItem("Nouveau");
	JMenuItem charger = new JMenuItem("Charger une frise");
	JMenuItem sauver = new JMenuItem("Sauvegarder");
	JMenuItem ajouter = new JMenuItem("Ajouter un évènement");
	JMenuItem voir = new JMenuItem("Voir la frise");
	JMenuItem aPropos = new JMenuItem("A propos");
	JMenuItem aide2 = new JMenuItem("Aide");
	JMenuItem quitter = new JMenuItem("Quitter la fenêtre");
	
	/**
	 * Ce constructeur met en place la fenêtre qui contiendra PanelTotal, son panel fils.
	 */
	public FenetreFrise() {
		super("Frise chronologique");
		
		fichier.add(nouveau);
		fichier.addSeparator();
		fichier.add(charger);
		fichier.addSeparator();
		fichier.add(sauver);
		
		frise.add(ajouter);
		frise.addSeparator();
		frise.add(voir);
		
		fermer.add(quitter);
		
		aide.add(aide2);
		aide.addSeparator();
		aide.add(aPropos);
		
		fichier.setMnemonic('F');
		frise.setMnemonic('A');
		fermer.setMnemonic('Q');
		aide.setMnemonic('H');
		
		nouveau.setActionCommand("nouveau");
		sauver.setActionCommand("sauver frise");
		charger.setActionCommand("charger");
		ajouter.setActionCommand("ajouter");
		voir.setActionCommand("voir");
		aPropos.setActionCommand("a propos");
		aide2.setActionCommand("aide");
		quitter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		
		barDeMenu.add(fichier);
		barDeMenu.add(frise);
		barDeMenu.add(fermer);
		barDeMenu.add(aide);
		
		barDeMenu.setBackground(Color.WHITE);
		this.setJMenuBar(barDeMenu);
		
		ecouteCarte(contentPane);
		
		this.setContentPane(contentPane);
		this.setBackground(Color.LIGHT_GRAY);
		this.setSize(700,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(0,0);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new FenetreFrise();

	}
	
	/**
	 * Cette méthode met le PanelTotal passé en paramètre à l'écoute des éléments de la JMenuBar.
	 * @param pan Un PanelTotal contenant tout les éléments de la fenêtre
	 */
	public void ecouteCarte(PanelTotal pan) {
		nouveau.addActionListener(pan);
		ajouter.addActionListener(pan);
		voir.addActionListener(pan);
		charger.addActionListener(pan);
		aPropos.addActionListener(pan);
		sauver.addActionListener(pan);
		aide2.addActionListener(pan);
	}
	
}
