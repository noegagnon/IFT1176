import java.util.*;

public class Jeu implements Comparable<Jeu> //a completer
{
    private String fabricant;
    private String jeu;
    private String cote;
    private List<String> console;

    public Jeu(String fabricant, String jeu, String cote) 
    {
        this.fabricant = fabricant;
        this.jeu = jeu;
        this.cote = cote;
        console = new ArrayList <String>();
    }
    public Jeu(String fabricant, String jeu, String cote, List<String> console) 
    {
        this.fabricant = fabricant;
        this.jeu = jeu;
        this.cote = cote;
        this.console = console;
    }

    public boolean equals(Object o){
        Jeu autre;

        if(o instanceof Jeu){
            autre = (Jeu)o;
            return fabricant.equals(autre.fabricant)&& jeu.equals(autre.jeu);
        } else {
            return false;
        }
    }

    // tri selon fabricant, puis selon le nom du jeu
    public int compareTo(Jeu autre){
        int res = fabricant.compareTo(autre.fabricant);
        //si meme nom de fabricant
        if(res==0)
            res = jeu.compareTo(autre.jeu);
        return res;
	}

    public int hashCode(){
		return fabricant.hashCode() + jeu.hashCode();
	}
		

	public String toString()
	{
		return String.format("%-15d %-34s %-25s %-12d %-13d", fabricant, jeu, cote,console);
	}

    public String getFabricant()
    {
        return fabricant;
    }
    
    public String getJeu()
    {
        return jeu;
    }

    public String getCote()
    {
        return cote;
    }

    public String getConsole(int index)
    {
        return console.get(index);
    }
}