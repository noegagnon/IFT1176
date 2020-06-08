import java.util.*;

public class Jeu implements Comparable<Jeu> //a completer
{
    private String fabricant;
    private String titre;
    private String cote;
    private LinkedHashSet<String> consoles;

    public Jeu(String fabricant, String titre, String cote) 
    {
        this.fabricant = fabricant;
        this.titre = titre;
        this.cote = cote;
        consoles = new LinkedHashSet<String>();
    }

    public Jeu(String fabricant, String titre) {
        this.fabricant = fabricant;
        this.titre = titre;
        consoles = new LinkedHashSet<String>();
    }

    public Jeu(String fabricant, String titre, String cote, LinkedHashSet<String> consoles) 
    {
        this.fabricant = fabricant;
        this.titre = titre;
        this.cote = cote;
        this.consoles = consoles;
    }

    public boolean equals(Object o){
        Jeu autre;

        if(o instanceof Jeu){
            autre = (Jeu)o;
            return titre.equals(autre.titre)&& fabricant.equals(autre.fabricant);
        } else {
            return false;
        }
    }

    // tri selon fabricant, puis selon le nom du jeu
    public int compareTo(Jeu autre){
        int res = titre.compareTo(autre.titre);
        //si meme nom de fabricant
        if(res==0)
            res = fabricant.compareTo(autre.fabricant);
        return res;
	}

    public int hashCode(){
		return fabricant.hashCode() + titre.hashCode();
	}
		

    public String toString(){
        String res = fabricant + ";" + titre + ";" + cote + ";"; 


 
        if(consoles.size() != 0) {
            for(String s : consoles)
            {
             //   System.out.printf("res %s\n", res);
               // System.out.printf("s %s\n", s);
               // System.out.printf("consoles %s\n", consoles);
                res += s + ",";
            }

        }

        String resultat = res.substring(0,res.length()-1);
        
        return resultat;
    }

    public void addConsoles(String c){
		consoles.add(c);
	}

    public String getFabricant()
    {
        return fabricant;
    }
    
    public String getTitre()
    {
        return titre;
    }

    public String getCote()
    {
        return cote;
    }


    public Boolean trouveConsole(String uneConsole){

        for(String s : consoles) {
            if(s == uneConsole) {
                return true;
            }
        }
        return false;
    }

    public LinkedHashSet<String> getConsoles()
    {
        return consoles;
    }


/*
    public String getConsole(int index)
    {
        return consoles.get(index);
    }
    */
}