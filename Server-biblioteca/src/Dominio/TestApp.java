package Dominio;

public class TestApp {

	public static void main(String[] x) {
		
		Biblioteca b1=new Biblioteca("A.Delfini","Via delfini 39");
		
		Libro l1 = new Libro("978978978","Kafka sulla spiaggia",true,"Romanzo","Murakami",322);
		Libro l2 = new Libro("123123123","Interpretazione dei sogni",true,"Romanzo","Freud",625);
		Libro l3 = new Libro("145236356","Quanti",true,"Saggio","Feynmann",318);
		Libro l4 = new Libro("147852369","Io uccido",false,"Romanzo","Faletti",627);
		Libro l5 = new Libro("159875364","Economia e finanza",true,"Saggio","Brera",625);
		
		Utente t1 = new Utente("Leonardo","Zini","ZNILRD99L20F257Q","20/07/1999","U0001");
		Utente t2 = new Utente("Mario","Rossi","MRORSS96F29F257S","29/02/1996","U0002");
		
		Operatore o1 = new Operatore("O001",b1);
		
		b1.AddLibro(l1,"Romanzo");
		b1.AddLibro(l2,"Saggistica");
		b1.AddLibro(l3,"Fisica");
		b1.AddLibro(l4,"Romanzo");
		b1.AddLibro(l5,"Economia");
		
		o1.Prestito(t1, l1);
		
		System.out.println(l1);
		
		o1.Prestito(t2, l1);
		
		System.out.println(l1);
		o1.Consegna(t1, l1);
		System.out.println(l1);
		o1.Prestito(t2, l1);
		System.out.println(l1);

		
		
	}
}
