import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class TP3 {
	static TestInterface laBase = new Bdd();
	Jeu unJeu;	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
       	String url="jdbc:mysql://mysql.iro.umontreal.ca/gagnonno_tp3";
		String username = UserData.login;
		String password = UserData.passwd;
		String driver = "com.mysql.jdbc.Driver";

		//Créer un objet du Driver
		Class.forName(driver);

    	Connection conn = DriverManager.getConnection(url, username , password);

    	if(loadTable(conn)){
    		requeteSelection(conn);
    	}

		conn.close();
	}
	
	static boolean addJeu(Connection conn) {
		
		return false;
		
	}
	

	static boolean loadTable(Connection conn) throws SQLException {
    	String sql;
    	boolean ln = true;
		Statement stat = conn.createStatement();
		
		try {
			sql = "DELETE FROM Jeu";
			stat.execute(sql);
			FileReader fr = null;
			boolean existeFile = true;
			boolean finFichier = false;
			try {
				fr = new FileReader("jeux.txt");
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
				         	stat.execute("INSERT INTO Jeu VALUES ('"+fabricant+"','"+nom+"','"+cote+"','"+consoles+"')");

						}
						else finFichier = true;
					}
					entree.close();
				}
			} catch (java.io.FileNotFoundException e) {
				System.out.println("Probleme d'ouvrir le fichier " + "jeux.txt");
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

	static void requeteSelection(Connection conn) {
		// TODO Auto-generated method stub
		// mettre les requetes associees a un bouton
		// chercher console: SELECT console FRROM Jeu Where console = nomConsole
	}

}
