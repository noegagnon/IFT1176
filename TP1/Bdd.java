/*
NOM: Gagnon
PRENOM: Noemie
*/

import java.util.*;
import java.io.*;

public class Bdd implements TestInterface{

	private Map<String, TreeSet<Jeu>> jeuxVideo = new LinkedHashMap<>();
	private TreeSet<Jeu> ensemble;

    public Bdd() {
		jeuxVideo = new LinkedHashMap<>();
	}

	// Ajoute le jeu passe en parametre a la banque de donnees
    public void addJeu(Jeu unJeu){
		// Si le fabricant est dans la base de donnees, on y ajoute le jeu, sinon on cree un ensemble avec 
		// ce fabricant
		String fabricant = unJeu.getFabricant();
		ensemble = jeuxVideo.get(fabricant);
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
    }

	// Retourne le jeu passe en parametre
	public Jeu getJeu(String titre, String fabricant){
		Jeu aTrouver = new Jeu(fabricant, titre);

		if(ensemble.contains(aTrouver)) {
			Iterator<Jeu> it = ensemble.iterator();
		 	while(it.hasNext()) {
		 		Jeu courant = it.next();
		 		if (aTrouver.equals(courant)) {
					return courant;
		 		}
		 	}
		} else {
			System.out.println("Le jeu " + aTrouver.getTitre() + " pas trouve");
		}
		return null;
	}

	// Ajoute les donnes du fichier passee en parametre a la banque de donnees
	public void addBdd(String nomFile){
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
						String[] infoJeux = ligneLue.split(";");					
						String fabricant = infoJeux[0];
						String titre = infoJeux[1];
						String cote = infoJeux[2]; 
						String consoles = infoJeux[3]; 
						String[] uneConsole = consoles.split(",");
						Jeu nouv = new Jeu(fabricant, titre, cote, new LinkedHashSet<String>(Arrays.asList(uneConsole)));
						addJeu(nouv);
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
	}

	public void loadBdd(String nomFile){
		Map<String, TreeSet<Jeu>> jeuxVid = new LinkedHashMap<>();
		TreeSet<Jeu> ens;
		addBdd(nomFile);
		// a completer
	}

	// Retourne les jeu pouvant se jouer sur la console passee en parametre
	public ArrayList<Jeu> chercheConsole(String console){
		ArrayList<Jeu> gameWithConsole= new ArrayList();
		Iterator<Jeu> it = ensemble.iterator();

		while(it.hasNext()) {
			Jeu jeuCourant = it.next();
			if(jeuCourant.trouveConsole(console)) {
				gameWithConsole.add(jeuCourant);
			}

		}
		return gameWithConsole;
	}

	// Retourne les jeu realises par le fabricant passe en parametre
	public Collection<Jeu> getJeuxFabricant(String fabricant){
		Collection<Jeu> jeuxFab = new ArrayList();
		Iterator<Jeu> it = ensemble.iterator();

		while(it.hasNext()) {
			Jeu jeuCourant = it.next();
			if(jeuCourant.getFabricant().equalsIgnoreCase(fabricant)) {
				jeuxFab.add(jeuCourant);
			}
		}	
		return jeuxFab;
	}

	// Retourne les jeu portant la cote passee en parametre
	public Collection<Jeu> chercheCote(String cote){
		Collection<Jeu> jeuxCote = new ArrayList();
		Iterator<Jeu> it = ensemble.iterator();

		while(it.hasNext()) {
			Jeu jeuCourant = it.next();
			String[] toutesCotes = jeuCourant.getCote().split("");
			for(int i=0; i<toutesCotes.length; i++) {
				if(toutesCotes[i].equalsIgnoreCase(cote)) {
					jeuxCote.add(jeuCourant);
				}
			}
		}	
		return jeuxCote;
	}

	// Enregistre la banque de donnees dans un fichier dont le nom est passe en parametre
	public void saveBdd(String nomFichier){	
		boolean probleme = false;
		FileWriter fw = null;
		try
		{
			fw = new FileWriter(nomFichier);
			if(!probleme)
			{
				System.out.println("Debut de la creation du fichier " + nomFichier + "\n");
				PrintWriter aCreer = new PrintWriter(fw);
				for(Map.Entry<String, TreeSet<Jeu>> entry : jeuxVideo.entrySet()) {
					//System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
					Iterator<Jeu> it = entry.getValue().iterator();
					while(it.hasNext()) {
						Jeu jeuCourant = it.next();
						//System.out.println("jeucourant " + jeuCourant);
						aCreer.println(jeuCourant);
					}					
				}	
				aCreer.close();
			}
		} catch(java.io.FileNotFoundException erreur){
			System.out.println("Probleme pour preparer l'ecriture\n");
			probleme = true;
		} catch(IOException e) {
			System.out.println("Erreur lors de l'ecriture du fichier");
		}
		System.out.println("Fin de la creation du fichier " + nomFichier + "\n\n");
	}
}