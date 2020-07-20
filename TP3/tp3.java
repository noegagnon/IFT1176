
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

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
    	addJeu(conn, "EA", "FIFA 2020", "E", "PS4,XONE,PC,Switch");
    	chercheConsole(conn, "PC");
    	getJeuxFabricant(conn, "EA");
    	chercheCote(conn, "P");
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
        	// ajouter consoles dans cellule si jeu existe
        	String consolesPresentes = "SELECT consoles FROM Jeu WHERE fabricant = "+ "'"+fabricant+"'" + " AND nom = "+ "'"+nom+"'";
        	//System.out.println(stat.executeQuery(consolesPresentes));
        	ResultSet cons = stat.executeQuery(consolesPresentes);
    		while( cons.next() ) {
    		    System.out.println(cons.getString(1));
    		}
        	//System.out.println(consolesPresentes);
        	String ajout = "UPDATE Jeu SET consoles = " + "'"+consoles+"' WHERE fabricant = "+ "'"+fabricant+"'" + " AND nom = "+ "'"+nom+"'";
        	System.out.println(ajout);
        	stat.executeUpdate(ajout);

        }

		return rset;
	}

	// Ajouter des jeux provenant d'un fichier a la base de donnees
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
		
	// Charger la base de donnees
	static boolean loadTable(Connection conn, String nomFichier) throws SQLException {
    	String sql;
    	//boolean ln = true;
		Statement stat = conn.createStatement();
		sql = "DELETE FROM Jeu";
		stat.execute(sql);
		return(addBdd(conn, nomFichier));
	}
	
	static String chercheConsole(Connection conn, String consoles) throws SQLException {
		Statement stat = conn.createStatement();
		String getConsoles = "SELECT * FROM Jeu WHERE consoles LIKE '%"+consoles+"%'";
    	ResultSet cons = stat.executeQuery(getConsoles);
	    ResultSetMetaData rsmd= cons.getMetaData();
    	int nbCol = rsmd.getColumnCount();
		String gameWithConsole = "";
		while( cons.next() ) {
		   // System.out.print(cons.getRow() + ")\t");
	    	for(int i=1; i<=nbCol;i++)
	    	{
	    		//System.out.print(cons.getString(i) + " ");
	    		gameWithConsole += "\t" + cons.getString(i);	    		
	    	}
	    	
	    	gameWithConsole += ";\n";

		}
 	    System.out.println("game " + gameWithConsole);
		return gameWithConsole;
	}
	
	static String getJeuxFabricant(Connection conn, String fabricant) throws SQLException {
		Statement stat = conn.createStatement();
		String getConsoles = "SELECT * FROM Jeu WHERE fabricant = '"+fabricant+"'";
    	ResultSet cons = stat.executeQuery(getConsoles);
	    ResultSetMetaData rsmd= cons.getMetaData();
    	int nbCol = rsmd.getColumnCount();
		String jeuxFab = "";
		while( cons.next() ) {
		   // System.out.print(cons.getRow() + ")\t");
	    	for(int i=1; i<=nbCol;i++)
	    	{
	    		//System.out.print(cons.getString(i) + " ");
	    		jeuxFab += "\t" + cons.getString(i);	    		
	    	}
	    	jeuxFab += ";\n";
		}
 	    System.out.println("game " + jeuxFab);
		return jeuxFab;
	}
	
	static String chercheCote(Connection conn, String cote) throws SQLException {
		Statement stat = conn.createStatement();
		String getConsoles = "SELECT * FROM Jeu WHERE cote LIKE '%"+cote+"%'";
    	ResultSet cons = stat.executeQuery(getConsoles);
	    ResultSetMetaData rsmd= cons.getMetaData();
    	int nbCol = rsmd.getColumnCount();
		String jeuxCote = "";
		while( cons.next() ) {
	    	for(int i=1; i<=nbCol;i++)
	    	{
	    		jeuxCote += "\t" + cons.getString(i);	    		
	    	}
	    	jeuxCote += ";\n";
		}
 	    System.out.println("game " + jeuxCote);
		return jeuxCote;
	}
	

	static void requeteSelection(Connection conn) {
		// TODO Auto-generated method stub
		// mettre les requetes associees a un bouton
		// chercher console: SELECT console FRROM Jeu Where console = nomConsole
	}

}