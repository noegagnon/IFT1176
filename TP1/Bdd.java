import java.util.*;
import java.io.*;


public class Bdd implements TestInterface{
	private Map<String, TreeSet<Jeu>> jeuxVideo;
	private TreeSet<Jeu> ensemble = new TreeSet<Jeu>(); 

    public Bdd() {
		jeuxVideo = new LinkedHashMap<>();
	}


	//ajouter qu'il faut ajouter les consoles (et la cote) si le jeu est deja present
    public void addJeu(Jeu unJeu){
		//System.out.println(unJeu.getTitre());
		ensemble.add(unJeu);
		jeuxVideo.put(unJeu.getFabricant(), ensemble);
		//System.out.println(jeuxVideo);
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
		Jeu aTrouver = new Jeu(fabricant, titre);

		if(ensemble.contains(aTrouver)) {
			Iterator<Jeu> it = ensemble.iterator();
		 	//boolean trouve = false;

		 	while(it.hasNext()) {
		 		Jeu courant = it.next();
		 		if (aTrouver.equals(courant)) {
		 			System.out.println("courant " + courant);
		 			//trouve = true;
					return courant;
		 		}
		 	}

		} else {
			System.out.println("Le jeu " + aTrouver.getTitre() + " pas trouve");
		}
		return null;
	}


	public void addBdd(String nomFile){
// a completer
	}

	public void loadBdd(String nomFile){
// a completer
	}

	public ArrayList<Jeu> chercheConsole(String console){
		//System.out.println(jeuxVideo.trouveConsole("EA"));
	// a completer et changer l'instruction du return
		System.out.println("asd" + jeuxVideo);
		ArrayList<Jeu> gameWithConsole= new ArrayList();
		Iterator<Jeu> it = ensemble.iterator();
		//boolean trouve = false;

		while(it.hasNext()) {
			Jeu jeuCourant = it.next();
			if(jeuCourant.trouveConsole(console)) {
				gameWithConsole.add(jeuCourant);
				System.out.println("courant " + jeuCourant);
			}

		}
		return gameWithConsole;
	}

	public Collection<Jeu> getJeuxFabricant(String fabricant){
	// a completer et potentiellement changer l'instruction du return
		Collection<Jeu> jeuxFab = new ArrayList();
		Iterator<Jeu> it = ensemble.iterator();

		while(it.hasNext()) {
			Jeu jeuCourant = it.next();
			if(jeuCourant.getFabricant().equalsIgnoreCase(fabricant)) {
				jeuxFab.add(jeuCourant);
				System.out.println("courant " + jeuCourant);
			}
		}	
		return jeuxFab;
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