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

	private JPanel getJeuPanel = new getJeuPanel();
	
	// menu
	private TextField tf;
	private TextField bddAAdd;
	private TextField bddALoad;
	private TextField fabricant;
	private TextField titre;

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
	

	public TP2(int l, int h){
		setSize(l,h);
		
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
		frame1.setTitle("Menu");
		frame1.pack();
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
		
		getJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//frame1.dispose();
				//new addBdd();
				swapPanel(getJeuPanel);

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
		
		bddALoad = new TextField(50);
		this.add(buttonLoad);
		this.add(buttonReset);
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
		
		tf = new TextField(50);
		this.add(buttonSave);
		this.add(buttonReset);
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
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

		JButton buttonAdd = new JButton("Add bdd");
		JButton buttonReset = new JButton("Reset");
		buttonAdd.addActionListener(this);
		buttonReset.addActionListener(this);
		
		bddAAdd = new TextField(50);
		this.add(buttonAdd);
		this.add(buttonReset);
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
			//reponse.setText(laBase.getJeu(strTitre, strFabricant));
	    	// afficher le jeu.. type Jeu pas string
		}

		if(action == "Reset") {
			titre.setText("");
			fabricant.setText("");
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
		new	TP2(300,300);
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
