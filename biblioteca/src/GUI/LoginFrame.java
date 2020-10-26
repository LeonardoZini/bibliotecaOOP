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
		
		Font f = new Font("Default",Font.PLAIN,18);
		JFrame p0 = new JFrame("Login");
		
		JLabel logo = new JLabel(new ImageIcon("./src/GUI/Piccolo_Logo.png")); 
		
		userTF = new JTextField("CodOp");
		userTF.setPreferredSize( new Dimension( 120, 24 ) );
		
		passwordF = new JPasswordField(10);
		passwordF.setPreferredSize( new Dimension( 120, 24 ) );
		
		okBT= new JButton("Accedi");
		okBT.setPreferredSize( new Dimension( 120, 24 ) );
		okBT.setBackground(new java.awt.Color(253, 185, 19));
		okBT.setForeground(new java.awt.Color(69, 85, 96));
		okBT.setFont(f);
		okBT.setFocusPainted(false);
		okBT.setBorder(BorderFactory.createLineBorder(new java.awt.Color(69, 85, 96), 1));
		
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
					p0.dispose();
					new HomepageFrame(userTF.getText());
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
				
					Font f = new Font("Default",Font.PLAIN,15);
					Font ft = new Font("Default",Font.PLAIN,15);
					
					JPanel wUser = new JPanel(new GridLayout(3,3,0,50));
					
					wUser.setBackground(Color.white); 
					wUser.setFont(f);
					
					JButton orange = new JButton();
					orange.setBackground(new java.awt.Color(253, 185, 19));
					orange.setFocusPainted(false);
					orange.setBorderPainted(false);
					JButton orange1 = new JButton();
					orange1.setBackground(new java.awt.Color(253, 185, 19));
					orange1.setFocusPainted(false);
					orange1.setBorderPainted(false);
					JButton orange2 = new JButton();
					orange2.setBackground(new java.awt.Color(253, 185, 19));
					orange2.setFocusPainted(false);
					orange2.setBorderPainted(false);
					
					JButton esci = new JButton("Esci");
					esci.setBackground(new java.awt.Color(253, 185, 19));
					esci.setForeground(new java.awt.Color(69, 85, 96));
					esci.setFont(f);
					esci.setFocusPainted(false);
					esci.setBorderPainted(false);
					
					wUser.add(orange);
					wUser.add(orange1);
					wUser.add(orange2);
					
					wUser.add(new JLabel(""));
					JLabel testo = new JLabel("Operatore non autorizzato!");
					testo.setFont(ft);
					testo.setForeground(new java.awt.Color(69, 85, 96));
					wUser.add(testo);
					wUser.add(new JLabel(""));
					
					wUser.add(new JLabel(""));
					wUser.add(new JLabel(""));
					wUser.add(esci);
					
					
					
					JDialog jDialog = new JDialog();
					jDialog.setContentPane(wUser);
					
			
					jDialog.setResizable(false);
					jDialog.setSize(550,200); //JDialog size
					jDialog.setLocationRelativeTo(null);
					jDialog.setUndecorated(true);
			        jDialog.setVisible(true);
			        
			        esci.addActionListener(new ActionListener() {
					    public void actionPerformed(ActionEvent e)
					    {
					        jDialog.dispose();
					    }
					});
				}
			}
		});
		
		// Frame generale pagina di Login
		         
		p0.setSize(new Dimension(700,700)); //dim

		//Pannello da attaccare al Frame
		JPanel p1 = new JPanel(new GridBagLayout());
		p1.setPreferredSize(p0.getSize());
		p1.setBackground(Color.WHITE);

		//-------------------- Pannello centale
		JPanel p2 = new JPanel(new GridLayout(4,5,0,90));
		p2.setPreferredSize(new Dimension(550,550));
		p2.setBackground(Color.WHITE);
		
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(logo);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		
		JLabel user = new JLabel("Username");
		user.setForeground(new java.awt.Color(69, 85, 96));
		user.setFont(f);
		p2.add(new JLabel(""));
		p2.add(user);
		p2.add(new JLabel(""));
		userTF.setForeground(new java.awt.Color(69, 85, 96));
		userTF.setFont(f);
		p2.add(userTF);
		p2.add(new JLabel(""));
		
		JLabel password = new JLabel("Password");
		password.setForeground(new java.awt.Color(69, 85, 96));
		password.setFont(f);
		p2.add(new JLabel(""));
		p2.add(password);
		p2.add(new JLabel(""));
		passwordF.setForeground(new java.awt.Color(69, 85, 96));
		passwordF.setFont(f);
		p2.add(passwordF);
		p2.add(new JLabel(""));
		
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(okBT);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		
		
		//---------------------
		
		p1.add(p2);
		p0.add(p1);
		
		
		p2.setBorder(BorderFactory.createLineBorder(new java.awt.Color(253, 185, 19),5));
		
		
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
