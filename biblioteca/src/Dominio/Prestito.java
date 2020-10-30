package Dominio;

public class Prestito {

	private String isbn;
	private String cf;
	private String codOp;
	private String data_prestito;
	private String data_consegna;
	
	
	public Prestito(String isbn, String cf, String codOp, String data_prestito, String data_consegna) {
		super();
		this.isbn = isbn;
		this.cf = cf;
		this.codOp = codOp;
		this.data_prestito = data_prestito;
		this.data_consegna = data_consegna;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getCodOp() {
		return codOp;
	}

	public void setCodOp(String codOp) {
		this.codOp = codOp;
	}

	public String getData_prestito() {
		return data_prestito;
	}

	public void setData_prestito(String data_prestito) {
		this.data_prestito = data_prestito;
	}

	public String getData_consegna() {
		return data_consegna;
	}

	public void setData_consegna(String data_consegna) {
		this.data_consegna = data_consegna;
	}

	public Prestito() {
		super();
	}
	
	
	
}
