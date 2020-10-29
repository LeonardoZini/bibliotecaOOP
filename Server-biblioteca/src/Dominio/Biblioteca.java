package Dominio;

import java.util.HashMap;
public class Biblioteca {
	
	private String nome;
	private String locazione;
	private HashMap<Libro,String> elenco_libri = new HashMap<Libro,String>(); //Libro in reparto
	
	public Biblioteca() {
		this.nome="None";
		this.locazione="None";
	}
	
	
	public Biblioteca(String nome, String locazione) {
		super();
		this.nome = nome;
		this.locazione = locazione;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocazione() {
		return locazione;
	}

	public void setLocazione(String locazione) {
		this.locazione = locazione;
	}
	
	public void AddLibro(Libro l,String reparto) {
		elenco_libri.put(l, reparto);
	}
	
	public String RicercaReparto(Libro L) {
		return this.elenco_libri.get(L);
	}
}
