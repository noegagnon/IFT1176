/*
NOM: Gagnon
PRENOM: Noemie
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class TP2 implements ActionListener{

	private JFrame frame1;

	private JPanel panel;
	private JPanel panel2;
	private TextField tf;
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
		JButton buttonLoad = new JButton("Load bdd");

		JButton buttonReset = new JButton("Reset");


		buttonLoad.addActionListener(this);

		buttonReset.addActionListener(this);
		
		tf = new TextField(10);
		panel = new JPanel();
		panel2 = new JPanel();
		
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(4,3));
		panel2.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel2.setLayout(new BorderLayout());
		
		panel2.add(tf);
		panel.add(buttonLoad);

		panel.add(buttonReset);
		
		frame1.setJMenuBar(mb);

		frame1.add(panel2, BorderLayout.NORTH);
		frame1.add(panel, BorderLayout.CENTER);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setTitle("Load une bdd");
		frame1.pack();
		frame1.setVisible(true);

		
		

	}
	private void setSize(int l, int h) {
		// TODO Auto-generated method stub
		
	}
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = tf.getText();
		String action = e.getActionCommand();
		System.out.println(text);
		System.out.println(action);
		if(action == "Load bdd") {
	    	laBase.loadBdd(text);

		}


		if(action == "Reset") {
			tf.setText("");
		} 
	}
}
