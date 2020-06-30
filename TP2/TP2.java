/*
NOM: Gagnon
PRENOM: Noemie
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class TP2 extends JFrame implements ActionListener{

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

	// menu
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

	
	TestInterface laBase = new Bdd();
	Jeu unJeu;	

	public TP2(int l, int h){
		
		//menu
		JMenuBar mb = new JMenuBar();
		fichier = new JMenu("fichier");
		loadBdd = new JMenuItem("load bdd");
		addBdd = new JMenuItem("add bdd");
		saveBdd = new JMenuItem("save bdd");

		jeu = new JMenu("jeu");
		addJeu = new JMenuItem("add Jeu");
		getJeu = new JMenuItem("get Jeu");
		chercheConsole = new JMenuItem("cherche console");
		getJeuFabricant = new JMenuItem("get Jeu Fabricant");
		chercheCote = new JMenuItem("cherche cote");

		addJeu.addActionListener(this);
		fichier.add(loadBdd);
		fichier.add(addBdd);
		fichier.add(saveBdd);

		jeu.add(addJeu);
		jeu.add(getJeu);
		jeu.add(chercheConsole);
		jeu.add(getJeuFabricant);
		jeu.add(chercheCote);

		mb.add(fichier);
		mb.add(jeu);
		
		frame1 = new JFrame();
		
		// boutons
		JButton buttonLoad = new JButton("Load bdd");
		JButton buttonReset = new JButton("Reset");
		buttonLoad.addActionListener(this);
		buttonReset.addActionListener(this);
			
		currentPanel = new JPanel();
		currentPanel.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));
		currentPanel.add(new JLabel("Veuillez choisir une option dans la barre de menu."));
		
		frame1.setJMenuBar(mb);
		frame1.add(currentPanel, BorderLayout.CENTER);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setTitle("Gestion d'une banque de donnees de jeux videos");
		frame1.setSize(l, h);
		frame1.setLocationRelativeTo(null);
		//frame1.pack();
		frame1.setVisible(true);
		frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		loadBdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//frame1.dispose();
				//new loadBdd();
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
				//frame1.dispose();
				//new addBdd();
				swapPanel(addJeuPanel);

			}
		});
		
		
		getJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//frame1.dispose();
				//new addBdd();
				swapPanel(getJeuPanel);

			}
		});
		
		chercheConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//frame1.dispose();
				//new addBdd();
				swapPanel(chercheConsolePanel);

			}
		});
		
		getJeuFabricant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//frame1.dispose();
				//new addBdd();
				swapPanel(getJeuFabPanel);

			}
		});
		
		chercheCote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//frame1.dispose();
				//new addBdd();
				swapPanel(chercheCotePanel);

			}
		});

	}
    public class loadPanel extends JPanel implements ActionListener {{
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		JButton buttonLoad = new JButton("Load bdd");
		JButton buttonReset = new JButton("Reset");
		buttonLoad.addActionListener(this);
		buttonReset.addActionListener(this);
		JLabel demandeFichier = new JLabel("Veuillez entrez le nom du fichier (.txt) a charger");

		bddALoad = new TextField(50);
		this.add(buttonLoad);
		this.add(buttonReset);
		this.add(demandeFichier);
		this.add(bddALoad);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// tf.getText() ca fait rien, donc fonctionne pas.
		String text = bddALoad.getText();
		String action = e.getActionCommand();
		System.out.println(text);
		System.out.println("action" + action);
		if(action == "Load bdd") {
	    	laBase.loadBdd(text);

		}

		if(action == "Reset") {
			bddALoad.setText("");
		} 	
	}}
    
    // la seule qui fonctionne ...
    public class savePanel extends JPanel implements ActionListener {{
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		JButton buttonSave = new JButton("Save bdd");
		JButton buttonReset = new JButton("Reset");
		buttonSave.addActionListener(this);
		buttonReset.addActionListener(this);
		JLabel demandeFichier = new JLabel("Veuillez entrez le nom du fichier dans lequel sauvegarder la banque de donnees");

		
		tf = new TextField(50);
		this.add(buttonSave);
		this.add(buttonReset);
		this.add(demandeFichier);
		this.add(tf);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// tf.getText() ca fait rien, donc fonctionne pas.
		String text = tf.getText();
		String action = e.getActionCommand();
		System.out.println(text);
		System.out.println("action" + action);
		if(action == "Save bdd") {
	    	laBase.saveBdd(text);
		}

		if(action == "Reset") {
			tf.setText("");
		} 	
	}}
    
    public class addPanel extends JPanel implements ActionListener {{
        //this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		JButton buttonAdd = new JButton("Add bdd");
		JButton buttonReset = new JButton("Reset");
		buttonAdd.addActionListener(this);
		buttonReset.addActionListener(this);
		
		JLabel demandeFichier = new JLabel("Veuillez entrez le nom du fichier a ajouter a la banque de donnees");
		bddAAdd = new TextField(50);
		this.add(buttonAdd);
		this.add(buttonReset);
		this.add(demandeFichier);
		this.add(bddAAdd);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// tf.getText() ca fait rien, donc fonctionne pas.
		String strFichier = bddAAdd.getText();
		String action = e.getActionCommand();
		System.out.println(strFichier);
		System.out.println("action" + action);
		if(action == "Add bdd") {
	    	laBase.addBdd(strFichier);
		}

		if(action == "Reset") {
			bddAAdd.setText("");
		} 	
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

    
    // add Jeu
    public class addJeuPanel extends JPanel implements ActionListener {{
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		JButton buttonAddJeu = new JButton("addJeu bdd");
		JButton buttonReset = new JButton("Reset");
		buttonAddJeu.addActionListener(this);
		buttonReset.addActionListener(this);
		JLabel ajouterTitre = new JLabel("Veuillez entrez le titre du jeu a ajouter");
		JLabel ajouterFabricant = new JLabel("Veuillez entrez le fabricant du jeu a ajouter");
		JLabel ajouterCote = new JLabel("Veuillez entrez la cote du jeu a ajouter");

		titreAAjouter = new TextField(50);
		fabricantAAjouter = new TextField(50);
		coteAAjouter = new TextField(50);
		
		this.add(buttonAddJeu);
		this.add(buttonReset);
		this.add(ajouterTitre);
		this.add(titreAAjouter);
		this.add(ajouterFabricant);
		this.add(fabricantAAjouter);
		this.add(ajouterCote);
		this.add(coteAAjouter);

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String strTitreAAjouter = titreAAjouter.getText();
		String strFabAAjouter = fabricantAAjouter.getText();
		String strCoteAAjouter = coteAAjouter.getText();
		
		
		String action = e.getActionCommand();
		System.out.println(titreAAjouter);
		System.out.println(fabricantAAjouter);

		System.out.println("action" + action);
		if(action == "addJeu bdd") {
	    	unJeu = new Jeu(strFabAAjouter, strTitreAAjouter, strCoteAAjouter);
	    	laBase.addJeu(unJeu);
	    	JOptionPane.showMessageDialog(frame1,
	    		    "Le jeu " + strTitreAAjouter + " du fabricant " + strFabAAjouter + " ayant une cote " + strCoteAAjouter + " a bien ete ajoute a la banque de donnees",
	    		    "Ajout d'un jeu",
	    		    JOptionPane.PLAIN_MESSAGE);
		}

		if(action == "Reset") {
			titreAAjouter.setText("");
			fabricantAAjouter.setText("");
			coteAAjouter.setText("");
			
		} 	
	}}
    
    // get Jeu
    public class getJeuPanel extends JPanel implements ActionListener {{
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));
		JButton buttonGetJeu = new JButton("getJeu bdd");
		JButton buttonReset = new JButton("Reset");
		buttonGetJeu.addActionListener(this);
		buttonReset.addActionListener(this);
		JLabel titreAEcrire = new JLabel("Veuillez entrez le titre du jeu recherche");
		JLabel fabricantAEcrire = new JLabel("Veuillez entrez le fabricant du jeu recherche");
		JLabel reponse = new JLabel();

		
		titre = new TextField(50);
		fabricant = new TextField(50);
		this.add(buttonGetJeu);
		this.add(buttonReset);
		this.add(titreAEcrire);
		this.add(titre);
		this.add(fabricantAEcrire);
		this.add(fabricant);
		this.add(reponse);

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// tf.getText() ca fait rien, donc fonctionne pas.
		String strTitre = titre.getText();
		String strFabricant = fabricant.getText();
		
		String action = e.getActionCommand();
		System.out.println(titre);
		System.out.println(fabricant);

		System.out.println("action" + action);
		if(action == "getJeu bdd") {
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
		    		    JOptionPane.PLAIN_MESSAGE);
	    	}

		}

		if(action == "Reset") {
			titre.setText("");
			fabricant.setText("");
		} 	
	}}
    
    
    public class consolePanel extends JPanel implements ActionListener {{
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		JButton buttonConsole = new JButton("Chercher les jeux pour cette console");
		JButton buttonReset = new JButton("Reset");
		buttonConsole.addActionListener(this);
		buttonReset.addActionListener(this);
		JLabel consoleAEcrire = new JLabel("Veuillez entrez une console");

		console = new TextField(50);
		this.add(buttonConsole);
		this.add(buttonReset);
		this.add(consoleAEcrire);
		this.add(console);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// tf.getText() ca fait rien, donc fonctionne pas.
		String strConsole = console.getText();
		String action = e.getActionCommand();
		System.out.println(strConsole);
		System.out.println("action" + action);
		if(action == "Chercher les jeux pour cette console") {
	    	laBase.chercheConsole(strConsole);
	    	if(laBase.chercheConsole(strConsole) != null) {
		    	JOptionPane.showMessageDialog(frame1,
		    			"Voici le(s) jeu(x) se jouant sur la console " + strConsole + ":\n" + laBase.chercheConsole(strConsole),
		    		    "Jeu(x) sur une console",
		    		    JOptionPane.PLAIN_MESSAGE);	    		
	    	} else {
		    	JOptionPane.showMessageDialog(frame1,
		    			"Aucun jeu se jouant sur la console " + strConsole + " n'a ete trouvé",
		    		    "Jeu(x) sur une console",
		    		    JOptionPane.PLAIN_MESSAGE);
	    	}
		}

		if(action == "Reset") {
			console.setText("");
		} 	
	}}
    
    public class fabricantPanel extends JPanel implements ActionListener {{
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		JButton buttonFabricant = new JButton("Chercher les jeux de ce fabricant");
		JButton buttonReset = new JButton("Reset");
		buttonFabricant.addActionListener(this);
		buttonReset.addActionListener(this);
		JLabel fabricantAEcrire = new JLabel("Veuillez entrez un fabricant");

		fab = new TextField(50);
		this.add(buttonFabricant);
		this.add(buttonReset);
		this.add(fabricantAEcrire);
		this.add(fab);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// tf.getText() ca fait rien, donc fonctionne pas.
		String strFabricant = fab.getText();
		String action = e.getActionCommand();
		System.out.println(strFabricant);
		System.out.println("action" + action);
		if(action == "Chercher les jeux de ce fabricant") {
	    	laBase.getJeuxFabricant(strFabricant);
		}

		if(action == "Reset") {
			fab.setText("");
		} 	
	}}
    
    public class cotePanel extends JPanel implements ActionListener {{
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		JButton buttonCote = new JButton("Chercher les jeux avec cette cote");
		JButton buttonReset = new JButton("Reset");
		buttonCote.addActionListener(this);
		buttonReset.addActionListener(this);
		JLabel coteAEcrire = new JLabel("Veuillez entrez une cote");

		cote = new TextField(50);
		this.add(buttonCote);
		this.add(buttonReset);
		this.add(coteAEcrire);
		this.add(cote);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// tf.getText() ca fait rien, donc fonctionne pas.
		String strCote = cote.getText();
		String action = e.getActionCommand();
		System.out.println(strCote);
		System.out.println("action" + action);
		if(action == "Chercher les jeux avec cette cote") {
	    	laBase.chercheCote(strCote);
		}

		if(action == "Reset") {
			cote.setText("");
		} 	
	}}
    
	public static void afficherJeu(TestInterface b, String fab, String titre ) 			
	throws IOException 
	{
		Jeu aAfficher = b.getJeu(titre, fab);
		if( aAfficher != null)
			System.out.println("aAfficher " + aAfficher);
		else
			System.out.println(titre + " n'est pas dans la banque de donnees");
	}
	
	public static void main (String[] args) {
		new	TP2(1000,500);
	}
	
	
	//pas besoin
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = tf.getText();
		String action = e.getActionCommand();
		System.out.println(text);
		System.out.println(action);
		/*
		if(action == "Load bdd") {
	    	laBase.loadBdd(text);

		}


		if(action == "Reset") {
			tf.setText("");
		} 
		*/

	}
}
