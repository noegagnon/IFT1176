import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class addBdd implements ActionListener {
	private JFrame f = new JFrame("Ajoutez une banque de donnees");
	private TextField tf;
	private JPanel panel;
	private JPanel panel2;
	TestInterface laBase = new Bdd();
	
	public addBdd() {
		JButton buttonAdd = new JButton("Add bdd");

		JButton buttonReset = new JButton("Reset");


		buttonAdd.addActionListener(this);

		buttonReset.addActionListener(this);
		
		tf = new TextField(10);
		panel = new JPanel();
		panel2 = new JPanel();
		
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(4,3));
		panel2.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel2.setLayout(new BorderLayout());
		
		panel2.add(tf);
		panel.add(buttonAdd);

		panel.add(buttonReset);
		

		f.add(panel2, BorderLayout.NORTH);
		f.add(panel, BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Ajouter une bdd");
		f.pack();
		f.setVisible(true);
		
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300,300);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String text = tf.getText();
		String action = e.getActionCommand();
		System.out.println(text);
		System.out.println(action);
		if(action == "Add bdd") {
	    	laBase.addBdd(text);

		}

		if(action == "Reset") {
			tf.setText("");
		} 	
	}
}