/*
NOM: Gagnon
PRENOM: Noemie
*/

import java.util.*;
/**
	Pour la correction du devoir, nous allons nous batir
	un programme de test qui va contenir la methode main
	a executer.  L'interface sert a garantir la syntaxe
	commune pour tous les travaux.

 */


public interface TestInterface {

    public void addJeu(Jeu unJeu);

	public Jeu getJeu(String titre, String fabricant);

	public boolean addBdd(String nomFile);

	public boolean loadBdd(String nomFile);

	public ArrayList<Jeu> chercheConsole(String console);

	public Collection<Jeu> getJeuxFabricant(String fabricant);

	public Collection<Jeu> chercheCote(String cote);

	public boolean saveBdd(String nomFichier);

}