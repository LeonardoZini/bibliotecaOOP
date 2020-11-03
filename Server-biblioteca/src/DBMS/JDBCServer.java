package DBMS;
import org.xml.sax.*;
import Dominio.*;
import static spark.Spark.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


import org.slf4j.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JDBCServer {
	static Logger logger = LoggerFactory.getLogger(JDBCServer.class);
	ObjectMapper om = new ObjectMapper();
	DBManager db;

	public void dbConnection() {
		try {
			db = new DBManager(DBManager.JDBCDriverMySQL, DBManager.JDBCURLMySQL);
			db.executeQuery("SELECT * FROM Libro LIMIT 1");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver not found");
			throw new RuntimeException();
		} catch (SQLException e) {
			System.out.println("Error in connecting database...");
			System.out.println(e.toString());
		}
	}

	public void run() {
		dbConnection();

		// Start embedded server at this port
		port(8080);

		/*
		 * API di test
		 */
		get("/", (request, reponse) -> {
			String r="Welcome";
			return r;
		});

		/*
		 * Ottenere tutti i libri presenti nel sistema
		 */
		get("/Libro", (request, response) -> {
			String query;

			query = String.format("SELECT L.*, Lo.disponibile FROM Libro L JOIN Locazione Lo ON Lo.ISBN=L.ISBN ");
			ResultSet rs = db.executeQuery(query);
			
			ArrayList<Libro> l = new ArrayList<Libro>();
			while (rs.next()) {
				l.add(new Libro(rs.getString("ISBN"),rs.getString("titolo"),rs.getString("disponibile"),rs.getString("genere"),rs.getString("autore"),rs.getInt("pagine")));
			}
			response.status(200);
			return om.writeValueAsString(l);
		});
		
		/*
		 * Ottenere ilibri con l'isbn desiderato
		 */
		get("/Libro/byISBN", (request, response) -> {
			String query;

			
			query = String.format("SELECT L.*, Lo.disponibile FROM Libro L JOIN Locazione Lo ON Lo.ISBN=L.ISBN "
					+ "where L.ISBN=\"%s\";",request.queryParams("ISBN"));
			
			ResultSet rs = db.executeQuery(query);
			ArrayList<Libro> l = new ArrayList<Libro>();
			while (rs.next()) {
				l.add(new Libro(rs.getString("ISBN"),rs.getString("titolo"),rs.getString("disponibile"),rs.getString("genere"),rs.getString("autore"),rs.getInt("pagine")));
			}
			response.status(200);
			return om.writeValueAsString(l);
		});
		/*
		 * Ottenere libri con il titolo desiderato
		 */
		get("/Libro/byTitolo", (request, response) -> {
			String query;

			
			query = String.format("SELECT L.*, Lo.disponibile "
					+ "FROM Libro L JOIN Locazione Lo ON Lo.ISBN=L.ISBN "
					+ "where L.titolo LIKE \"%s\" "
					+ ";",request.queryParams("titolo").replace("-", " "));
			ResultSet rs = db.executeQuery(query);

			ArrayList<Libro> l = new ArrayList<Libro>();
			while (rs.next()) {
				l.add(new Libro(rs.getString("ISBN"),rs.getString("titolo"),rs.getString("disponibile"),rs.getString("genere"),rs.getString("autore"),rs.getInt("pagine")));
			}
			response.status(200);
			return om.writeValueAsString(l);
		});
		
		/*
		 * ottenere i prestiti del genere desiderato
		 */
		get("/Libro/byGenere", (request, response) -> {
			String query;

			
			query = String.format("SELECT L.*, Lo.disponibile FROM Libro L JOIN Locazione Lo ON Lo.ISBN=L.ISBN where L.genere LIKE \"%s\";",request.queryParams("genere").replace("-", " "));
			ResultSet rs = db.executeQuery(query);

			ArrayList<Libro> l = new ArrayList<Libro>();
			while (rs.next()) {
				l.add(new Libro(rs.getString("ISBN"),rs.getString("titolo"),rs.getString("disponibile"),rs.getString("genere"),rs.getString("autore"),rs.getInt("pagine")));
			}
			response.status(200);
			return om.writeValueAsString(l);
		});
		
		/*
		 * Ottenere i libri dell'autore desiderato
		 */
		get("/Libro/byAutore", (request, response) -> {
			String query;

			
			query = String.format("SELECT L.*, Lo.disponibile FROM Libro L JOIN Locazione Lo ON Lo.ISBN=L.ISBN where L.autore LIKE \"%s\";",request.queryParams("autore").replace("-", " "));
			ResultSet rs = db.executeQuery(query);

			ArrayList<Libro> l = new ArrayList<Libro>();
			while (rs.next()) {
				l.add(new Libro(rs.getString("ISBN"),rs.getString("titolo"),rs.getString("disponibile"),rs.getString("genere"),rs.getString("autore"),rs.getInt("pagine")));
			}
			response.status(200);
			return om.writeValueAsString(l);
		});
		
		
		/*
		 * Login dell'operatore
		 * 
		 */
		
		/*
		 * API: per effettuare login dell'operatore da desktop
		 */
		post("/login", (request, response) -> {
			String user = request.queryParams("user");
			String pswd = request.queryParams("password");
			System.out.println("User: " + user + " pswd: " + pswd);
			
			String query = String.format("SELECT idOperatore FROM Operatore where "
					+ "idOperatore = \"%s\" AND password = \"%s\";",user,pswd);
			try {
				ResultSet rs = db.executeQuery(query);
				if(rs.next())
				{
					
					System.out.println(rs.getString(1));
					response.status(200);
					return "OK";
				}
				else {
					response.status(404);
					System.out.println("Non autenticato");
					return "NO";
				}
				
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
			return "Error";
			
			
			
		});
		/*
		 * API: ritorna la lista di utenti iscritti al sistema
		 */
		get("/Utente", (request, response) -> {
			String cF = request.queryParams("CF");
			String query = String.format("SELECT * FROM Utente WHERE CF = \"%s\";", cF);
			ResultSet rs = db.executeQuery(query);
			return "LOG";
			
		});
		/*
		 * API: inserire libro da desktop
		 */
		
		post("/insert/libro", (request, response) -> {
			
			try {
				String isbn = request.queryParams("ISBN");
				String titolo = request.queryParams("titolo").replace('-', ' ');
				String genere = request.queryParams("genere").replace('-', ' ');
				String autore = request.queryParams("autore").replace('-', ' ');
				String pagine = request.queryParams("pagine").replace('-', ' ');
				String idOp = request.queryParams("codop");
				System.out.println(isbn + titolo + pagine + idOp);
				
				String query=String.format("INSERT INTO Libro(ISBN,titolo,genere,autore,pagine) VALUES"
						+ "(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");",isbn,titolo,genere,autore,pagine);
				System.out.println(query);
				db.executeUpdate(query);
				query=String.format("SELECT biblioteca FROM "
						+ "Operatore WHERE idOperatore = \"%s\";",idOp);
				ResultSet rs = db.executeQuery(query);
				
				if(rs.next()) {
					query=String.format("INSERT INTO Locazione(ISBN,idBiblioteca,disponibile) VALUES (\"%s\",\"%s\",\"S\");",isbn,rs.getString("biblioteca"));
					db.executeUpdate(query);
					response.status(200);
					return "insert";
				}
				else {
					response.status(401);
					return "Operator not found";
				}
				
			}
			catch(SQLException e1) {
				e1.printStackTrace();
				response.status(404);
				return "Database Error";
			}
			catch(Exception e1) {
				response.status(500);
				e1.printStackTrace();
				return "Server Error";
			}
			
			
		});

		/*
		 * API: inserire utente da desktop
		 */
		post("/insert/utente", (request, response) -> {
			String nome = request.queryParams("nome");
			String cognome = request.queryParams("cognome");
			String cF = request.queryParams("cf");
			String data_nascita = request.queryParams("data-nascita");

			try {
				String query=String.format("INSERT INTO Utente(CF,nome,cognome,data_nascita) VALUES"
						+ "(\"%s\",\"%s\",\"%s\",\"%s\");",cF,nome,cognome,data_nascita);
				System.out.println(query);
				db.executeUpdate(query);
				response.status(200);
			}
			catch(Exception e1) {
				response.status(403);
				e1.printStackTrace();
				System.out.println("Errore");
				return "403";
			}
			
			
			
			return "200";
		});
		
		/*
		 * Funzione per ottenere i prestiti di un cliente che
		 * utilizza telegram
		 */
		get("/telegram/utente", (request, response) -> {
			String idTelegram= request.queryParams("id");
			ObjectMapper om = new ObjectMapper();
			ResultSet rs;
			HashMap<Integer,ArrayList<String>> resp = new HashMap<Integer,ArrayList<String>>();
			try {
				String query=String.format("SELECT P.ISBN, P.data_prestito, "
						+ "P.data_consegna FROM Utente U JOIN"
						+ " Prestito P WHERE P.CF=U.CF and U.idTelegram=\"%s\";",idTelegram);
				rs = db.executeQuery(query);
				response.status(200);
				
				int i=0;
				while(rs.next()) {
					ArrayList<String> tmp = new ArrayList<String>();
					tmp.add(rs.getString(1));
					tmp.add(rs.getString(2));
					tmp.add(rs.getString(3));
					resp.put(i, tmp);
					i++;
				}
				System.out.println(resp);
				return om.writeValueAsString(resp);
			}
			catch(Exception e1) {
				response.status(403);
				e1.printStackTrace();
				System.out.println("Errore");
				return "403";
			}
			
			
			
		});
		
		/*
		 * API per registrare al servizio telegram un utente della biblioteca
		 */
		
		post("/telegram/registrazione", (request, response) -> {
			String idTelegram= request.queryParams("id");
			String cF = request.queryParams("cf");

			try {
				String query=String.format("UPDATE Utente"
						+ "SET idTelegram=\"%s\""
						+ "WHERE CF=\"%s\";",idTelegram,cF);
				System.out.println(query);
				db.executeUpdate(query);
				response.status(200);
			}
			catch(Exception e1) {
				response.status(403);
				e1.printStackTrace();
				System.out.println("Errore");
				return "403";
			}
				
			
			return "200";
		});
		post("/prestito", (request, response) -> {
		
			String isbn = request.queryParams("ISBN");
			String cf_utente = request.queryParams("cf");
			String codOp = request.queryParams("codOp");
			
			String check_cf_query=String.format("SELECT * FROM Utente WHERE "
					+ "CF=\"%s\";", cf_utente);
			/*
			 * Controllo che il result set non sia vuoto
			 */
			
			/*
			 * Controllo che il libro sia disponibile 
			 * nella biblioteca dell'operatore
			 */
			String check_disp=String.format(""
					+ "SELECT disponibile "
					+ "FROM Locazione L JOIN Operatore O "
					+ "ON L.idBiblioteca=O.biblioteca "
					+ "WHERE L.ISBN=\"%s\" "
					+ "AND O.idOperatore=\"%s\";"
					
					,isbn,codOp);
			
			System.out.println(check_cf_query);
			System.out.println(check_disp);
			
			/*
			 * Controllo che il result set sia 1 e che il campo sia S
			 */
			ResultSet rs1 = db.executeQuery(check_cf_query);
			if(rs1.next())
			{
				
				System.out.println("Utente esiste, verifichiamo condizioni");
				ResultSet rs2 = db.executeQuery(check_disp);
				if (rs2.next()) {
					if(rs2.getString(1).equals(new String("S"))) {
						/*
						 * Libro esiste nella biblioteca dell'operatore
						 * ed è disponibile. Query di UPDATE TODO
						 */
						System.out.println("Libro disponibile nella biblioteca");
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDateTime now = LocalDateTime.now();
						String date = new String(dtf.format(now).toString());
						String query_update=String.format(""
								+ "INSERT INTO Prestito(ISBN,CF,idOperatore,data_prestito,data_consegna)"
								+ " VALUES (\"%s\",\"%s\",\"%s\",\"%s\",NULL);"
								+ "",isbn,cf_utente,codOp,date);
						String query_unavailable=String.format(""
								+ "UPDATE Locazione"
								+ " SET disponibile='N'"
								+ " WHERE ISBN=\"%s\";"
								+ "", isbn);
						db.executeUpdate(query_update);
						db.executeUpdate(query_unavailable);
						System.out.println(query_update +"\n"+query_unavailable);

					}
					else {
						System.out.println("Libro non disponibile nella biblioteca");
						System.out.println(rs2.getString(1));
						response.status(403);
						return "Book not available";
					}
				}
				else {
					System.out.println("Libro non esiste nella biblioteca");
					response.status(401);
					return "Book doens't exists in this library";
				}
			}
			else {
				response.status(404);
				System.out.println("Utente non trovato");
				return "Utente non trovato";
			}
			
			return "end update correctly";
		
			
		});
		
		get("/prestiti/attivi", (request,response) -> {
			String cF = request.queryParams("cf");
			ObjectMapper om = new ObjectMapper();
			String query_active_loan=new String();
			if (!cF.equals("ALL")) {
				 query_active_loan= String.format("SELECT * "
						+ "FROM Prestito "
						+ "WHERE CF=\"%s\" "
						+ "AND data_consegna IS NULL;", cF);
			}
			else{
				 query_active_loan= String.format("SELECT * "
						+ "FROM Prestito;");
			}
			
			ResultSet rs = db.executeQuery(query_active_loan); 
			response.status(200);
			
			ArrayList<Prestito> l = new ArrayList<Prestito>();
			while (rs.next()) {
				l.add(new Prestito(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			System.out.println(l.toString());
			return om.writeValueAsString(l);
			
		});
		
		post("/prestito/chiudi", (request,response) ->{
			String isbn_to_close = request.queryParams("ISBN");
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();
			String date = new String(dtf.format(now).toString());
			
			String query = String.format("UPDATE Prestito "
					+ "SET data_consegna=\"%s\" "
					+ "WHERE ISBN=\"%s\";",date,isbn_to_close);
			int r = db.executeUpdate(query);
			
			String query_update = String.format(""
					+ "UPDATE Locazione "
					+ "SET disponibile='S'"
					+ "WHERE ISBN=\"%s\"; " , isbn_to_close);
			r = db.executeUpdate(query_update);
			System.out.println(r);
			response.status(200);
			return "closed";
		});
	}

	public static void main(String[] args) {
		System.out.println("Starting JDBCServer...");
		new JDBCServer().run();
	}
}
