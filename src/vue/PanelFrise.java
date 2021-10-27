package vue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import controleur.Controleur;
import modele.InfoFrise;
import modele.ModeleTable;

public class PanelFrise extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JTable table;
	JScrollPane scrollPane;
	
	/**
	 * Ce constructeur utilise son mod�le ModeleTable pour construire une table
	 * qui est ensuite mise dans un scrollPane.
	 * @param info Une InfoFrise que l'utilisateur a saisie
	 */
	public PanelFrise(InfoFrise info) {
		this.setBackground(Color.WHITE);
		
		ModeleTable modele = new ModeleTable(info);
		table = new JTable(modele);
		table.setRowHeight(65);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setBackground(new Color(253,70,38));
		table.getTableHeader().setForeground(Color.WHITE);
		table.setDefaultRenderer(String.class, new CelluleRenderer());
		
		scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(table);
		scrollPane.setPreferredSize(new Dimension(1100,300));
		
		this.add(scrollPane);
	}

	/**
	 * Cette m�thode met le controleur pass� en param�tre � l'�coute de la table.
	 * @param controleur Un controleur qui g�re ce panel
	 */
	public void enregistreEcouteur(Controleur controleur) {
		// TODO Auto-generated method stub
		table.addMouseListener(controleur);
	}
	
	/**
	 * Getter qui retourne la table du panel.
	 * @return table 
	 */
	public JTable getTable() {
		return table;
	}
	
	public JScrollPane getScroll() {
		return scrollPane;
	}
	
}
