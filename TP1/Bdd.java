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
						j.addConsole(uneConsole[i]);
						jeuExiste = true;
					}	
				} 
			} 
			if(!jeuExiste) {
				System.out.println("Le jeu does not exist adding to ensemble" + unJeu.getTitre());
				ensemble.add(unJeu);
			} 						
		} else {
			ensemble = new TreeSet<Jeu>();
			ensemble.add(unJeu);
			jeuxVideo.put(fabricant, ensemble);
		}	
		System.out.println("jeuxVideo " + jeuxVideo);
    }

	// Retourne le jeu passe en parametre
	public Jeu getJeu(String titre, String fabricant){
		Jeu aTrouver = new Jeu(fabricant, titre);


		ensemble = jeuxVideo.get(fabricant);
		if (ensemble != null) {
			Iterator<Jeu> it = ensemble.iterator();
			while(it.hasNext()) {
				Jeu courant = it.next();
	
				//System.out.println(courant);
			
				if (aTrouver.equals(courant)) {
					return courant;
				}
			}
	
		}

		System.out.println("Le jeu " + aTrouver.getTitre() + " pas trouve");
		return null;
	}

	// Ajoute les donnees du fichier passee en parametre a la banque de donnees
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
		
		//Map<String, TreeSet<Jeu>> jeuxVideo = new LinkedHashMap<>();
		//TreeSet<Jeu> ensemble;
		addBdd(nomFile);
		// a completer
		
	}

	// Retourne le(s) jeu(x) pouvant se jouer sur la console passee en parametre
	public ArrayList<Jeu> chercheConsole(String console){
		ArrayList<Jeu> gameWithConsole = new ArrayList<Jeu>();
		for(Map.Entry<String, TreeSet<Jeu>> entry : jeuxVideo.entrySet()) {
			//System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
			Iterator<Jeu> it = entry.getValue().iterator();
			while(it.hasNext()) {
				Jeu jeuCourant = it.next();
				//System.out.println(jeuCourant);
				// System.out.println(jeuCourant.trouveConsole(console));
				// SSystem.out.println(jeuCourant.getConsoles());
				if(jeuCourant.trouveConsole(console)) {
					//System.out.println(jeuCourant);
					gameWithConsole.add(jeuCourant);
				}
			}				
		}
		//System.out.println(gameWithConsole);
		return gameWithConsole;

	}

	// Retourne le(s) jeu(x) realise(s) par le fabricant passe en parametre
	public Collection<Jeu> getJeuxFabricant(String fabricant){
		Collection<Jeu> jeuxFab = new ArrayList<Jeu>();

		for(Map.Entry<String, TreeSet<Jeu>> entry : jeuxVideo.entrySet()) {
			//System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
			Iterator<Jeu> it = entry.getValue().iterator();
			while(it.hasNext()) {
				Jeu jeuCourant = it.next();
				if(jeuCourant.getFabricant().equalsIgnoreCase(fabricant)) {
					jeuxFab.add(jeuCourant);
				}
			}				
		}
		return jeuxFab;
	}

	// Affiche le(s) jeu(x) portant la cote passee en parametre
	public void chercheCote(String cote){
		//Collection<Jeu> jeuxCote = new ArrayList();

		System.out.println("Jeu(x) ayant la cote " + cote + ":");
		for(Map.Entry<String, TreeSet<Jeu>> entry : jeuxVideo.entrySet()) {
			//System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
			Iterator<Jeu> it = entry.getValue().iterator();
			while(it.hasNext()) {
				Jeu jeuCourant = it.next();
				//System.out.println("jeucourant " + jeuCourant);
				String[] toutesCotes = jeuCourant.getCote().split("");
				for(int i=0; i<toutesCotes.length; i++) {
					//System.out.println(toutesCotes[i]);
					if(toutesCotes[i].equalsIgnoreCase(cote)) {
						System.out.println(jeuCourant);
					}
				}
			}				
		}			
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
				System.out.println("\nDebut de la creation du fichier " + nomFichier + "\n");
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