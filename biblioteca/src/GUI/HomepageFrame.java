package GUI;

import javax.swing.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import Dominio.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

//import java.net.HttpUrlConnection;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.ActionEvent;
import DBManager.DBManager;
//import View.BorderLayout;
//import View.JLabel;
//import View.JPanel;


public class HomepageFrame extends JFrame implements ActionListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField searchTF;
	private JComboBox<String> typeCB;
	private String[] options;
	private JButton confirm;
	private JButton close;
	private URL url;
	

	public HomepageFrame() {
		super("Homepage Ricerca");
		
		Font f = new Font("Default",Font.PLAIN,18);
		Font f2 = new Font("Default",Font.PLAIN,12);
		options = new String[] {"ISBN","Titolo","Genere","Autore"};		
		
		confirm = new JButton("Cerca");
		confirm.setBackground(new java.awt.Color(253, 185, 19));
		confirm.setForeground(new java.awt.Color(69, 85, 96));
		confirm.setFont(f);
		
		searchTF = new JTextField("");
		searchTF.setFont(f);
		searchTF.setForeground(new java.awt.Color(69, 85, 96));
		
		typeCB = new JComboBox<>(options);
		typeCB.setBackground(new java.awt.Color(253, 185, 19));
		typeCB.setForeground(new java.awt.Color(69, 85, 96));
		typeCB.setFont(f);
		
		confirm.addActionListener(this);
		
		//------------------------- Close Button
		close = new JButton("Close");
		close.setBackground(new java.awt.Color(253, 185, 19));
		close.setForeground(new java.awt.Color(69, 85, 96));
		close.setFont(f);

		close.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		        System.exit(0);
		    }
		});
		//--------------------------
		
		JLabel logo = new JLabel(new ImageIcon("./src/GUI/logof.png")); 
		
		//--------------------------Pannello della ricerca 
	
		JPanel p0_0 = new JPanel(new GridLayout(4,5,0,80));
		
		
		p0_0.setOpaque(true);
		p0_0.setBackground(Color.white);
		
		p0_0.add(new JLabel(""));
		p0_0.add(new JLabel(""));
		p0_0.add(new JLabel(""));
		p0_0.add(new JLabel(""));
		p0_0.add(new JLabel(""));
		  
		p0_0.add(new JLabel(""));
		p0_0.add(searchTF);
		p0_0.add(new JLabel(""));
		p0_0.add(typeCB);
		p0_0.add(new JLabel(""));
		  
		p0_0.add(new JLabel(""));
		p0_0.add(new JLabel(""));
		p0_0.add(confirm);
		p0_0.add(new JLabel(""));
		p0_0.add(new JLabel(""));
		
		p0_0.add(new JLabel(""));
		p0_0.add(new JLabel(""));
		p0_0.add(new JLabel(""));
		p0_0.add(new JLabel(""));
		p0_0.add(new JLabel(""));
		
		/*
		 * Inserisci utente
		 * Inserisci libro
		 * Cerca utente
		 * Visualizza prestiti
		 * ecc.
		 * 
		 * è da fare ancora la divisione dei libri per biblioteca
		
		*/
		
		//------------------- Pannello di sopra
		
		//JPanel p1= new JPanel(new GridLayout(1,3));
		JPanel p1= new JPanel(new BorderLayout());
		p1.setOpaque(true);
		p1.setBackground(Color.white);
		
		JPanel p1_1 = new JPanel(new FlowLayout()); // pannello per bottoni del men� --da fare
		p1_1.setOpaque(true);
		p1_1.setBackground(new java.awt.Color(253, 185, 19));
		
		JPanel p1_2 = new JPanel(new FlowLayout()); // pannello decorativo
		p1_2.setOpaque(true);
		p1_2.setBackground(new java.awt.Color(253, 185, 19));
		
		p1.add(BorderLayout.PAGE_START, new JLabel("<html><br/></html>"));
		p1.add(logo,BorderLayout.CENTER);
		p1.add(BorderLayout.LINE_START,p1_1);
		p1.add(BorderLayout.LINE_END,p1_2);
		
		//------------------- Pannello di sotto
		
		JPanel p3= new JPanel(new GridLayout(2,9));
		p3.setOpaque(true);
		p3.setBackground(Color.white);
		JLabel l1= new JLabel("				About(?)"); // --da fare, basta metterci un botton con sfondo e bordi color bianco(sfondo) 
		l1.setFont(f2);
		l1.setForeground(new java.awt.Color(69, 85, 96));
		
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(close);
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(l1);  
		
		//------------------- Pannello centrale
		
		
		JPanel p2=new JPanel(new BorderLayout());
		p2.add(BorderLayout.PAGE_START,new JLabel(" "));
		p2.add(BorderLayout.LINE_START,new JLabel("                                                    "));
		p2.add(BorderLayout.CENTER,p0_0);
		p2.add(BorderLayout.LINE_END,new JLabel("                                                      "));
		p2.add(BorderLayout.PAGE_END,new JLabel(" "));
		
		p2.setOpaque(true);           
		p2.setBackground(Color.white);
		
		
		p0_0.setBorder(BorderFactory.createLineBorder(new java.awt.Color(253, 185, 19),3));
		
		
		//------------------- Unione dei vari pannelli -> verr� settato come pannello da visualizzare
		
		JPanel pFin= new JPanel(new BorderLayout());
		pFin.setOpaque(true);
		pFin.setBackground(Color.white);
		
		pFin.add(BorderLayout.PAGE_START,p1);
		pFin.add(BorderLayout.PAGE_END,p3);
		pFin.add(BorderLayout.CENTER,p2);
		
		
		setContentPane(pFin);
		
		
		
		//imposto la finestra non-resizible e di dimensione max schermo ( il pulsante Close permette di chiuedere la finestra
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == confirm) {
			try {
				ObjectMapper om = new ObjectMapper();
				url=new URL("http://2.224.243.66:8080/Libro");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
					
				Libro[] libro = om.readValue(read.readLine(),Libro[].class);
				
				ArrayList<Libro> data = new ArrayList<Libro>(Arrays.asList(libro));
				
				
				new TableSearch(data);
				
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			
										
		}
	}
	
	
	
	public static void main(String[] args) {
		new HomepageFrame();
		
		
	}
}