import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;


public class Q3 extends JFrame implements ActionListener, ItemListener{
	JFrame frame;
	private String[] choix = new String[] {"1", "2", "5", "10"};
	private JTextField montant, taux, resultat;
	private JButton calculer;
	
	private JComboBox<String> valChoix;
	private int valTerme = 0;
	
	
	//**pas mettre frame devant, ca bug nullpointerexception
	
	public Q3(int l, int h, String title) {
		frame = new JFrame();
		setSize(l,h);
		setTitle(title);
		setLayout(new GridLayout(3,3));
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	    // LIGNE 1 Ajout des titres
	    this.add(new JLabel("Montant"));
	    this.add(new JLabel("taux"));
	    this.add(new JLabel("Terme"));
	    
	    // LIGNE 2 Ajout des boites de text et menu deroulant (JComboBox)
	    this.add(montant = new JTextField(20));
	    this.add(taux = new JTextField(20));
	    
	    // Ajouter les valeur (qui sont dans tableau "choix" au JComboBox
	    valChoix = new JComboBox<String>(choix);

	    // Pour ecouter et faire qqc lorsque click sur element du comboBox
	    valChoix.addItemListener(this);
	    this.add(valChoix);
	    
	    
	    // LIGNE 3 Ajout du bouton, un titre  et boite de text pour afficher valeur
	    calculer = new JButton("calculer");
	    calculer.addActionListener(this);
	    this.add(calculer);
	    this.add(new JLabel("Valeur future"));
	    
	    // pour pas que user puisse ecrire ds textField de resultat
	    resultat = new JTextField(20);
	    resultat.setEditable(false);

	    this.add(resultat);
		setVisible(true);

	} 
	

	// Main method
	public static void main(String[] args) {		
		Q3 invest = new Q3(200,200, "Investissement");
		invest.pack();
		invest.setVisible(true);
	}

	// Action du bouton cacluler
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		double valeurD, montantD, tauxD;
		
		// convertir en Double les valeurs entrees par usager
		montantD = Double.parseDouble(montant.getText());
		tauxD = Double.parseDouble(taux.getText());
		valeurD = montantD*Math.pow(1+tauxD, valTerme);
		
		// sifaut que ce soit un string, fait + "" apres le montant 
		resultat.setText(valeurD + "$");
	}

	// Action pour le JComboBox
	@Override
	public void itemStateChanged(ItemEvent event) {
		// TODO Auto-generated method stub
		if(event.getStateChange() == ItemEvent.SELECTED) {
			valTerme = Integer.parseInt(event.getItem().toString());
			
		}
	}
	

}
