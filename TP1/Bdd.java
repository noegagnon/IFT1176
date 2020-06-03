import java.util.*;
import java.io.*;


public class Bdd implements TestInterface{
	private Map<String, TreeSet<Jeu>> jeuxVideo;
	private TreeSet<Jeu> ensemble = new TreeSet<Jeu>(); 

    public Bdd() {
		jeuxVideo = new LinkedHashMap<>();
    }


    public void addJeu(Jeu unJeu){
		System.out.println(unJeu.getTitre());
		ensemble.add(unJeu);
		jeuxVideo.put(unJeu.getFabricant(), ensemble);
		System.out.println(jeuxVideo);
		//jeuxVideo.add(unJeu.getFabricant, );
		//if(unJeu.getTitre())
		/*
		// si la map ne contient pas le jeu a ajouter, on l'ajoute avec la collection de console vide.
		if(!jeuxVideo.containsValue(unJeu)) {
			LinkedHashSet<String> consoles = new LinkedHashSet<String>();
			//jeuxVideo.put(unJeu, consoles);
			System.out.println(jeuxVideo);
			//System.out.println("heelo");
		
		}
*/
    }

	public Jeu getJeu(String titre, String fabricant){
		/*
		Jeu aTrouver = new Jeu(fabricant, titre);
		System.out.println(aTrouver);
		if(jeuxVideo.containsKey(aTrouver)) {
			System.out.println("hey");
		
		}
		*/
		//a completer et changer l'instruction du return
		return null;
	}



	public void addBdd(String nomFile){
// a completer
	}

	public void loadBdd(String nomFile){
// a completer
	}

	public ArrayList<Jeu> chercheConsole(String console){
	// a completer et changer l'instruction du return
		return null;
	}

	public Collection<Jeu> getJeuxFabricant(String fabricant){
	// a completer et potentiellement changer l'instruction du return
		return null;
	}

	public void saveBdd(String nomFichier){
	//A completer
	/*
		// Creation d'un fichier
	static void creerFichier(Nation[] pays, int nbPays, int continentVoulu, String nomACreer) 
			throws IOException
	{
		boolean probleme = false;
		FileWriter fw = null;
		try
		{
			fw = new FileWriter(nomACreer);
		} catch(java.io.FileNotFoundException erreur)
		{
			System.out.println("Probleme pour preparer l'ecriture\n");
			probleme = true;
		}
		if(!probleme)
		{
			System.out.println("Debut de la creation du fichier " + nomACreer + "\n");
			PrintWriter aCreer = new PrintWriter(fw);
			
			for(int i=0; i<nbPays; i++)
			{
				if(pays[i].getCodeContinent() == continentVoulu)
					aCreer.printf("%s\n", pays[i]);
			}
			
			aCreer.close();
			System.out.println("Fin de la creation du fichier " + nomACreer + "\n\n");
		}
	}
	*/
	}

}