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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	private String CodOp;
	
	private	JButton NewUserBT;
	private	JButton NewBookBT;
	private	JButton SearchUserBT;
	private	JButton BooksBT;
	
	private JButton AboutBT;
	

	public HomepageFrame(String CodOp) {
		super("Homepage Ricerca");
		
		this.CodOp=CodOp;

		Font f = new Font("Default",Font.PLAIN,18);
		Font f2 = new Font("Default",Font.PLAIN,8);
		Font ft = new Font("Default",Font.PLAIN,15);
		options = new String[] {"ISBN","Titolo","Genere","Autore"};		
		
		confirm = new JButton("Cerca");
		confirm.setBackground(new java.awt.Color(253, 185, 19));
		confirm.setForeground(new java.awt.Color(69, 85, 96));
		confirm.setFont(f);
		confirm.setFocusPainted(false);
		confirm.setBorder(BorderFactory.createLineBorder(new java.awt.Color(69, 85, 96), 1));
		
		NewUserBT = new JButton("Aggiungi Utente");
		NewBookBT = new JButton("Aggiungi Libro");
		SearchUserBT = new JButton("Cerca Utente");
		BooksBT = new JButton("Visualizza Prestiti");
		
		NewUserBT.setBackground(new java.awt.Color(253, 185, 19));
		NewUserBT.setForeground(new java.awt.Color(69, 85, 96));
		NewUserBT.setFont(f);
		NewUserBT.setFocusPainted(false);
        NewUserBT.setBorder(BorderFactory.createLineBorder(new java.awt.Color(69, 85, 96), 1));
		NewBookBT.setBackground(new java.awt.Color(253, 185, 19));
		NewBookBT.setForeground(new java.awt.Color(69, 85, 96));
		NewBookBT.setFont(f);
		NewBookBT.setFocusPainted(false);
		NewBookBT.setBorder(BorderFactory.createLineBorder(new java.awt.Color(69, 85, 96), 1));
		SearchUserBT.setBackground(new java.awt.Color(253, 185, 19));
		SearchUserBT.setForeground(new java.awt.Color(69, 85, 96));
		SearchUserBT.setFont(f);
		SearchUserBT.setFocusPainted(false);
		SearchUserBT.setBorder(BorderFactory.createLineBorder(new java.awt.Color(69, 85, 96), 1));
		BooksBT.setBackground(new java.awt.Color(253, 185, 19));
		BooksBT.setForeground(new java.awt.Color(69, 85, 96));
		BooksBT.setFont(f);
        BooksBT.setFocusPainted(false);
        BooksBT.setBorder(BorderFactory.createLineBorder(new java.awt.Color(69, 85, 96), 1));
		
		AboutBT = new JButton("             About");
		AboutBT.setBackground(Color.white);
		AboutBT.setForeground(Color.black);
		AboutBT.setFont(ft);
		AboutBT.setContentAreaFilled(false);
        AboutBT.setFocusPainted(false);
        AboutBT.setBorderPainted(false);
		
		searchTF = new JTextField("ALL");
		searchTF.setFont(f);
		searchTF.setForeground(new java.awt.Color(69, 85, 96));
		
		typeCB = new JComboBox<>(options);
		typeCB.setBackground(new java.awt.Color(253, 185, 19));
		typeCB.setForeground(new java.awt.Color(69, 85, 96));
		typeCB.setFont(f);
		typeCB.setFocusable(false);
		
		confirm.addActionListener(this);
		NewUserBT.addActionListener(this);
		NewBookBT.addActionListener(this);
		SearchUserBT.addActionListener(this);
		BooksBT.addActionListener(this);
		AboutBT.addActionListener(this);
		
		//------------------------- Close Button
		close = new JButton("Esci");
		close.setBackground(new java.awt.Color(253, 185, 19));
		close.setForeground(new java.awt.Color(69, 85, 96));
		close.setFont(f);
		close.setFocusPainted(false);
		close.setBorder(BorderFactory.createLineBorder(new java.awt.Color(69, 85, 96), 1));

		close.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		        System.exit(0);
		    }
		});
		//--------------------------
		
		JLabel logo = new JLabel(new ImageIcon("./src/GUI/Logo_BT.png")); 
		
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
		
		p0_0.add(NewUserBT);
		p0_0.add(NewBookBT);
		p0_0.add(new JLabel(""));
		p0_0.add(SearchUserBT);
		p0_0.add(BooksBT);
		
		
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
		
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		
		JLabel UserN = new JLabel();
		UserN.setText("   Operatore:  " +CodOp);
		UserN.setFont(ft);
		UserN.setForeground(Color.black);
		
		p3.add(UserN);
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(close);
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(new JLabel(""));
		p3.add(AboutBT);  
		
		//------------------- Pannello centrale
		
		
		JPanel p2=new JPanel(new BorderLayout());
		p2.add(BorderLayout.PAGE_START,new JLabel(" "));
		p2.add(BorderLayout.LINE_START,new JLabel("                                                    "));
		p2.add(BorderLayout.CENTER,p0_0);
		p2.add(BorderLayout.LINE_END,new JLabel("                                                      "));
		p2.add(BorderLayout.PAGE_END,new JLabel(" "));
		
		p2.setOpaque(true);           
		p2.setBackground(Color.white);
		
		
		p0_0.setBorder(BorderFactory.createLineBorder(new java.awt.Color(253, 185, 19),4));
		
		
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
				String field = searchTF.getText().toString().replace(" ", "-");
				if(field.equals(new String("ALL"))) {
					
					url=new URL("http://2.224.243.66:8080/Libro");
				}
				else {
					if (typeCB.getSelectedIndex() == 0) {
						//ISBN
						url=new URL(String.format("http://2.224.243.66:8080/Libro/byISBN?ISBN=%s",field));
					}
					else if	(typeCB.getSelectedIndex() == 1)
					{
						//Titolo
						url=new URL(String.format("http://2.224.243.66:8080/Libro/byTitolo?titolo=%s",field));
					}
					else if	(typeCB.getSelectedIndex() == 2) {
						//Genere
						url=new URL(String.format("http://2.224.243.66:8080/Libro/byGenere?genere=%s",field));
					}
					else if (typeCB.getSelectedIndex() == 3) {
						//Autore
						url=new URL(String.format("http://2.224.243.66:8080/Libro/byAutore?autore=%s",field));
					}
				}
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				Libro[] libro = om.readValue(read.readLine(),Libro[].class);
				ArrayList<Libro> data = new ArrayList<Libro>(Arrays.asList(libro));
				
				if (!data.isEmpty()){
					new TableSearch(data);
				}
				else {
					JOptionPane.showMessageDialog(null, "Nessun riscontro");
				}
				
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Dio porco non va un cazzo\n"+  e1.toString());
			}								
		}
		
		if(e.getSource() == AboutBT) {
			
			Font f = new Font("Default",Font.PLAIN,15);
			
			JPanel aboutPL= new JPanel();
			aboutPL.setBackground(new java.awt.Color(253, 185, 19));
			
			// Messaggio che apparirà nel JDialog premendo su about, basta modificarlo qui sotto ( usare \n per andare a capo)
			JTextArea textArea= new JTextArea("@Lorenzo Fontanili\n@Leonardo Zini\nTesina per il corso di Programmazione ad Oggetti anno 2019-2020");
			textArea.setForeground(new java.awt.Color(69, 85, 96));   
			textArea.setBackground(new java.awt.Color(253, 185, 19)); 
			textArea.setFont(f);
			textArea.setEditable(false);
			
			aboutPL.add(textArea);
			
			JDialog aboutJD= new JDialog(this, "About BiblioTech"); // JDialog name
			aboutJD.setContentPane(aboutPL);
			
			aboutJD.setResizable(false);
			aboutJD.setSize(600,150); //JDialog size
			aboutJD.setLocationRelativeTo(null);
			
	        aboutJD.setVisible(true);
	        
		}
		
		if(e.getSource() == NewUserBT) {
			
			/*
			 * Creazione form per inserimento nuovo utente
			 */
			Font f = new Font("Default",Font.PLAIN,15);
			
			JPanel pUser = new JPanel(new GridLayout(6,5,0,50));
			
			pUser.setBackground(Color.white); 
			pUser.setFont(f);
			
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
			
			close = new JButton("Esci");
			close.setBackground(new java.awt.Color(253, 185, 19));
			close.setForeground(new java.awt.Color(69, 85, 96));
			close.setFont(f);
			close.setFocusPainted(false);
			close.setBorderPainted(false);
			
			pUser.add(orange);
			pUser.add(orange1);
			pUser.add(orange2);
			pUser.add(orange3);
			pUser.add(orange4);
			
			pUser.add(new JLabel(""));
			JLabel nome = new JLabel("Nome");
			nome.setFont(f);
			nome.setForeground(new java.awt.Color(69, 85, 96));
			pUser.add(nome);
			pUser.add(new JLabel(""));
			JTextField NomeTF = new JTextField("");
			NomeTF.setFont(f);
			NomeTF.setForeground(new java.awt.Color(69, 85, 96));
			pUser.add(NomeTF);
			pUser.add(new JLabel(""));
			
			pUser.add(new JLabel(""));
			JLabel Cognome = new JLabel("Cognome");
			Cognome.setFont(f);
			Cognome.setForeground(new java.awt.Color(69, 85, 96));
			pUser.add(Cognome);
			pUser.add(new JLabel(""));
			JTextField CognomeTF = new JTextField("");
			CognomeTF.setFont(f);
			CognomeTF.setForeground(new java.awt.Color(69, 85, 96));
			pUser.add(CognomeTF);
			pUser.add(new JLabel(""));
			
			pUser.add(new JLabel(""));
			JLabel CF = new JLabel("CF");
			CF.setFont(f);
			CF.setForeground(new java.awt.Color(69, 85, 96));
			pUser.add(CF);
			pUser.add(new JLabel(""));
			JTextField CFTF = new JTextField("");
			CFTF.setFont(f);
			CFTF.setForeground(new java.awt.Color(69, 85, 96));
			pUser.add(CFTF);
			pUser.add(new JLabel(""));

			pUser.add(new JLabel(""));
			JLabel Data = new JLabel("Data di Nascita");
			Data.setFont(f);
			Data.setForeground(new java.awt.Color(69, 85, 96));
			pUser.add(Data);
			
			pUser.add(new JLabel(""));
			JFormattedTextField DataTF = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
			DataTF.setText("yyyy-mm-dd");
			DataTF.setFont(f);
			DataTF.addFocusListener(new FocusAdapter() {
				 public void focusGained(FocusEvent e2) {
					DataTF.setText("");
				 }
				
			});
			DataTF.setForeground(new java.awt.Color(69, 85, 96));
			pUser.add(DataTF);
			pUser.add(new JLabel(""));
			
			
			pUser.add(close);
			pUser.add(new JLabel(""));
			pUser.add(new JLabel(""));
			pUser.add(new JLabel(""));
			JButton inviaU = new JButton("Invia");
			inviaU.setBackground(new java.awt.Color(253, 185, 19));
			inviaU.setForeground(new java.awt.Color(69, 85, 96));
			inviaU.setFont(f);
			inviaU.setBorderPainted(false);
			inviaU.setFocusPainted(false);
			pUser.add(inviaU);
			
			JDialog jDialog= new JDialog(this, "Aggiungi Utente"); // JDialog name
			jDialog.setContentPane(pUser);
	
			jDialog.setResizable(false);
			jDialog.setSize(800,500); //JDialog size
			jDialog.setLocationRelativeTo(null);
			jDialog.setUndecorated(true);
	        jDialog.setVisible(true);
	        
	        close.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e)
			    {
			        jDialog.dispose();
			    }
			});
	        
	        inviaU.addActionListener(new ActionListener() {  
			    public void actionPerformed(ActionEvent e)
{
			    	/*
			    	 * Action listener per mandare al server le informazioni del nuovo utente
			    	 */
			    	
			    	try{
			    		String nome=NomeTF.getText();
			    		String cognome = CognomeTF.getText();
			    		String data_nascita = DataTF.getText();
			    		String cF= CFTF.getText();
			    		
			    		System.out.println(String.format("http://2.224.243.66:8080/insert/utente?nome=%s&cognome=%s&"
			    				+ "cf=%s&data-nascita=%s",nome,cognome,data_nascita,cF));
			    		
			    		
			    		url=new URL(String.format("http://2.224.243.66:8080/insert/utente?nome=%s&cognome=%s&"
			    				+ "cf=%s&data-nascita=%s",nome,cognome,cF,data_nascita));
			    		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			    		
						connection.setRequestMethod("POST");
						
						if(connection.getResponseCode()==200) {
							JOptionPane.showMessageDialog(rootPane, "Utente inserito con successo!");
						}
						else if(connection.getResponseCode()==403) {
							JOptionPane.showMessageDialog(rootPane, "Errore interno");
						}
						else {
							JOptionPane.showMessageDialog(rootPane, "Errore nell'inserimento dell'utente!");
						}
						
		
			    	}
			    	catch(IOException e1) {
			    		System.out.println("WTF are u doing");
			    		e1.printStackTrace();
			    	
			    	}
			    }
			});
	        
		}
		
		if(e.getSource() == NewBookBT) {
			/*
			 * Creazione jDialog per creare form per inserire un nuovo libro
			 */
			
			Font f = new Font("Default",Font.PLAIN,15);
			
			JPanel pBook = new JPanel(new GridLayout(7,5,0,35));
			
			pBook.setBackground(Color.white); 
			pBook.setFont(f);
			
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
			
			close = new JButton("Esci");
			close.setBackground(new java.awt.Color(253, 185, 19));
			close.setForeground(new java.awt.Color(69, 85, 96));
			close.setFont(f);
			close.setFocusPainted(false);
			close.setBorderPainted(false);
			
			pBook.add(orange);
			pBook.add(orange1);
			pBook.add(orange2);
			pBook.add(orange3);
			pBook.add(orange4);
			
			pBook.add(new JLabel(""));
			JLabel ISBN = new JLabel("ISBN");
			ISBN.setFont(f);
			ISBN.setForeground(new java.awt.Color(69, 85, 96));
			pBook.add(ISBN);
			pBook.add(new JLabel(""));
			JTextField ISBNTF = new JTextField("");
			ISBNTF.setFont(f);
			ISBNTF.setForeground(new java.awt.Color(69, 85, 96));
			pBook.add(ISBNTF);
			pBook.add(new JLabel(""));
			
			pBook.add(new JLabel(""));
			JLabel nome = new JLabel("Nome");
			nome.setFont(f);
			nome.setForeground(new java.awt.Color(69, 85, 96));
			pBook.add(nome);
			pBook.add(new JLabel(""));
			JTextField NomeTF = new JTextField("");
			NomeTF.setFont(f);
			NomeTF.setForeground(new java.awt.Color(69, 85, 96));
			pBook.add(NomeTF);
			pBook.add(new JLabel(""));
			
			pBook.add(new JLabel(""));
			JLabel Genere = new JLabel("Genere");
			Genere.setFont(f);
			Genere.setForeground(new java.awt.Color(69, 85, 96));
			pBook.add(Genere);
			pBook.add(new JLabel(""));
			JTextField GenereTF = new JTextField("");
			GenereTF.setFont(f);
			GenereTF.setForeground(new java.awt.Color(69, 85, 96));
			pBook.add(GenereTF);
			pBook.add(new JLabel(""));
			
			pBook.add(new JLabel(""));
			JLabel Autore = new JLabel("Autore");
			Autore.setFont(f);
			Autore.setForeground(new java.awt.Color(69, 85, 96));
			pBook.add(Autore);
			pBook.add(new JLabel(""));
			JTextField AutoreTF = new JTextField("");
			AutoreTF.setFont(f);
			AutoreTF.setForeground(new java.awt.Color(69, 85, 96));
			pBook.add(AutoreTF);
			pBook.add(new JLabel(""));

			pBook.add(new JLabel(""));
			JLabel Pagine = new JLabel("Pagine");
			Pagine.setFont(f);
			Pagine.setForeground(new java.awt.Color(69, 85, 96));
			pBook.add(Pagine);
			pBook.add(new JLabel(""));
			JTextField PagineTF = new JTextField("");
			PagineTF.setFont(f);
			PagineTF.setForeground(new java.awt.Color(69, 85, 96));
			pBook.add(PagineTF);
			pBook.add(new JLabel(""));
			
			pBook.add(close);
			pBook.add(new JLabel(""));
			pBook.add(new JLabel(""));
			pBook.add(new JLabel(""));
			JButton inviaL = new JButton("Invia");
			inviaL.setBackground(new java.awt.Color(253, 185, 19));
			inviaL.setForeground(new java.awt.Color(69, 85, 96));
			inviaL.setFont(f);
			inviaL.setFocusPainted(false);
			inviaL.setBorderPainted(false);
			pBook.add(inviaL);
			
			inviaL.addActionListener(this); // TO-DO
			
			JDialog jDialog= new JDialog(this, "Aggiungi Libro"); // JDialog name
			jDialog.setContentPane(pBook);
	
			jDialog.setResizable(false);
			jDialog.setSize(800,500); //JDialog size
			jDialog.setLocationRelativeTo(null);
			jDialog.setUndecorated(true);
	        jDialog.setVisible(true);
	        
	        close.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e)
			    {
			        jDialog.dispose();
			    }
			});
	        
	        inviaL.addActionListener(new ActionListener() {  
			    public void actionPerformed(ActionEvent e)
			    {
			    	/*
			    	 * Action listener per mandare al server le informazioni di un nuovo libro
			    	 */
			    	try{
			    		String isbn=ISBNTF.getText();
			    		String titolo = NomeTF.getText();
			    		String genere = GenereTF.getText();
			    		String autore= AutoreTF.getText();
			    		String pagine = PagineTF.getText();
			    		
			    		System.out.println(String.format("http://2.224.243.66:8080/insert/libro?ISBN=%s&titolo=%s&"
			    				+ "genere=%s&autore=%s&pagine%s",isbn,titolo,genere,autore,pagine));
			    		
			    		
			    		url=new URL(String.format("http://2.224.243.66:8080/insert/libro?ISBN=%s&titolo=%s&"
			    				+ "genere=%s&pagine=%s&autore=%s&",isbn,titolo,genere,pagine,autore));
			    		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			    		
						connection.setRequestMethod("POST");
						
						if(connection.getResponseCode()==200) {
							JOptionPane.showMessageDialog(rootPane, "Libro inserito con successo!");
						}
						else if(connection.getResponseCode()==403) {
							JOptionPane.showMessageDialog(rootPane, "ISBN gia presente nel database");
						}
						else {
							JOptionPane.showMessageDialog(rootPane, "Errore nell'inserimento del libro!");
						}
						
		
			    	}
			    	catch(IOException e1) {
			    		System.out.println("WTF are u doing");
			    		e1.printStackTrace();
			    	
			    	}
			    }
			});
	        
		}
		
		if(e.getSource()==SearchUserBT) {
			
			Font f = new Font("Default",Font.PLAIN,15);
			
			JPanel sUser = new JPanel(new GridLayout(5,5,0,30));
			
			sUser.setBackground(Color.white); 
			sUser.setFont(f);
			
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
			
			close = new JButton("Esci");
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
			    	jDialog.dispose();
			    }
			});
		}
	}
	
	
	
	public static void main(String[] args) {
		new HomepageFrame("AA1111");
		
		
	}
}
