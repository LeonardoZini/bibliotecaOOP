package GUI;
import java.awt.BorderLayout;

import javax.swing.*;


public class TableSearch extends JFrame{

	
	private static final long serialVersionUID = 1L;
	
	String[] columnNames= {"ISBN", "Titolo","Genere","Pagine","Autore",};
	
	/*
	 * 
	 * Usato per fare i test. L'oggetto data andrà costruito al momento della query al DB, e passato
	 * al costruttore. Non sarà possibile aprire questo frame senza passare un oggetto [][].
	 * I controlli poi su questo oggetto verranno fatti qui, per poter printare eventuali errore del db.
	 * Ad esempio se la query non da risultati.
	 * 
	 * Va inoltre se si riesce abbellita e inserite le immagini/bottoni per eventuali prestiti o consegne, inserite in un altr
	 * colonna
	 * 
	 */
	

	
	
	
	
	public TableSearch(Object[][] data) {
		super("Risultati");
		
		
		JPanel container = new JPanel(new BorderLayout());
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		container.setLayout(new BorderLayout());
		container.add(table.getTableHeader(), BorderLayout.PAGE_START);
		container.add(scrollPane, BorderLayout.CENTER);
		
		this.add(container);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
	}
	
	
	
	public static void main(String[] args) {
		Object[][] data = {{"123456789","Test1","Test2","Test3","Test4","test5"}};
		new TableSearch(data);
		
		
	}
}
