package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelChargement extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JButton charger;
	private JComboBox <String> boxFichiers;
	private String [] listeFichiers;
	
	public PanelChargement() {
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.gridx = 0;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.anchor = contrainte.fill = GridBagConstraints.CENTER;
		contrainte.insets = new Insets(10,10,10,10);
		
		JLabel label = new JLabel("Choisissez un fichier :", JLabel.CENTER);
		this.add(label,contrainte);
		contrainte.gridy++;
		contrainte.gridheight++;
		
		charger = new JButton("Parcourir");
		charger.setActionCommand("bouton charger");
		this.add(charger, contrainte);
		
	}
	
	public void ecouteCarte(PanelTotal panT) {
		charger.addActionListener(panT);
	}

}
