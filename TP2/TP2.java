/*
NOM: Gagnon
PRENOM: Noemie
*/


//ajouter dialog saveBdd, shortcut fermer
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class TP2 extends JFrame{

	private JFrame frame1;

	// panel
	private JPanel currentPanel;
	private JPanel loadBddPanel = new loadPanel();
	private JPanel addBddPanel = new addPanel();
	private JPanel saveBddPanel = new savePanel();
	private JPanel addJeuPanel = new addJeuPanel();
	private JPanel getJeuPanel = new getJeuPanel();
	private JPanel chercheConsolePanel = new consolePanel();
	private JPanel getJeuFabPanel = new fabricantPanel();
	private JPanel chercheCotePanel = new cotePanel();
	private JPanel appliInfoPanel = new appliPanel();


	// Messages pour chaque panel
	private TextField tf;
	private TextField bddAAdd;
	private TextField bddALoad;
	private TextField titreAAjouter;
	private TextField fabricantAAjouter;
	private TextField coteAAjouter;
	private TextField fabricant;
	private TextField titre;
	private TextField console;
	private TextField fab;
	private TextField cote;

	// Menu
	private JMenu fichier;
	private JMenuItem loadBdd;
	private JMenuItem addBdd;
	private JMenuItem saveBdd;
	private JMenu jeu;
	private JMenuItem addJeu;
	private JMenuItem getJeu;
	private JMenuItem chercheConsole;
	private JMenuItem getJeuFabricant;
	private JMenuItem chercheCote;
	private JMenu aide;
	private JMenuItem appliBdd;
	private JMenuItem fermer;

	TestInterface laBase = new Bdd();
	Jeu unJeu;	

	public TP2(int l, int h){
		
		frame1 = new JFrame();

		// Menu
		JMenuBar mb = new JMenuBar();
		fichier = new JMenu("Fichier");
		loadBdd = new JMenuItem("Charger une banque de donnees");
		addBdd = new JMenuItem("Ajouter une banque de donnees");
		saveBdd = new JMenuItem("Sauvegarder une banque de donnees");
		jeu = new JMenu("Jeu");
		addJeu = new JMenuItem("Ajouter un jeu");
		getJeu = new JMenuItem("Trouver un jeu");
		chercheConsole = new JMenuItem("Trouver les jeux par console");
		getJeuFabricant = new JMenuItem("Trouver les jeux par fabricant");
		chercheCote = new JMenuItem("Trouver les jeux par cote");
		aide = new JMenu("Aide");
		appliBdd = new JMenuItem("AppliBdd");
		fermer = new JMenuItem("Fermer");
		
		// raccourcis
	    KeyStroke keyAddbdd = KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK);
	    addBdd.setAccelerator(keyAddbdd);
	    KeyStroke keyJeu = KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK);
	    getJeu.setAccelerator(keyJeu);
	    KeyStroke keyConsole = KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK);
	    chercheConsole.setAccelerator(keyConsole);    
	    KeyStroke keyCote = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
	    chercheCote.setAccelerator(keyCote);    
	    
	    // fermer  ????????
	    KeyStroke keyFermer = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK);
	    fermer.setAccelerator(keyFermer); 	  
	    KeyStroke keyLoad = KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK);
	    loadBdd.setAccelerator(keyLoad); 
	    KeyStroke keySave = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
	    saveBdd.setAccelerator(keySave);	    
	    KeyStroke keyAddJeu = KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK);
	    addJeu.setAccelerator(keyAddJeu); 
	    KeyStroke keyFab = KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_DOWN_MASK);
	    getJeuFabricant.setAccelerator(keyFab); 
  
	    // ajout des sous menus
		fichier.add(loadBdd);
		fichier.add(addBdd);
		fichier.add(saveBdd);
		jeu.add(addJeu);
		jeu.add(getJeu);
		jeu.add(chercheConsole);
		jeu.add(getJeuFabricant);
		jeu.add(chercheCote);
		aide.add(appliBdd);
		aide.add(fermer);

		// Ajout des elements du menu a la banque de donnees
		mb.add(fichier);
		mb.add(jeu);
		mb.add(aide);
				
		// Boutons
		/*
		JButton buttonLoad = new JButton("Load bdd");
		JButton buttonEffacer = new JButton("Effacer");
		
		buttonLoad.addActionListener(this);
		buttonEffacer.addActionListener(this);
			*/
		currentPanel = new JPanel();
		currentPanel.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));
		currentPanel.add(new JLabel("Veuillez choisir une option dans la barre de menu."));
		
		frame1.setJMenuBar(mb);
		frame1.add(currentPanel, BorderLayout.CENTER);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setTitle("Gestion d'une banque de donnees de jeux videos");
		frame1.setSize(l, h);
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
		frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		loadBdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
                swapPanel(loadBddPanel);
				
			}
		});
		
		frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		saveBdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//frame1.dispose();
				swapPanel(saveBddPanel);
			}
		});

		frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		addBdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//frame1.dispose();
				//new addBdd();
				swapPanel(addBddPanel);

			}
		});

		addJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				swapPanel(addJeuPanel);
			}
		});
		
		
		getJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				swapPanel(getJeuPanel);

			}
		});
		
		chercheConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				swapPanel(chercheConsolePanel);
			}
		});
		
		getJeuFabricant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				swapPanel(getJeuFabPanel);
			}
		});
		
		chercheCote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				swapPanel(chercheCotePanel);

			}
		});
		appliBdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
                swapPanel(appliInfoPanel);
			}
		});
	}
	
	// Chargement d'un fichier dans la banque de donnees
    public class loadPanel extends JPanel implements ActionListener {{
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		JButton buttonLoad = new JButton("Charger la bdd");
		JButton buttonEffacer = new JButton("Effacer");
		buttonLoad.addActionListener(this);
		buttonEffacer.addActionListener(this);
		JLabel demandeFichier = new JLabel("Veuillez entrez le nom du fichier a charger");

		//bddALoad = new TextField(50);
		this.add(demandeFichier);
		this.add(bddALoad = new TextField(50));
		this.add(buttonLoad);
		this.add(buttonEffacer);
    }

	@Override
	public void actionPerformed(ActionEvent e) {		
		String text = bddALoad.getText();
		String action = e.getActionCommand();
		System.out.println(text);
		System.out.println("action" + action);
		if(action == "Charger la bdd") {
	    	laBase.loadBdd(text);
	    	if(laBase.loadBdd(text)) {
		    	JOptionPane.showMessageDialog(frame1,
		    		    "La banque de donnees a ete creee a partir du fichier " + text,
		    		    "Creation d'une bdd",
		    		    JOptionPane.PLAIN_MESSAGE);
	    	} else {
		    	JOptionPane.showMessageDialog(frame1,
		    			"La banque de donnees a partir du fichier " + text + " n'a pas pu etre creee",
		    		    "Creation d'une bdd",
		    		    JOptionPane.ERROR_MESSAGE);		
	    	}

		} else if(action == "Effacer") {
			bddALoad.setText("");
		} 	
	}}
    
    // Sauvegarde de la banque de donnees dans un fichier
    public class savePanel extends JPanel implements ActionListener {{
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		JButton buttonSave = new JButton("Sauvegarder la bdd");
		JButton buttonEffacer = new JButton("Effacer");
		buttonSave.addActionListener(this);
		buttonEffacer.addActionListener(this);
		JLabel demandeFichier = new JLabel("Veuillez entrez le nom du fichier dans lequel sauvegarder la banque de donnees");

		
		tf = new TextField(50);
		this.add(demandeFichier);
		this.add(tf);
		this.add(buttonSave);
		this.add(buttonEffacer);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String strToSave = tf.getText();
		String action = e.getActionCommand();
		System.out.println(strToSave);
		System.out.println("action" + action);
		if(action == "Sauvegarder la bdd") {
	    	laBase.saveBdd(strToSave);
	    	if(laBase.saveBdd(strToSave)) {
		    	JOptionPane.showMessageDialog(frame1,
		    		    "La banque de donnees a ete sauvegardee sur le fichier " + strToSave,
		    		    "Sauvegarde d'une bdd",
		    		    JOptionPane.PLAIN_MESSAGE);
	    	} else {
		    	JOptionPane.showMessageDialog(frame1,
		    			"La banque de donnees a partir du fichier n'a pas pu etre sauvegardee sur le fichier " + strToSave ,
		    		    "Sauvegarde d'une bdd",
		    		    JOptionPane.ERROR_MESSAGE);		
	    	}
		} else if(action == "Effacer") {
			tf.setText("");
		} 	
	}}
    
    // Ajout d'un fichier a la banque de donnees
    public class addPanel extends JPanel implements ActionListener {{
        //this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		JButton buttonAdd = new JButton("Ajouter la bdd");
		JButton buttonEffacer = new JButton("Effacer");
		buttonAdd.addActionListener(this);
		buttonEffacer.addActionListener(this);
		
		JLabel demandeFichier = new JLabel("Veuillez entrez le nom du fichier a ajouter a la banque de donnees");
		bddAAdd = new TextField(50);
		this.add(demandeFichier);
		this.add(bddAAdd);
		this.add(buttonAdd);
		this.add(buttonEffacer);
    }

	@Override
	public void actionPerformed(ActionEvent e) {		
		String strFichier = bddAAdd.getText();
		String action = e.getActionCommand();
		System.out.println(strFichier);
		System.out.println("action" + action);
		if(action == "Ajouter la bdd") {
	    	laBase.addBdd(strFichier);
	    	if(laBase.addBdd(strFichier)) {
		    	JOptionPane.showMessageDialog(frame1,
		    		    "Le fichier " + strFichier + " a ete ajoute a la banque de donnees ",
		    		    "Ajout d'une bdd",
		    		    JOptionPane.PLAIN_MESSAGE);
	    	} else {
		    	JOptionPane.showMessageDialog(frame1,
		    		    "Le fichier " + strFichier + " n'a pas pu etre ajoute a la banque de donnees",
		    		    "Ajout d'une bdd",
		    		    JOptionPane.ERROR_MESSAGE);		
	    	}
		} else if(action == "Effacer") {
			bddAAdd.setText("");
		} 	
	}}

    
    // Ajout d'un jeu a la banque de donnees
    public class addJeuPanel extends JPanel implements ActionListener {{
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		JButton buttonAddJeu = new JButton("Ajouter jeu");
		JButton buttonEffacer = new JButton("Effacer");
		buttonAddJeu.addActionListener(this);
		buttonEffacer.addActionListener(this);
		JLabel ajouterTitre = new JLabel("Veuillez entrez le titre du jeu a ajouter");
		JLabel ajouterFabricant = new JLabel("Veuillez entrez le fabricant du jeu a ajouter");
		JLabel ajouterCote = new JLabel("Veuillez entrez la cote du jeu a ajouter");

		titreAAjouter = new TextField(50);
		fabricantAAjouter = new TextField(50);
		coteAAjouter = new TextField(50);
		
		this.add(ajouterTitre);
		this.add(titreAAjouter);
		this.add(ajouterFabricant);
		this.add(fabricantAAjouter);
		this.add(ajouterCote);
		this.add(coteAAjouter);
		this.add(buttonAddJeu);
		this.add(buttonEffacer);
    }

	@Override
	public void actionPerformed(ActionEvent e) {		
		String strTitreAAjouter = titreAAjouter.getText();
		String strFabAAjouter = fabricantAAjouter.getText();
		String strCoteAAjouter = coteAAjouter.getText();	
		
		String action = e.getActionCommand();
		System.out.println(titreAAjouter);
		System.out.println(fabricantAAjouter);

		System.out.println("action" + action);
		if(action == "Ajouter jeu") {
	    	unJeu = new Jeu(strFabAAjouter, strTitreAAjouter, strCoteAAjouter);
	    	laBase.addJeu(unJeu);
	    	JOptionPane.showMessageDialog(frame1,
	    		    "Le jeu " + strTitreAAjouter + " du fabricant " + strFabAAjouter + " ayant une cote " + strCoteAAjouter + " a bien ete ajoute a la banque de donnees",
	    		    "Ajout d'un jeu",
	    		    JOptionPane.PLAIN_MESSAGE);
		} else if(action == "Effacer") {
			titreAAjouter.setText("");
			fabricantAAjouter.setText("");
			coteAAjouter.setText("");
		} 	
	}}
    
    // Affichage d'un jeu de la banque de donnees
    public class getJeuPanel extends JPanel implements ActionListener {{
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));
		JButton buttonGetJeu = new JButton("Trouver le jeu");
		JButton buttonEffacer = new JButton("Effacer");
		buttonGetJeu.addActionListener(this);
		buttonEffacer.addActionListener(this);
		JLabel titreAEcrire = new JLabel("Veuillez entrez le titre du jeu recherche");
		JLabel fabricantAEcrire = new JLabel("Veuillez entrez le fabricant du jeu recherche");
		JLabel reponse = new JLabel();
	
		titre = new TextField(50);
		fabricant = new TextField(50);
		this.add(titreAEcrire);
		this.add(titre);
		this.add(fabricantAEcrire);
		this.add(fabricant);
		this.add(reponse);
		this.add(buttonGetJeu);
		this.add(buttonEffacer);
    }

	@Override
	public void actionPerformed(ActionEvent e) {		
		String strTitre = titre.getText();
		String strFabricant = fabricant.getText();	
		String action = e.getActionCommand();
		System.out.println(titre);
		System.out.println(fabricant);

		System.out.println("action" + action);
		if(action == "Trouver le jeu") {
	    	laBase.getJeu(strTitre, strFabricant);
	    	if(laBase.getJeu(strTitre, strFabricant) != null) {
		    	JOptionPane.showMessageDialog(frame1,
		    			"Voici le jeu recherché : \n" + laBase.getJeu(strTitre, strFabricant),
		    		    "Jeu recherché",
		    		    JOptionPane.PLAIN_MESSAGE);	    		
	    	} else {
		    	JOptionPane.showMessageDialog(frame1,
		    			"Le jeu " + strTitre + "du fabricant " + strFabricant + " est introuvable",
		    		    "Jeu recherché",
		    		    JOptionPane.WARNING_MESSAGE);
	    	}

		} else if(action == "Effacer") {
			titre.setText("");
			fabricant.setText("");
		} 	
	}}
    
    // Affichage des jeux faisant partie de la banque de donnees se jouant sur une console voulue
    public class consolePanel extends JPanel implements ActionListener {{
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		JButton buttonConsole = new JButton("Chercher les jeux pour cette console");
		JButton buttonEffacer = new JButton("Effacer");
		buttonConsole.addActionListener(this);
		buttonEffacer.addActionListener(this);
		JLabel consoleAEcrire = new JLabel("Veuillez entrez une console");

		console = new TextField(50);
		this.add(consoleAEcrire);
		this.add(console);
		this.add(buttonConsole);
		this.add(buttonEffacer);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String strConsole = console.getText();
		String action = e.getActionCommand();
		System.out.println(strConsole);
		System.out.println("action" + action);
		if(action == "Chercher les jeux pour cette console") {
	    	laBase.chercheConsole(strConsole);
	    	if(laBase.chercheConsole(strConsole).size() != 0) {
		    	JOptionPane.showMessageDialog(frame1,
		    			"Voici le(s) jeu(x) se jouant sur la console " + strConsole + ":\n" + laBase.chercheConsole(strConsole),
		    		    "Jeu(x) sur une console",
		    		    JOptionPane.PLAIN_MESSAGE);	    		
	    	} else {
		    	JOptionPane.showMessageDialog(frame1,
		    			"Aucun jeu se jouant sur la console " + strConsole + " n'a ete trouvé",
		    		    "Jeu(x) sur une console",
		    		    JOptionPane.WARNING_MESSAGE);
	    	}
		} else if(action == "Effacer") {
			console.setText("");
		} 	
	}}
    
    // Affichage des jeux faisant partie de la banque de donnees provenant d'un fabricant voulu
    public class fabricantPanel extends JPanel implements ActionListener {{
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		JButton buttonFabricant = new JButton("Chercher les jeux de ce fabricant");
		JButton buttonEffacer = new JButton("Effacer");
		buttonFabricant.addActionListener(this);
		buttonEffacer.addActionListener(this);
		JLabel fabricantAEcrire = new JLabel("Veuillez entrez un fabricant");

		fab = new TextField(50);
		this.add(fabricantAEcrire);
		this.add(fab);
		this.add(buttonFabricant);
		this.add(buttonEffacer);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String strFabricant = fab.getText();
		String action = e.getActionCommand();
		System.out.println(strFabricant);
		System.out.println("action" + action);
		if(action == "Chercher les jeux de ce fabricant") {
	    	laBase.getJeuxFabricant(strFabricant);
	    	if(laBase.getJeuxFabricant(strFabricant).size() != 0) {
		    	JOptionPane.showMessageDialog(frame1,
		    			"Voici le(s) jeu(x) du fabricant " + strFabricant + ":\n" + laBase.getJeuxFabricant(strFabricant),
		    		    "Jeu(x) sur une console",
		    		    JOptionPane.PLAIN_MESSAGE);	    		
	    	} else {
		    	JOptionPane.showMessageDialog(frame1,
		    			"Aucun jeu du fabricant " + strFabricant + " n'a ete trouvé",
		    		    "Jeu(x) sur une console",
		    		    JOptionPane.WARNING_MESSAGE);
	    	}
		} else if(action == "Effacer") {
			fab.setText("");
		} 	
	}}
    
    // Affichage des jeux faisant partie de la banque de donnees ayant une cote voulue
    public class cotePanel extends JPanel implements ActionListener {{
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));
		JButton buttonCote = new JButton("Chercher les jeux avec cette cote");
		JButton buttonEffacer = new JButton("Effacer");
		buttonCote.addActionListener(this);
		buttonEffacer.addActionListener(this);
		JLabel coteAEcrire = new JLabel("Veuillez entrez une cote");

		cote = new TextField(50);
		this.add(coteAEcrire);
		this.add(cote);
		this.add(buttonCote);
		this.add(buttonEffacer);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String strCote = cote.getText();
		String action = e.getActionCommand();
		System.out.println(strCote);
		System.out.println("action" + action);
		if(action == "Chercher les jeux avec cette cote") {
	    	laBase.chercheCote(strCote);	    	
	    	if(laBase.chercheCote(strCote).size() != 0) {
		    	JOptionPane.showMessageDialog(frame1,
		    			"Voici le(s) jeu(x) avec la cote " + strCote + ":\n" + laBase.chercheCote(strCote),
		    		    "Jeu(x) sur une console",
		    		    JOptionPane.PLAIN_MESSAGE);	    		
	    	} else {
		    	JOptionPane.showMessageDialog(frame1,
		    			"Aucun jeu ayant la cote " + strCote + " n'a ete trouvé",
		    		    "Jeu(x) avec une certaine cote",
		    		    JOptionPane.WARNING_MESSAGE);
	    	}
		} else if (action == "Effacer") {
			cote.setText("");
		} 	
	}}
    
    // Information sur l'application
    public class appliPanel extends JPanel{{
        this.setLayout(new GridLayout(4,1));
        this.add(new JLabel("Version : 1.0", JLabel.CENTER));
        this.add(new JLabel("Conceptrice: Noemie Gagnon", JLabel.CENTER));
        this.add(new JLabel("Copyright: blabla", JLabel.CENTER));
        this.setBorder(BorderFactory.createEmptyBorder(150, 150, 150, 150));
    }}
    
    protected void swapPanel(JPanel newPanel) {
        SwingUtilities.invokeLater(new Runnable() {   	
            @Override
            public void run() {
                frame1.remove(currentPanel);
                frame1.add(newPanel);
                currentPanel = newPanel;
                frame1.invalidate();
                frame1.revalidate();
            }
        });
    }
    
    // Afficher un jeu 
	public void afficherJeu(TestInterface b, String fab, String titre ) 			
	throws IOException 
	{
		Jeu aAfficher = b.getJeu(titre, fab);
		if( aAfficher != null) {
	    	JOptionPane.showMessageDialog(frame1,
	    			"Jeu a affichier: \n" + aAfficher,
	    		    "Affichage d'un jeu",
	    		    JOptionPane.PLAIN_MESSAGE); 
		} else {
	    	JOptionPane.showMessageDialog(frame1,
	    			"Le jeu " + titre + " du fabricant " + fab + "n'est pas dans la banque de donnees",
	    		    "Affichage d'un jeu",
	    		    JOptionPane.PLAIN_MESSAGE); 
			
		}
	}
	
	public static void main (String[] args) {
		new	TP2(600,500);
	}
	
	/*
	//pas besoin
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		String text = tf.getText();
		String action = e.getActionCommand();
		System.out.println(text);
		System.out.println(action);
		

	}
*/
}
