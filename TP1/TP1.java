/*
NOM: Gagnon
PRENOM: Noemie
*/

import java.util.*;
import java.io.*;

public class TP1 {

	// Pour faire afficher un jeu.  a modifier pour le tp2
	public static void afficherJeu(TestInterface b, String fab, String titre ) 			
	throws IOException 
	{
		Jeu aAfficher = b.getJeu(titre, fab);
		if( aAfficher != null)
			System.out.println("aAfficher " + aAfficher);
		else
			System.out.println(titre + " n'est pas dans la banque de donnees");
	}


    public static void main(String[] args) 	 
 	{
		LinkedHashSet<String> linkedset =  
                           new LinkedHashSet<String>();   
  
		//TestInterface laBase = new Bdd();

/*
        // Adding element to LinkedHashSet   
        linkedset.add("Nintendo");   
        linkedset.add("Xbox");   

		Jeu jeu1 = new Jeu("Ubisoft", "Sims", "T", linkedset);
		//System.out.println(jeu1);

		Jeu jeu2 = new Jeu("Ubisoft", "Beat Saber", "AT", linkedset);
*/
		/*

    	Jeu unJeu = new Jeu("EA", "The Sims 4", "R", new LinkedHashSet<String>(Arrays.asList("PS4,XONE,PC".split(","))));
		System.out.println("cac " + unJeu);
    	//laBase.addJeu(unJeu);
		//laBase.addJeu(jeu1);
		laBase.addJeu(unJeu);
		
		//laBase.addJeu(jeu2);

		//laBase.getJeu("Sims", "Ubisoft");

		laBase.getJeu("EA", "The Sims 5");

		System.out.println("\nLes infos sur les Sims 5 : ");
    	afficherJeu(laBase, "EA", "The Sims 5");
		System.out.println("\nLes infos sur les Sims 5 : ");
    	afficherJeu(laBase, "Ubisoft", "Sims");


		System.out.println("\n\nLes jeux disponibles sur la SWITCH sont :");
		List<Jeu> lstSwitch = laBase.chercheConsole("Xbox");
		System.out.println(lstSwitch);

		//List<Jeu> lstSwitch = laBase.chercheConsole("Xbox");


	// etait pas la...

		Collection<Jeu> colFab = laBase.getJeuxFabricant("UBISOFT");
		System.out.println("\n\nLes jeux de UBISOFT");
		if(colFab==null)
			System.out.println("Aucun jeu par UBISFOT");
		else{
			for(Jeu j : colFab)
			System.out.println(j);
		}

		Collection<Jeu> colCote = laBase.chercheCote("T");
		System.out.println("\n\nLes jeux de cote T");
		System.out.println(colCote);

		Collection<Jeu> colCote2 = laBase.chercheCote("A");
		System.out.println("\n\nLes jeux de cote A");
		System.out.println(colCote2);


		laBase.loadBdd("jeux.txt");
		laBase.loadBdd("jeuxComplement.txt");
*/
		 /*


		FileReader fr = null;
		boolean existeFichier = true;
		boolean finFichier = false;
		String nomFichier = "jeux.txt";


		try {
			fr = new FileReader(nomFichier);
		}

		catch(java.io.FileNotFoundException erreur) {
			System.out.println("Probleme pour ouvrir le fichier " + nomFichier);
			existeFichier = false;
		}

		if(existeFichier) {
			BufferedReader entree = new BufferedReader(fr);
			while(!finFichier) {
				String uneLigne = entree.readLine();
				if(uneLigne == null)  {
					finFichier = true;
				} else {
					String information[] = uneLigne.split(";");
					String fabricant = information[0];
					String jeu = information[1];
					String cote = information[2];
					List<String> consoles = Arrays.asList(information[3].split(","));
					System.out.println(consoles);		
				}
			}
			
			entree.close();
		}
		*/

    	TestInterface laBase = new Bdd();
    	Jeu unJeu;

    	unJeu = new Jeu("EA", "The Sims 5", "M");
    	laBase.addJeu(unJeu);

    	unJeu.addConsole("PS4");
    	unJeu.addConsole("XONE");

    	System.out.println("Les infos sur les Sims 5 : ");
    	afficherJeu(laBase, "EA", "The Sims 5");

    	laBase.loadBdd("jeux.txt");

    	System.out.println("\n\nApr�s le load, les infos sur les Sims 5 : ");
    	afficherJeu(laBase, "EA", "The Sims 5");

    	System.out.println("Les infos sur NHL 20 : ");
    	afficherJeu(laBase, "EA", "NHL 2020");

    	System.out.println("Les infos sur Vampyr : ");
    	afficherJeu(laBase, "FOCUS", "Vampyr");


		laBase.addBdd("jeuxComplement.txt");

		System.out.println("\n\nApres le addBdd, les infos sur les Sims 5 : ");
    	afficherJeu(laBase, "EA", "The Sims 5");

    	System.out.println("Les infos sur NHL 20 : ");
    	afficherJeu(laBase, "EA", "NHL 2020");

    	System.out.println("Les infos sur Vampyr : ");
    	afficherJeu(laBase, "FOCUS", "Vampyr");

		System.out.println("\n\nLes jeux disponibles sur la SWITCH sont :");
		List<Jeu> lstSwitch = laBase.chercheConsole("SWITCH");

		for(Jeu j : lstSwitch)
			System.out.println(j);

		Collection<Jeu> colFab = laBase.getJeuxFabricant("UBISOFT");
		System.out.println("\n\nLes jeux de UBISOFT");
		if(colFab==null)
			System.out.println("Aucun jeu par UBISFOT");
		else{
			for(Jeu j : colFab)
			System.out.println(j);
		}

		laBase.saveBdd("sauvegarde.txt");
    
	}
	
}
