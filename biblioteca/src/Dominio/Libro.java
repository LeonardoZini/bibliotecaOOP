package Dominio;

public class Libro {
		private String ISBN; // Univoco
		private String disponibile;
		private String nome;
		private String genere;
		private String autore;
		private String pagine;
		
		
		
		public Libro() {
			super();
			this.ISBN="None";
			this.nome="None";
			this.disponibile="S";
			this.genere="None";
			this.autore="None";
			this.pagine="";
		}

		public Libro(String iSBN, String nome, String disponibile, String genere, String autore, String pagine) {
			this.ISBN = iSBN;
			this.disponibile = disponibile;
			this.nome = nome;
			this.genere = genere;
			this.autore = autore;
			this.pagine = pagine;
		}
		
		public String getISBN() {
			return ISBN;
		}
		public void setISBN(String iSBN) {
			ISBN = iSBN;
		}
		public String getDisponibile() {
			return disponibile;
		}
		public void setDisponibile(String disponibile) {
			this.disponibile = disponibile;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getGenere() {
			return genere;
		}
		public void setGenere(String genere) {
			this.genere = genere;
		}
		public String getAutore() {
			return autore;
		}
		public void setAutore(String autore) {
			this.autore = autore;
		}
		public String getPagine() {
			return pagine;
		}
		public void setPagine(String pagine) {
			this.pagine = pagine;
		}

		@Override
		public String toString() {
			return "Libro [ISBN=" + ISBN + ", disponibile=" + disponibile + ", nome=" + nome + ", genere=" + genere
					+ ", autore=" + autore + ", pagine=" + pagine + "]";
		}
		
		
		
}
