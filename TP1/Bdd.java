import java.util.*;
import java.io.*;


public class Bdd implements TestInterface{
	

    public Bdd() {
     //a completer
    }


    public void addJeu(Jeu unJeu){
	// a completer
		Jeu.add(unJeu);

    }

	public Jeu getJeu(String titre, String fabricant){
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
	}

}