package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.ActionEvent;
import DBManager.DBManager;
//import View.BorderLayout;
//import View.JLabel;
//import View.JPanel;


public class LoginFrame extends JFrame implements ActionListener{
	
	
	/*
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	private JTextField userTF;
	private JPasswordField passwordF;
	private JButton okBT;
	
	
	
	public LoginFrame() {
		
		
		
		
		userTF = new JTextField("CodOp");
		userTF.setPreferredSize( new Dimension( 120, 24 ) );
		
		passwordF = new JPasswordField(10);
		passwordF.setPreferredSize( new Dimension( 120, 24 ) );
		
		okBT= new JButton("Accedi");
		okBT.setPreferredSize( new Dimension( 120, 24 ) );
		
		okBT.addActionListener(new ActionListener() {
			
			 @Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * 1. Invia dati al server
				 * 2. Aspetta risposta
				 * 3. Se ok, apri homepage, passando il codice operatore come parametro
				 * 4. Chiudi pagina login
				 */
				 
				try {
					@SuppressWarnings("deprecation")
					URL url=new URL(String.format("http://2.224.243.66:8080/login?user=%s&password=%s",userTF.getText(),passwordF.getText()));
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("POST");
					BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					System.out.println(read.readLine());
					rootPane.setVisible(false);
					rootPane.setEnabled(false);
					new HomepageFrame(userTF.getText());
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
				
					JOptionPane.showMessageDialog(rootPane, "Operatore non autorizzato");
				}
				
					
				 
				
			}
		});
		
		// Frame generale pagina di Login
		JFrame p0 = new JFrame("Login");         
		p0.setSize(new Dimension(600,600)); //dim

		//Pannello da attaccare al Frame
		JPanel p1 = new JPanel(new GridBagLayout());
		p1.setPreferredSize(p0.getSize());
		p1.setBackground(Color.WHITE);

		//-------------------- Pannello centale
		JPanel p2 = new JPanel(new GridLayout(4,4,0,70));
		p2.setPreferredSize(new Dimension(370,370));
		p2.setBackground(Color.WHITE);
		
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		
		p2.add(new JLabel(""));
		p2.add(new JLabel("Username"));
		p2.add(userTF);
		p2.add(new JLabel(""));
		
		p2.add(new JLabel(""));
		p2.add(new JLabel("Password"));
		p2.add(passwordF);
		p2.add(new JLabel(""));
		
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(okBT);
		
		
		
		//---------------------
		
		

		p1.add(p2);
		p0.add(p1);
		
		
		p2.setBorder(BorderFactory.createLineBorder(new java.awt.Color(253, 185, 19),3));
		
		
		p0.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		p0.setResizable(false);
		p0.setLocationRelativeTo(null);
		p0.setVisible(true);  
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
	}
	
	public static void main(String[] args) {
		new LoginFrame();
		
		
	}
	

	
}




/*

- finire bottone OK
-fare actionListener bottone OK
-finire parte PASSWORD
-abbellire design generale


*/