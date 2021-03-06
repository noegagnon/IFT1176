/*
NOM: Gagnon
PRENOM: Noemie
*/

import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Bdd {
	private static Connection conn;
	static Jeu unJeu;
	
	public static void init() throws SQLException, ClassNotFoundException {
		
       	String url="jdbc:mysql://mysql.iro.umontreal.ca/gagnonno_tp3";
		String username = UserData.login;
		String password = UserData.passwd;
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		conn = DriverManager.getConnection(url, username , password);
	}
	
	// Ajouter un jeu a la base de donnees
	public static void addJeu(Jeu unJeu) throws SQLException {
		Statement stat = conn.createStatement();
		String getString = "SELECT * FROM Jeu WHERE fabricant = "+ "'"+unJeu.getFabricant()+"'" + " AND nom = "+ "'"+unJeu.getTitre()+"'";	
        ResultSet rset = stat.executeQuery(getString);	

    	// Si le jeu n'existe pas dans la bd
        if(!rset.next()) {
            String consoleStr = "";            
    		Iterator<String> iterator = unJeu.getConsoles().iterator();    		 
    		while( iterator.hasNext() ){
    		    consoleStr += iterator.next();
    		    if(iterator.hasNext()) consoleStr+=",";
    		}
         	String inserer = "INSERT INTO Jeu VALUES ('"+unJeu.getFabricant()+"','"+unJeu.getTitre()+"','"+unJeu.getCote()+"','"+consoleStr+"')";
         	stat.execute(inserer);	
        } else {
	    	String consolesPresentes = "SELECT consoles FROM Jeu WHERE fabricant = "+ "'"+unJeu.getFabricant()+"'" + " AND nom = "+ "'"+unJeu.getTitre()+"'";
	    	ResultSet cons = stat.executeQuery(consolesPresentes);
			LinkedHashSet<String> console = unJeu.getConsoles();
			if(cons.next()) {
				String[] dbConsStr = cons.getString(1).split(",");
				for(int i=0; i<dbConsStr.length; i++) {
					console.add(dbConsStr[i]);
				}
			}			
			String strConsoleComplet = "";
    		Iterator<String> iterator = console.iterator();   		 
    		while( iterator.hasNext() ){
    			strConsoleComplet += iterator.next();
    		    if(iterator.hasNext()) strConsoleComplet+=",";
    		}	
	    	String ajout = "UPDATE Jeu SET consoles = " + "'"+strConsoleComplet+"' WHERE fabricant = "+ "'"+unJeu.getFabricant()+"'" + " AND nom = "+ "'"+unJeu.getTitre()+"'";
	    	stat.executeUpdate(ajout);
        }
	}
	
	// Retourne le jeu passe en parametre
	public static String getJeu(String nom, String fabricant) throws SQLException {
		Statement stat = conn.createStatement();
		String getConsoles = "SELECT * FROM Jeu WHERE nom = '" + nom + "' AND fabricant = '" + fabricant+"'";
    	ResultSet cons = stat.executeQuery(getConsoles);
    	ResultSetMetaData rsmd= cons.getMetaData();
    	int nbCol = rsmd.getColumnCount();
		String jeuATrouver = "";
		while( cons.next() ) {
	    	for(int i=1; i<nbCol;i++)
	    	{
	    		jeuATrouver += cons.getString(i) + ";";	    		
	    	}
	    	jeuATrouver += cons.getString(nbCol) + "\n";
		}
		return jeuATrouver;
	}

	// Ajoute des jeux provenant d'un fichier a la base de donnees
	@SuppressWarnings("finally")
	static boolean addBdd (String nomFichier) throws SQLException {
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
							String[] consArr = consoles.split(",");
							LinkedHashSet<String> console = new LinkedHashSet<String>(Arrays.asList(consArr));
							unJeu = new Jeu(fabricant, nom, cote, console);
							addJeu(unJeu);
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
	public static boolean loadTable(String nomFichier) throws SQLException {
    	String sql;
		Statement stat = conn.createStatement();
		sql = "DELETE FROM Jeu";
		stat.execute(sql);
		return(addBdd(nomFichier));
	}
	
	// Retourne le(s) jeu(x) pouvant se jouer sur la console passee en parametre
	static String chercheConsole(String consoles) throws SQLException {
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
		return gameWithConsole;
	}
	
	// Retourne le(s) jeu(x) realise(s) par le fabricant passe en parametre
	static String getJeuxFabricant(String fabricant) throws SQLException {
		Statement stat = conn.createStatement();
		String getConsoles = "SELECT * FROM Jeu WHERE fabricant = '"+fabricant+"'";
    	ResultSet cons = stat.executeQuery(getConsoles);
	    ResultSetMetaData rsmd= cons.getMetaData();
    	int nbCol = rsmd.getColumnCount();
		String jeuxFab = "";
		while( cons.next() ) {
	    	for(int i=1; i<nbCol;i++)
	    	{
	    		jeuxFab += cons.getString(i) + ";";	    		
	    	}
    		jeuxFab += cons.getString(nbCol) + "\n";	    		
		}
		return jeuxFab;
	}
	
	// Retourne le(s) jeu(x) portant la cote passee en parametre
	public static String chercheCote(String cote) throws SQLException {
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
		return jeuxCote;
	}
	
	// Enregistre la base de donnees dans un fichier dont le nom est passe en parametre
	public static boolean saveBdd(String nomFichier) throws SQLException{	
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