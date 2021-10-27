package vue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class Aide extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public Aide() {
        super("Aide Utilisateur");
        
		JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setEditable(false);
       
        textPane.setText("<html>" + 
        		"<head>" + 
        		"<style>"
        		+ "body{ font-family: Verdana; } "
        		+ "blockquote{ font-family: Consolas; border-left: 4px solid #B0B0B0; padding-left: 8px; }"
        		+ "hr{ color: #A0A0A0; }"
        		+ "h1{ color: #3399FF; }"
        		+ "h2{ color: #3399FF; }"
        		+ "</style>"
        		+ "<meta charset=\"utf-8\">" + 
        		"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" + 
        		"<title>aide - frise</title>" + 
        		"<link rel=\"stylesheet\" href=\"https://stackedit.io/style.css\" />" + 
        		"</head>" + 
        		"<body class=\"stackedit\">" + 
        		"<div class=\"stackedit__html\"><h1 id=\"page-daide\">Page d’aide</h1><hr><br>" + 
        		"<h2 id=\"introduction\">Introduction</h2><hr>" + 
        		"<p>Salut à vous, ami utilisateur !<br>" + 
        		"Vous utilisez notre programme et si vous lisez ces lignes, c’est que vous cherchez un peu d’aide, n’est ce pas ? Alors c’est parti !<br>" + 
        		"<em>S’il y a des bugs, il y a malheureusement de fortes chances qu’ils ne soient jamais corrigé (dsl).</em></p><br>" + 
        		"<h2 id=\"faire-une-frise---pas-à-pas\">Faire une frise - Pas à pas</h2><hr>" + 
        		"<ol>" + 
        		"<li>La page d’accueil du programme est un formulaire de création de frise. Vous devez y saisir l’intitulé de votre frise (qui sera aussi le nom de votre fichier), la date de début, la date de fin et l’intervalle d’affichage des années.</li>" + 
        		"<li>Une fois la frise créée, une frise vide apparaît. Pour y ajouter des éléments, il faut aller dans “Ajouter un évènement” dans la barre de menu, sous la section <em>Ajout</em>. Cela fait apparaître un formulaire de création d’évènement où vous devez saisir la titre de l’évènement, sa date, sa description, son image et son importance. Enfin, appuyez sur le bouton <em>Ajouter</em> pour que l’évènement soit ajouté à la frise.</li>" + 
        		"<li>Vous pouvez visualiser la frise à tout moment en appuyant sur le bouton “Voir la frise”.</li>" + 
        		"<li>Et voilà, une frise flambant neuve !</li>" + 
        		"</ol><br>" + 
        		"<h2 id=\"sauvegarde-et-chargement\">Sauvegarde et chargement</h2><hr>" + 
        		"<p>Une fois que vous avez fait votre magnifique frise, il ne faut pas oublier de sauvegarder. La sauvegarde s’effectuer dans “Sauvegarde”, sous la section <em>Fichier</em>.<br>" + 
        		"Vous pouvez aussi charger une frise que vous auriez déjà faite en allant dans “Charger une frise”.</p><br>" + 
        		"<blockquote>" + 
        		"<p style='font-weight: bold; padding-bottom: 10px;'>Martin et Léo</p>" + 
        		"</blockquote>" + 
        		"</div>" + 
        		"</body>" + 
        		"</html>");
        
        JScrollPane scroll = new JScrollPane(textPane);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        this.getContentPane().add(scroll);
        this.setSize(900,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ferme la fenêtre d'aide sans fermer FenêtreFrise
	}

}
