import java.util.*;
import java.io.*;

// arranger ensemble pour etre juste pour 1 jeu
public class Bdd implements TestInterface{
	//private Map<String, TreeSet<Jeu>> jeuxVideo;
	//private TreeSet<Jeu> ensemble = new TreeSet<Jeu>(); 
	Map<String, TreeSet<Jeu>> jeuxVideo = new LinkedHashMap<>();
	//TreeSet<Jeu> ensemble = new TreeSet<Jeu>(); 
	TreeSet<Jeu> ensemble;
    public Bdd() {
		jeuxVideo = new LinkedHashMap<>();
	}


	//ajouter qu'il faut ajouter les consolessi le jeu est deja present
    public void addJeu(Jeu unJeu){

		// Si le fabricant est dans la base de donnees, on y ajoute le jeu, sinon on cree un ensemble avec 
		// ce fabricant
		String fabricant = unJeu.getFabricant();
		ensemble = jeuxVideo.get(fabricant);
		//System.out.println(unJeu.getConsoles());
		String[] uneConsole = new String[unJeu.getConsoles().size()];
		uneConsole = unJeu.getConsoles().toArray(uneConsole);
		
		if(ensemble != null) {
			boolean jeuExiste = false;
			for(Jeu j : ensemble) {

				if(unJeu.equals(j)) {
					for(int i=0; i<uneConsole.length; i++ ){
						j.addConsoles(uneConsole[i]);
						jeuExiste = true;
					}	
				} 
			} 
			if(!jeuExiste) {
				ensemble.add(unJeu);
			} 						
		} else {
			ensemble = new TreeSet<Jeu>();
			ensemble.add(unJeu);
			jeuxVideo.put(fabricant, ensemble);
		}	
		System.out.println(jeuxVideo);

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
		//Map<String, TreeSet<Jeu>> jeuxVideo = new LinkedHashMap<>();
		//TreeSet<Jeu> ensemble;

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
					
					Jeu nouv = new Jeu(fabricant, titre, cote, new LinkedHashSet<String>(Arrays.asList(uneConsole)));
					//System.out.println("nouv " + nouv);
					// Ajout des consoles au jeu
					

					// Si le fabricant est dans la base de donnees, on y ajoute le jeu, sinon on cree un ensemble avec 
					// ce fabricant
					addJeu(nouv);
					/*
					ensemble = jeuxVideo.get(fabricant);

					if(ensemble != null) {
						//caller addJeu nouv.addJeu();			
						boolean jeuExiste = false;
						for(Jeu j : ensemble) {

							if(nouv.equals(j)) {
								for(int i=0; i<uneConsole.length; i++ ){
									j.addConsoles(uneConsole[i]);
									jeuExiste = true;
								}	
							} 
						} 
						if(!jeuExiste) {
							ensemble.add(nouv);
						} 						
					} else {
						ensemble = new TreeSet<Jeu>();
						ensemble.add(nouv);
						jeuxVideo.put(fabricant, ensemble);
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
		//System.out.println(jeuxVideo);	 
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
		// Creation d'un fichier
		/*
		boolean probleme = false;
		FileWriter fw = null;
		try
		{
			fw = new FileWriter(nomACreer);

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

		} catch(java.io.FileNotFoundException erreur){
			System.out.println("Probleme pour preparer l'ecriture\n");
			probleme = true;
		} catch(IOException e) {
			System.out.println("Erreur lors de l'ecriture du fichier");
		}
			System.out.println("Fin de la creation du fichier " + nomACreer + "\n\n");
		}
	
	

*/	
	}


}