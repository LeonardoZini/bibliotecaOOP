package Dominio;

public class Libro {
		private String ISBN; // Univoco
		private boolean isAvailable;
		private String nome;
		private String genere;
		private String autore;
		private int pagine;
		
		
		
		public Libro() {
			super();
			this.ISBN="None";
			this.nome="None";
			this.isAvailable=true;
			this.genere="None";
			this.autore="None";
			this.pagine=-1;
		}

		public Libro(String iSBN, String nome, boolean isAvailable, String genere, String autore, int pagine) {
			super();
			ISBN = iSBN;
			this.isAvailable = isAvailable;
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
		public boolean IsAvailable() {
			return isAvailable;
		}
		public void setIsAvailable(boolean isAvailable) {
			this.isAvailable = isAvailable;
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
		public int getPagine() {
			return pagine;
		}
		public void setPagine(int pagine) {
			this.pagine = pagine;
		}

		@Override
		public String toString() {
			return "Libro [ISBN=" + ISBN + ", isAvailable=" + isAvailable + ", nome=" + nome + ", genere=" + genere
					+ ", autore=" + autore + ", pagine=" + pagine + "]";
		}
		
		
		
}
