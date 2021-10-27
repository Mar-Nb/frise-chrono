package modele;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;

public class LectureEcriture {
	
	/**
	 * Méthode permettant de lire un fichier.
	 * @param parFichier File correspondant fichier devant être lu
	 * @return
	 */
	public static Object lecture(File parFichier){
		ObjectInputStream flux ;
		Object objetLu = null ;
		
		// Ouverture du fichier en mode lecture
		try {
			flux = new ObjectInputStream(new FileInputStream (parFichier)) ;
			objetLu = (Object)flux.readObject() ;
			flux.close() ;
		}
		
		catch (ClassNotFoundException parException){
			System.err.println(parException.toString()) ;
			System.exit(1) ;
		}
		
		catch(IOException parException){
			System.err.println("Erreur lecture du fichier " + parException.toString()) ;
			System.exit(1) ;
		}
		
		return objetLu;
	} // lecture ()
	
	/**
	 * Méthode permettant d'écrire un objet dans un fichier .ser
	 * @param parFichier File correspondant au fichier dans lequel l'objet sera écrit
	 * @param parObjet Object correspondant à l'objet qui sera écrit
	 */
	public static void ecriture(File parFichier, Object parObjet){
		ObjectOutputStream flux = null;
		
		// Ouverture du fichier en mode �criture
		try {
			flux = new ObjectOutputStream (new FileOutputStream (parFichier));
			flux.writeObject(parObjet);
			flux.flush();
			flux.close();
		}
		catch(IOException parException){
			System.err.println("Problème à l'écriture\n" + parException.toString());
			System.exit(1) ;
		}
	}

}
