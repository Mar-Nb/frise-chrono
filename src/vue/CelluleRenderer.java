package vue;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * <style> body{ margin-left: 15px; } </style>
 * <h1> <i>CelluleRenderer</i> </h1>
 * <h2> <code>public class CelluleRenderer</code> </h2>
 * <p> Cette classe est le renderer de la frise <i>PanelFrise</i> de l'application. Elle est permet d'afficher
 * l'image correspondante à l'évènement affiché sous la bonne date dans la frise.<br>Elle hérite de la classe <i>JLabel</i>
 * pour faire une présentation assez simple. Elle implémente <i>TableCellRenderer</i> puisque c'est un renderer de <i>JTable</i>.</p>
 * <hr>
 * @author niomb
 * 
 * @see PanelFrise
 * @see TableCellRenderer
 * @see JTable
 */
public class CelluleRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	/**
	 * <style> body{ margin-left: 15px ; } </style>
	 * <h1> <i> CelluleRenderer </i> </h1>
	 * <h2> <code>public CelluleRenderer()</code></h2>
	 * <p> Cette classe héritant de la classe <i>JLabel</i> fait appel au constructeur de JLabel et est opaque.</p>
	 * <hr>
	 * @see javax.swing.JComponent#setOpaque(boolean)
	 */
	public CelluleRenderer() {
		super();
		setOpaque(true);
	}

	/**
	 * <style> body{ margin-left: 15px ; } </style>
	 * <h1><i>getTableCellRendererComponent</i></h1>
	 * <h2><code> public Component getTableCellRendererComponent(JTable table, Object valeur, boolean estSelectionne, boolean aLeFocus, int ligne,int col)</code></h2>
	 * <p> Cette méthode est un <i>Override</i> de l'interface <b>TableCellRenderer</b>. Elle définit un toolTipText pour chaque case ayant une valeur.
	 * Elle attribue aussi l'image qui correspond au chemin indiqué dans la cellule de la frise.
	 * <hr>
	 * @return this Un objet de type JLabel
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object valeur, boolean estSelectionne, boolean aLeFocus, int ligne,
			int col) {
		// TODO Auto-generated method stub
		ImageIcon image = null;
		
		if (table != null) {
			if (valeur != null && ((String) valeur).contains(";")) {
				this.setToolTipText(((String) valeur).substring(((String) valeur).lastIndexOf(";") + 1));
				image = new ImageIcon(((String) valeur).substring(0 ,((String) valeur).lastIndexOf(";")));
			}
			
			if (image != null) {
				Image iconeTable = scaleImage(image.getImage(), table.getRowHeight(ligne));
				this.setIcon(new ImageIcon(iconeTable));
			} else {
				this.setIcon(image);
				this.setToolTipText(null);
			}
		}
		
		return this;
	}
	
	/**
	 * <style> body{ margin-left: 15px; margin-right: 15px; } code{ font-family: Consolas; } h1{ text-decoration: underline; } .name{ color: #4286f4; }</style>
	 * <h1><i>scaleImage</i></h1>
	 * <h2><code>public static Image scaleImage(<span class='name'>Image</span> source, <span class='name'>int</span> width, <span class='name'>int</span> height)</code></h2>
	 * 
	 * @param source : (Image)
	 * @param width : (Integer)
	 * @param height : (Integer)
	 * @return img : (Image)
	 */
	public static Image scaleImage(Image source, int width, int height) {
	    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = (Graphics2D) img.getGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(source, 0, 0, width, height, null);
	    g.dispose();
	    return img;
	}
	
	/**
	 * <style> body{ margin-left: 15px; margin-right: 15px; } code{ font-family: Consolas; } h1{ text-decoration: underline; } .name{ color: #4286f4; }</style>
	 * <h1><i>scaleImage</i></h1>
	 * <h2><code>public static Image scaleImage(<span class='name'>Image</span> source, <span class='name'>int</span> size)</code></h2>
	 * <p>Permet de calculer la taille adéquate pour la redimension de l'image et renvoie l'image redimensionnée.</p>
	 * 
	 * @param source : (Image)
	 * @param size : (Integer)
	 * @return scaleImage(source, width, height) : (Image)
	 */
	public static Image scaleImage(Image source, int size) {
	    int width = source.getWidth(null);
	    int height = source.getHeight(null);
	    double f = 0;
	    if (width < height) { 
	    	// Portrait
	        f = (double) height / (double) width;
	        width = (int) (size / f);
	        height = size;
	    } else { 
	    	// Paysage
	        f = (double) width / (double) height;
	        width = size;
	        height = (int) (size / f);
	    }
	    return scaleImage(source, width, height);
	}

}
