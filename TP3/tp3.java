
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class TP3 {
	//static TestInterface laBase = new Bdd();
	//Jeu unJeu;	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
       	String url="jdbc:mysql://mysql.iro.umontreal.ca/gagnonno_tp3";
		String username = UserData.login;
		String password = UserData.passwd;
		String driver = "com.mysql.jdbc.Driver";

		//Créer un objet du Driver
		Class.forName(driver);
		System.out.println("je0");
    	Connection conn = DriverManager.getConnection(url, username , password);
		System.out.println("fd");

/*
    	if(loadTable(conn)){
    		requeteSelection(conn);
    	}
*/
    	loadTable(conn, "jeux.txt");
    	addJeu(conn, "EA", "FIFA 2020", "E", "PS4,PC,Switch,Xbox");
    	chercheConsole(conn, "PC");
    	getJeuxFabricant(conn, "EA");
    	chercheCote(conn, "P");
    	saveBdd(conn, "save.txt");
    	//addBdd(conn, "jeux.txt");
		conn.close();
	}
	
	// Ajouter un jeu a la base de donnees
	public static ResultSet addJeu(Connection conn, String fabricant, String nom, String cote, String consoles) throws SQLException {
		Statement stat = conn.createStatement();
		String getString = "SELECT * FROM Jeu WHERE fabricant = "+ "'"+fabricant+"'" + " AND nom = "+ "'"+nom+"'"; //+ " AND nom = "+nom;
		System.out.println("getString" + getString);

        ResultSet rset = stat.executeQuery(getString);
        if(!rset.next()) {
         	stat.execute("INSERT INTO Jeu VALUES ('"+fabricant+"','"+nom+"','"+cote+"','"+consoles+"')");
        } else {
        	String consolesPresentes = "SELECT consoles FROM Jeu WHERE fabricant = "+ "'"+fabricant+"'" + " AND nom = "+ "'"+nom+"'";
        	ResultSet cons = stat.executeQuery(consolesPresentes);
            ArrayList<String> consBd = new ArrayList<String>();
			String[] uneConsole = consoles.split(",");
			
    		while( cons.next() ) {
    			consBd.add(cons.getString(1));
    		    System.out.println("iciiii" + cons.getString(1));
    		}

    		// donne array avec 1er indice les consoles deja presente et les consoles a ajouter ensuite
    		for(int i=0; i<uneConsole.length; i++) {
    			if(consBd.get(0).indexOf(uneConsole[i]) == -1) {
    				consBd.add(uneConsole[i]);
    			}
    		}
    		
    		// convertir arrayList en array
    		String[] consBdArr = new String[consBd.size()];    		
    		for(int i=0; i<consBd.size(); i++) {
    			consBdArr[i] = consBd.get(i);
    		}
    		
    		// convertir array en String
    		String strConsolesUpd = consBdArr[0];
    		for(int i=1; i<consBd.size() ; i++) {
    			strConsolesUpd += "," + consBdArr[i];
    		}
    		//System.out.println("laaaaaaa" + strConsolesUpd);

        	//System.out.println(consolesPresentes);
        	String ajout = "UPDATE Jeu SET consoles = " + "'"+strConsolesUpd+"' WHERE fabricant = "+ "'"+fabricant+"'" + " AND nom = "+ "'"+nom+"'";
        	System.out.println(ajout);
        	stat.executeUpdate(ajout);
        }
		return rset;
	}

	// Ajoute des jeux provenant d'un fichier a la base de donnees
	static boolean addBdd (Connection conn, String nomFichier) throws SQLException {
    	boolean ln = true;
		Statement stat = conn.createStatement();
		
		try {
			FileReader fr = null;
			boolean existeFile = true;
			boolean finFichier = false;
			try {
				fr = new FileReader(nomFichier);
				if (existeFile) {
					BufferedReader entree = new BufferedReader (fr);
					while (!finFichier) {
						String ligneLue = entree.readLine(); // null si fin de fichier
						if (ligneLue != null){
							String[] infoJeux = ligneLue.split(";");					
							String fabricant = infoJeux[0];
							String nom = infoJeux[1];
							String cote = infoJeux[2]; 
							String consoles = infoJeux[3]; 
							String[] uneConsole = consoles.split(",");
							addJeu(conn, fabricant, nom, cote, consoles);
				         	//stat.execute("INSERT INTO Jeu VALUES ('"+fabricant+"','"+nom+"','"+cote+"','"+consoles+"')");

						}
						else finFichier = true;
					}
					entree.close();
				}
			} catch (java.io.FileNotFoundException e) {
				System.out.println("Probleme d'ouvrir le fichier " + nomFichier);
				existeFile = false;
			} catch(IOException e) {
				System.out.println("Erreur lors de la lecture du fichier");
			}

		}
		catch(SQLException ex){
			System.out.println("Erreur dans modification de la table");
			ex.printStackTrace();
			ln = false;
		}
		finally{
			stat.close();
			return ln;

		}
	}
		
	// Charge la base de donnees
	static boolean loadTable(Connection conn, String nomFichier) throws SQLException {
    	String sql;
		Statement stat = conn.createStatement();
		sql = "DELETE FROM Jeu";
		stat.execute(sql);
		return(addBdd(conn, nomFichier));
	}
	
	// Retourne le(s) jeu(x) pouvant se jouer sur la console passee en parametre
	static String chercheConsole(Connection conn, String consoles) throws SQLException {
		Statement stat = conn.createStatement();
		String getConsoles = "SELECT * FROM Jeu WHERE consoles LIKE '%"+consoles+"%'";
    	ResultSet cons = stat.executeQuery(getConsoles);
	    ResultSetMetaData rsmd= cons.getMetaData();
    	int nbCol = rsmd.getColumnCount();
		String gameWithConsole = "";
		while( cons.next() ) {
	    	for(int i=1; i<nbCol;i++)
	    	{
	    		gameWithConsole += cons.getString(i) + ";";	    		
	    	}
	    	gameWithConsole += cons.getString(nbCol) + "\n";
		}
 	    System.out.println("game " + gameWithConsole);
		return gameWithConsole;
	}
	
	// Retourne le(s) jeu(x) realise(s) par le fabricant passe en parametre
	static String getJeuxFabricant(Connection conn, String fabricant) throws SQLException {
		Statement stat = conn.createStatement();
		String getConsoles = "SELECT * FROM Jeu WHERE fabricant = '"+fabricant+"'";
    	ResultSet cons = stat.executeQuery(getConsoles);
	    ResultSetMetaData rsmd= cons.getMetaData();
    	int nbCol = rsmd.getColumnCount();
		String jeuxFab = "";
		while( cons.next() ) {
		   // System.out.print(cons.getRow() + ")\t");
	    	for(int i=1; i<nbCol;i++)
	    	{
	    		//System.out.print(cons.getString(i) + " ");
	    		jeuxFab += cons.getString(i) + ";";	    		
	    	}
    		jeuxFab += cons.getString(nbCol) + "\n";	    		
		}
 	    System.out.println("game " + jeuxFab);
		return jeuxFab;
	}
	
	// Retourne le(s) jeu(x) portant la cote passee en parametre
	static String chercheCote(Connection conn, String cote) throws SQLException {
		Statement stat = conn.createStatement();
		String getConsoles = "SELECT * FROM Jeu WHERE cote LIKE '%"+cote+"%'";
    	ResultSet cons = stat.executeQuery(getConsoles);
	    ResultSetMetaData rsmd= cons.getMetaData();
    	int nbCol = rsmd.getColumnCount();
		String jeuxCote = "";
		while( cons.next() ) {
	    	for(int i=1; i<nbCol;i++)
	    	{
	    		jeuxCote += cons.getString(i) + ";";	    		
	    	}
	    	jeuxCote += cons.getString(nbCol) + "\n";
		}
 	    System.out.println("game " + jeuxCote);
		return jeuxCote;
	}
	
	// Enregistre la base de donnees dans un fichier dont le nom est passe en parametre
	public static boolean saveBdd(Connection conn, String nomFichier) throws SQLException{	
		Statement stat = conn.createStatement();

		boolean probleme = false;
		boolean sauvegarde = true;
		FileWriter fw = null;
		try
		{
			fw = new FileWriter(nomFichier);
			if(!probleme)
			{
				String bd = "SELECT * FROM Jeu";
		    	ResultSet contenuBase = stat.executeQuery(bd);
				System.out.println("\nDebut de la creation du fichier " + nomFichier + "\n");
				PrintWriter aCreer = new PrintWriter(fw);
			    ResultSetMetaData rsmd= contenuBase.getMetaData();
		    	int nbCol = rsmd.getColumnCount();
				while( contenuBase.next() ) {
				    	for(int i=1; i<nbCol;i++)
				    	{
							aCreer.print(contenuBase.getString(i) + ";");
				    	}
				    	aCreer.print(contenuBase.getString(nbCol));
				    	aCreer.print("\n");
				}
				aCreer.close();
			}
		} catch(java.io.FileNotFoundException erreur){
			System.out.println("Probleme pour preparer l'ecriture\n");
			probleme = true;
			sauvegarde = false;
		} catch(IOException e) {
			System.out.println("Erreur lors de l'ecriture du fichier");
			sauvegarde = false;
		}
		System.out.println("Fin de la creation du fichier " + nomFichier + "\n\n");
		return sauvegarde;
	}


}