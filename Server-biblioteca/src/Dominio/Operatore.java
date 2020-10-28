package Dominio;

public class Operatore extends Persona {

	private String CodOperatore;
	private Biblioteca locazione;
	
	
	
	
	public Operatore(String codOperatore, Biblioteca locazione) {
		super();
		CodOperatore = codOperatore;
		this.locazione = locazione;
	}

	public String getCodOperatore() {
		return CodOperatore;
	}

	public void setCodOperatore(String codOperatore) {
		CodOperatore = codOperatore;
	}

	public Biblioteca getLocazione() {
		return locazione;
	}

	public void setLocazione(Biblioteca locazione) {
		this.locazione = locazione;
	}

	public void Prestito(Utente t, Libro l) {
		if(!l.IsAvailable()) {
			//Libro non disponibile al prestito
			System.out.println("Libro non disponibile");
			return;
		}
		
		//Libro disponibile		
		t.addLibro_in_prestito(l);
		l.setIsAvailable(false);
				
	}
	
	public void Consegna(Utente t, Libro l) {
		if(t.getLibri_in_prestito().contains(l)) {
			t.removeLibro_in_prestito(l);
			l.setIsAvailable(true);
		}
		
		else {
			//Errore: libro non era in prestito a quell'utente
		}
	}
}
