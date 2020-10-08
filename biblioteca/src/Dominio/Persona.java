package Dominio;


//TODO
/*
 * 1. Convert data_nascita in Date
 */

class Persona {

	private String nome;
	private String cognome;
	private String CF; //Unico
	private String data_nascita;
	
	public Persona() {
		this.cognome="None";
		this.nome="None";
		this.CF="None";
		this.data_nascita="01/01/1970";
	}
	public Persona(String nome, String cognome, String cF, String data_nascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.CF = cF;
		this.data_nascita = data_nascita;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCF() {
		return CF;
	}

	public void setCF(String cF) {
		this.CF = cF;
	}

	public String getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(String data_nascita) {
		this.data_nascita = data_nascita;
	}
	
	
}
