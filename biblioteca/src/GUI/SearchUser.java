package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchUser extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchUser(String ISBN,String codOp) {
		Font f = new Font("Default",Font.PLAIN,15);
		
		JPanel sUser = new JPanel(new GridLayout(5,5,0,30));
		
		sUser.setBackground(Color.white); 
		sUser.setFont(f);
		
		JButton orange = new JButton();
		orange.setBackground(new java.awt.Color(253, 185, 19));
		orange.setFocusPainted(false);
		orange.setBorderPainted(false);
		JButton orange1 = new JButton(codOp);
		orange1.setBackground(new java.awt.Color(253, 185, 19));
		orange1.setFocusPainted(false);
		orange1.setBorderPainted(false);
		JButton orange2 = new JButton(ISBN);
		orange2.setBackground(new java.awt.Color(253, 185, 19));
		orange2.setFocusPainted(false);
		orange2.setBorderPainted(false);
		JButton orange3 = new JButton();
		orange3.setBackground(new java.awt.Color(253, 185, 19));
		orange3.setFocusPainted(false);
		orange3.setBorderPainted(false);
		JButton orange4 = new JButton();
		orange4.setBackground(new java.awt.Color(253, 185, 19));
		orange4.setFocusPainted(false);
		orange4.setBorderPainted(false);
		orange.setEnabled(false);
		orange1.setEnabled(false);
		orange2.setEnabled(false);
		orange3.setEnabled(false);
		orange4.setEnabled(false);
		
		JButton close = new JButton("Esci");
		close.setBackground(new java.awt.Color(253, 185, 19));
		close.setForeground(new java.awt.Color(69, 85, 96));
		close.setFont(f);
		close.setFocusPainted(false);
		close.setBorderPainted(false);
		
		sUser.add(orange);
		sUser.add(orange1);
		sUser.add(orange2);
		sUser.add(orange3);
		sUser.add(orange4);
		
		sUser.add(new JLabel(""));
		sUser.add(new JLabel(""));
		sUser.add(new JLabel(""));
		sUser.add(new JLabel(""));
		sUser.add(new JLabel(""));
		
		sUser.add(new JLabel(""));
		JLabel CU = new JLabel("Inserire Codice Utente");
		CU.setFont(f);
		CU.setForeground(new java.awt.Color(69, 85, 96));
		sUser.add(CU);
		sUser.add(new JLabel(""));
		JTextField CUTF = new JTextField("");
		CUTF.setFont(f);
		CUTF.setForeground(new java.awt.Color(69, 85, 96));
		sUser.add(CUTF);
		sUser.add(new JLabel(""));
	
		sUser.add(new JLabel(""));
		sUser.add(new JLabel(""));
		sUser.add(new JLabel(""));
		sUser.add(new JLabel(""));
		sUser.add(new JLabel(""));
		
		
		sUser.add(close);
		sUser.add(new JLabel(""));
		sUser.add(new JLabel(""));
		sUser.add(new JLabel(""));
		JButton inviaU = new JButton("Invia");
		inviaU.setBackground(new java.awt.Color(253, 185, 19));
		inviaU.setForeground(new java.awt.Color(69, 85, 96));
		inviaU.setFont(f);
		inviaU.setBorderPainted(false);
		inviaU.setFocusPainted(false);
		sUser.add(inviaU);
		
		JDialog jDialog= new JDialog(this, "Cerca Utente"); // JDialog name
		jDialog.setContentPane(sUser);
	
		jDialog.setResizable(false);
		jDialog.setSize(750,300); //JDialog size
		jDialog.setLocationRelativeTo(null);
		jDialog.setUndecorated(true);
	    jDialog.setVisible(true);
	    
	    close.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		        jDialog.dispose();
		    }
		});
	    
	    inviaU.addActionListener(new ActionListener() {  // TO-DO l'action listener per mandare al server
		    public void actionPerformed(ActionEvent e)
		    {
		    	/*
		    	 * Quando si invia il codice utente al server per prenotare il libro
		    	 * 
		    	 */
		    	
		    	if(CUTF.getText().equals(new String(""))) {
		    		JOptionPane.showMessageDialog(jDialog, "Inserire codice utente");
		    	}
		    	else {
		    		try {
			    		String codiceUtente = CUTF.getText();
			    		URL url=new URL(String.format("http://2.224.243.66:8080/prestito?"
			    				+ "ISBN=%s&cf=%s&codOp=%s",ISBN,codiceUtente,codOp));
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						connection.setRequestMethod("POST");
						//BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						//System.out.println(read.readLine());
						
						/*
						 * HANDLE DEWLLA RISPOSTA
						 */
						switch(connection.getResponseCode()) {
							case 200: //Tutto OK
								JOptionPane.showMessageDialog(jDialog, "Prestito confermato");
								
								break;
							case 404:
								//Utente non trovato
								JOptionPane.showMessageDialog(jDialog, "Utente non trovato");
								break;
							case 401:
								//Libro non presente nella biblioteca dell'operatore
								JOptionPane.showMessageDialog(jDialog, "Libro non presente in questa biblioteca");
								break;
							case 403:
								//Libro non è disponibile
								JOptionPane.showMessageDialog(jDialog, "Libro non è disponibile");
								break;
							
						}
						jDialog.dispose();
		    		}
		    		catch(Exception e1) {
		    			e1.printStackTrace();
		    		}
		    		
		    	}
		    }
		});
	}
	
	public static void main(String[] args) {
		new SearchUser("789456123456","AA1111");
		
		
	}

}
