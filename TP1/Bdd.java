import java.util.*;
import java.io.*;

// arranger ensemble pour etre juste pour 1 jeu
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
		Map<String, TreeSet<Jeu>> jeuxVideo = new LinkedHashMap<>();
		//TreeSet<Jeu> ensemble = new TreeSet<Jeu>(); 
		TreeSet<Jeu> ensemble;

		FileReader fr = null;
		boolean existeFile = true;
		boolean finFichier = false;
		try {
		   fr = new FileReader(nomFile);
		

			if (existeFile) {
			BufferedReader entree = new BufferedReader (fr);

			while (!finFichier) {
				String ligneLue = entree.readLine(); // null si fin de fichier
				if (ligneLue != null){
					//System.out.println(ligneLue);
					
					String[] infoJeux = ligneLue.split(";");
					//System.out.println(infoJeux);
					
					String fabricant = infoJeux[0];
					String titre = infoJeux[1];
					String cote = infoJeux[2]; 
					String consoles = infoJeux[3]; 
					String[] uneConsole = consoles.split(",");

/*
					System.out.println(fabricant);
					System.out.println(titre);
					System.out.println(cote);
					System.out.println(consoles);
					for(int i=0; i<uneConsole.length; i++) {
						System.out.println("uneConsole" + uneConsole[i]);

					}
*/
		

					
					Jeu nouv = new Jeu(fabricant, titre, cote);

					// Ajout des consoles au jeu
					for(int i=0; i<uneConsole.length; i++ ){
						nouv.addConsoles(uneConsole[i]);
					}					

					// Si le fabricant est dans la base de donnees, on y ajoute le jeu, sinon on cree un ensemble avec 
					// ce fabricant
					ensemble = jeuxVideo.get(fabricant);
					if(ensemble != null) {
						ensemble.add(nouv);
					} else {
						ensemble = new TreeSet<Jeu>();
						ensemble.add(nouv);
						jeuxVideo.put(fabricant, ensemble);
					}

					

					//verifier si un jeu contient le fabricant et le titre, si oui, y ajouter juste consoles
					// sinon, ajouter le jeu
					//String verifFab = ensemble.get(fabricant);
					//String verifTitre = ensemble.get(titre);
					//System.out.println(nouv.containsValue("verif " + verifFab));	
					//if(nouv.containsValue(verifFab) )	
					
					/*
					********************fonctione pour ajouter, mais garde juste le dernier fabricant de la liste****************
					ensemble = new TreeSet<Jeu>();
					ensemble.add(nouv);
					jeuxVideo.put(fabricant, ensemble);
					*/

					/*

					while (!chanson.equals("---"))
					{  nouv.addChanson(chanson);
						chanson = entree.readLine();
					}
					*/
					// ensemble.add(nouv);

					
					/*
					ensemble = discographie.get(artiste);
					if(ensemble != null)
						ensemble.add(nouv);
					else{
						ensemble = new TreeSet<Album>();
						ensemble.add(nouv);
						discographie.put(artiste,ensemble);
					}
					*/
					
				}

				else finFichier = true;
			}
			entree.close();
			}
		} catch (java.io.FileNotFoundException e) {
			System.out.println("Probleme d'ouvrir le fichier " + nomFile);
			existeFile = false;
		} catch(IOException e) {
			System.out.println("Erreur lors de la lecture du fichier");
		}
		System.out.println(jeuxVideo);

		  //System.out.println(discographie);

		  //System.out.println("\nLes album contenant Big time sont :\n");
		  //System.out.println(trouveChanson(discographie,"Big time"));

	   

// a completer
		
	}

	public ArrayList<Jeu> chercheConsole(String console){
		//System.out.println(jeuxVideo.trouveConsole("EA"));
	// a completer et changer l'instruction du return
		//System.out.println("asd" + jeuxVideo);
		ArrayList<Jeu> gameWithConsole= new ArrayList();
		Iterator<Jeu> it = ensemble.iterator();
		//boolean trouve = false;

		while(it.hasNext()) {
			Jeu jeuCourant = it.next();
			if(jeuCourant.trouveConsole(console)) {
				gameWithConsole.add(jeuCourant);
				//System.out.println("courant " + jeuCourant);
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
				//System.out.println("courant " + jeuCourant);
			}
		}	
		return jeuxFab;
	}


	public Collection<Jeu> chercheCote(String cote){
	// a completer et potentiellement changer l'instruction du return
		Collection<Jeu> jeuxCote = new ArrayList();
		Iterator<Jeu> it = ensemble.iterator();

		while(it.hasNext()) {
			Jeu jeuCourant = it.next();
			String[] toutesCotes = jeuCourant.getCote().split("");
			for(int i=0; i<toutesCotes.length; i++) {
				if(toutesCotes[i].equalsIgnoreCase(cote)) {
					jeuxCote.add(jeuCourant);
					//System.out.println("courant " + jeuxCote);
				}
			}
			//System.out.println(toutesCotes);

		}	
		return jeuxCote;
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
	

		TreeSet<Jeu> ensemble = new TreeSet<Jeu>();
		FileReader fr = null;
		boolean existeFile = true;
		boolean finFichier = false;
		String nomFile = "jeu.txt";
		try {
			fr = new FileReader(nomFile);
		} catch (java.io.FileNotFoundException e) {
			System.out.println("Probleme d'ouvrir le fichier " + nomFile);
			existeFile = false;
		}

		if (existeFile) {
		BufferedReader entree = new BufferedReader (fr);

		while (!finFichier) {
			String artiste = entree.readLine(); // null si fin de fichier
			if (artiste != null){

				String titre = entree.readLine();
				Album nouv = new Jeu(artiste,titre);
				String chanson = entree.readLine();

				while (!chanson.equals("---"))
				{  nouv.addChanson(chanson);
					chanson = entree.readLine();
				}
					ensemble.add(nouv);
			}
			else finFichier = true;
		}
		entree.close();
		}
		*/
	}
	

}