package DBMS;
import Dominio.*;
import static spark.Spark.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

			query = String.format("SELECT * FROM Libro");
			ResultSet rs = db.executeQuery(query);
			
			ArrayList<Libro> l = new ArrayList<Libro>();
			while (rs.next()) {
				l.add(new Libro(rs.getString("ISBN"),rs.getString("titolo"),true,rs.getString("genere"),rs.getString("autore"),rs.getInt("pagine")));
			}
			response.status(200);
			return om.writeValueAsString(l);
		});
		
		/*
		 * Ottenere ilibri con l'isbn desiderato
		 */
		get("/Libro/byISBN", (request, response) -> {
			String query;

			
			query = String.format("SELECT * FROM Libro where ISBN=\"%s\";",request.queryParams("ISBN"));
			
			ResultSet rs = db.executeQuery(query);
			ArrayList<Libro> l = new ArrayList<Libro>();
			while (rs.next()) {
				l.add(new Libro(rs.getString("ISBN"),rs.getString("titolo"),true,rs.getString("genere"),rs.getString("autore"),rs.getInt("pagine")));
			}
			response.status(200);
			return om.writeValueAsString(l);
		});
		/*
		 * Ottenere libri con il titolo desiderato
		 */
		get("/Libro/byTitolo", (request, response) -> {
			String query;

			
			query = String.format("SELECT * FROM Libro where titolo LIKE \"%s\";",request.queryParams("titolo").replace("-", " "));
			ResultSet rs = db.executeQuery(query);

			ArrayList<Libro> l = new ArrayList<Libro>();
			while (rs.next()) {
				l.add(new Libro(rs.getString("ISBN"),rs.getString("titolo"),true,rs.getString("genere"),rs.getString("autore"),rs.getInt("pagine")));
			}
			return om.writeValueAsString(l);
		});
		
		/*
		 * ottenere i prestiti del genere desiderato
		 */
		get("/Libro/byGenere", (request, response) -> {
			String query;

			
			query = String.format("SELECT * FROM Libro where genere LIKE \"%s\";",request.queryParams("genere").replace("-", " "));
			ResultSet rs = db.executeQuery(query);

			ArrayList<Libro> l = new ArrayList<Libro>();
			while (rs.next()) {
				l.add(new Libro(rs.getString("ISBN"),rs.getString("titolo"),true,rs.getString("genere"),rs.getString("autore"),rs.getInt("pagine")));
			}
			return om.writeValueAsString(l);
		});
		
		/*
		 * Ottenere i libri dell'autore desiderato
		 */
		get("/Libro/byAutore", (request, response) -> {
			String query;

			
			query = String.format("SELECT * FROM Libro where autore LIKE \"%s\";",request.queryParams("autore").replace("-", " "));
			ResultSet rs = db.executeQuery(query);

			ArrayList<Libro> l = new ArrayList<Libro>();
			while (rs.next()) {
				l.add(new Libro(rs.getString("ISBN"),rs.getString("titolo"),true,rs.getString("genere"),rs.getString("autore"),rs.getInt("pagine")));
			}
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
			String isbn = request.queryParams("ISBN");
			String titolo = request.queryParams("titolo");
			String genere = request.queryParams("genere");
			String autore = request.queryParams("autore");
			String pagine = request.queryParams("pagine");
			try {
				String query=String.format("INSERT INTO Libro(ISBN,titolo,genere,autore,pagine) VALUES"
						+ "(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");",isbn,titolo,genere,autore,pagine);
				System.out.println(query);
				db.executeUpdate(query);
				response.status(200);
			}
			catch(Exception e1) {
				response.status(403);
				System.out.println("Errore");
				return "403";
			}
			
			
			
			return "200";
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

			try {
				String query=String.format("SELECT * FROM Utente U JOIN"
						+ "Prestito P WHERE P.CF=U.CF and U.idTelegram=\"%s\";",idTelegram);
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
		 * API per registrare al servizio telegram un utente della biblioteca
		 */
		
		get("/telegram/registrazione", (request, response) -> {
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
		
	}

	public static void main(String[] args) {
		System.out.println("Starting JDBCServer...");
		new JDBCServer().run();
	}
}