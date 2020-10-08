package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.ActionEvent;
import DBManager.DBManager;



public class HomepageFrame extends JFrame implements ActionListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JTextField searchTF;
	private JComboBox<String> typeCB;
	private String[] options;
	private JButton confirm;
	

	public HomepageFrame() {
		super("Homepage");
		
		options = new String[] {"ISBN","Titolo","Genere","Autore"};		
		confirm = new JButton("Cerca");		
		searchTF = new JTextField("");
		typeCB = new JComboBox<>(options);
		
		confirm.addActionListener(this);
		
		
		
		JPanel p2 = new JPanel(new GridLayout(1,2,10,10));
		p2.add(searchTF);
		p2.add(typeCB);
		
		
		
		
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(p2);
		add(confirm);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1600, 1600);
		setVisible(true);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirm) {
			System.out.println("Button pressed..");
			
			//Fare query in base a quello che viene cercato
			DBManager db;
			try {
				db = new DBManager(DBManager.JDBCDriverMySQL, DBManager.JDBCURLMySQL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = db.searchHome(options[typeCB.getSelectedIndex()],searchTF.getText());
				rs.next();
				
				/*
				 * Adesso stampa su console, dovrà stampare tutte le informazioni riguardo a quel libro/ quei libri
				 * tipo se è disponibile, dov'è, chi lo ha preso in prestito, ecc
				 */
				System.out.println(rs.getString(1) + " " + rs.getString("titolo"));
				
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
	}
	
	
	
	public static void main(String[] args) {
		new HomepageFrame();
		
		
	}
}


