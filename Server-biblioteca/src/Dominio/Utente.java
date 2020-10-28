package Dominio;
import java.util.*;

public class Utente extends Persona {
	
	private String codice;
	private int num_prestiti_attivi; //MAX 3
	private List<Libro> libri_in_prestito;
	
	public Utente() {
		super();
		this.codice="None";
		this.num_prestiti_attivi=0;
		libri_in_prestito = new ArrayList<Libro>();
		
	}
	
	public Utente(String codice) {
		super();
		this.codice=codice;
		this.num_prestiti_attivi=0;
		libri_in_prestito = new ArrayList<Libro>();
	}
	
	

	public Utente(String nome, String cognome, String cF, String data_nascita,String codice) {
		super(nome, cognome, cF, data_nascita);
		// TODO Auto-generated constructor stub
		this.codice=codice;
		this.num_prestiti_attivi=0;
		libri_in_prestito = new ArrayList<Libro>();
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public List<Libro> getLibri_in_prestito() {
		return libri_in_prestito;
	}

	public void addLibro_in_prestito(Libro L) {
		if(this.num_prestiti_attivi<3) {
			this.libri_in_prestito.add(L);
			this.num_prestiti_attivi++;
		}
		else {
			//Numero massimo di libri in prestito raggiunto
		}
		
	}
	
	public void removeLibro_in_prestito(Libro L) {
		this.libri_in_prestito.remove(L);
		this.num_prestiti_attivi--;
	}

	public int getNum_prestiti_attivi() {
		return num_prestiti_attivi;
	}

	public void setNum_prestiti_attivi(int num_prestiti_attivi) {
		this.num_prestiti_attivi = num_prestiti_attivi;
	}
	
	

}
