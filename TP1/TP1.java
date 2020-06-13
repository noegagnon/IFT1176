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


    public static void main(String[] args) throws IOException
 	{
		 /*
		LinkedHashSet<String> linkedset =  
                           new LinkedHashSet<String>();   
  
		TestInterface laBase = new Bdd();


        // Adding element to LinkedHashSet   
        linkedset.add("Nintendo");   
        linkedset.add("Xbox");   

		Jeu jeu1 = new Jeu("Ubisoft", "Sims", "T", linkedset);
//		System.out.println(jeu1);

		Jeu jeu2 = new Jeu("Ubisoft", "Beat Saber", "AT", linkedset);
	//	System.out.println("jeu2 " + jeu2);
		
		//Jeu jeu3 = new Jeu("Ubisoft", "Sims");
		//laBase.addJeu(jeu3);


    	Jeu unJeu = new Jeu("EA", "The Sims 4", "R");
		//System.out.println("cac " + unJeu);
    	//laBase.addJeu(unJeu);
		//laBase.addJeu(jeu1);
		laBase.addJeu(unJeu);
		//System.out.println(laBase);
		laBase.addJeu(jeu1);
		laBase.addJeu(jeu2);
		
		System.out.println("getJeu Sims ubisoft "+ laBase.getJeu("Sims", "Ubisoft"));

		System.out.println("getJeu sims4 "+ laBase.getJeu("The Sims 4", "EA"));

		System.out.println("getJeu sims4 "+ laBase.getJeu("Beat Saber", "Ubisoft"));
		
		//laBase.getJeu("The Sims 4", "EA");

		// afficherJeu
		System.out.println("\nLes infos sur les Sims 5 : ");
    	afficherJeu(laBase, "EA", "The Sims 5");
		System.out.println("\nLes infos sur Sims : ");
    	afficherJeu(laBase, "Ubisoft", "Sims");

		// chercheConsole
		System.out.println("\n\nLes jeux disponibles sur la SWITCH sont :");
		List<Jeu> lstSwitch = laBase.chercheConsole("Switch");
		System.out.println(lstSwitch);

		System.out.println("\n\nLes jeux disponibles sur la Xbox sont :");
		List<Jeu> lstXbox = laBase.chercheConsole("Xbox");
		System.out.println(lstXbox);


		//get jeux d'un fabricant
		Collection<Jeu> colFab = laBase.getJeuxFabricant("UBISOFT");
		System.out.println("\n\nLes jeux de UBISOFT");
		if(colFab==null)
			System.out.println("Aucun jeu par UBISFOT");
		else{
			for(Jeu j : colFab)
			System.out.println(j);
		}

		laBase.chercheCote("T");


		laBase.loadBdd("jeux.txt");
		laBase.loadBdd("jeuxComplement.txt");
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
    	//laBase.loadBdd("jeuxComplement.txt");

    	System.out.println("\n\nApr�s le load, les infos sur les Sims 5 : ");
    	afficherJeu(laBase, "EA", "The Sims 5");

    	System.out.println("Les infos sur NHL 20 : ");
    	afficherJeu(laBase, "EA", "NHL 2020");

    	System.out.println("Les infos sur Vampyr : ");
    	afficherJeu(laBase, "FOCUS", "Vampyr");


		laBase.addBdd("jeuxComplement.txt");
		//laBase.addBdd("jeux.txt");

		System.out.println("\n\nApres le addBdd, les infos sur les Sims 5 : ");
    	afficherJeu(laBase, "EA", "The Sims 5");

    	System.out.println("Les infos sur NHL 20 : ");
    	afficherJeu(laBase, "EA", "NHL 2020");

    	System.out.println("Les infos sur Vampyr : ");
		afficherJeu(laBase, "FOCUS", "Vampyr");
		laBase.chercheCote("T");


		System.out.println("\n\nLes jeux disponibles sur la SWITCH sont :");
		List<Jeu> lstSwitch = laBase.chercheConsole("EA");
		System.out.println("liste " + lstSwitch);

		for(Jeu j : lstSwitch)
			System.out.println(j);

/*
		Collection<Jeu> colFab = laBase.getJeuxFabricant("UBISOFT");
		System.out.println(colFab);
		System.out.println("\n\nLes jeux de UBISOFT");
		if(colFab==null)
			System.out.println("Aucun jeu par UBISFOT");
		else{
			for(Jeu j : colFab)
			System.out.println(j);
		}

		laBase.saveBdd("sauvegarde.txt");
    */
	}
	
}
