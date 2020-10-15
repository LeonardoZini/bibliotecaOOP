package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;


public class TableSearch extends JFrame{

	
	private static final long serialVersionUID = 1L;
	
	String[] columnNames= {"ISBN", "Titolo","Genere","Pagine","Autore"};
	
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
		
		setUndecorated(true);
		
		Font f = new Font("Default",Font.PLAIN,18);
		Font f2 = new Font("Default",Font.PLAIN,12);
		
		JPanel container = new JPanel(new BorderLayout());
		
		JButton confirm = new JButton("Prenota");
		confirm.setBackground(new java.awt.Color(253, 185, 19));
		confirm.setForeground(new java.awt.Color(69, 85, 96));
		confirm.setFont(f);
		
		JButton close = new JButton("Close");
		close.setBackground(new java.awt.Color(253, 185, 19));
		close.setForeground(new java.awt.Color(69, 85, 96));
		close.setFont(f);
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*Close windows*/
				dispose();
			}
		});
		
		
		/*
		 * L&F table
		 */
		
		JTable table = new JTable(new JTableButtonModel(data, columnNames));
		
		TableCellRenderer tableRenderer;
		tableRenderer = table.getDefaultRenderer(JButton.class);		
		table.setRowHeight(20);
		table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setRowMargin(5);
		table.setEnabled(true);
		table.setSelectionBackground(new java.awt.Color(253, 185, 19));
		
		container.setLayout(new BorderLayout());
		container.add(table.getTableHeader(), BorderLayout.PAGE_START);
		container.add(scrollPane, BorderLayout.CENTER);
		
		JPanel p2 = new JPanel(new BorderLayout());
		
		confirm.setEnabled(true);
		confirm.addActionListener(new ActionListener(){  
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(table.getSelectedRow()<0) {
					JOptionPane.showMessageDialog(null, "Mannaggia la puttana seleziona una riga");
					
				}
				else {
					
					int value;
					value=JOptionPane.showConfirmDialog(null, "Vuoi prestare \""+table.getModel().getValueAt(table.getSelectedRow(), 1).toString()+"\"?"
							,"Conferma", JOptionPane.YES_NO_OPTION);
					if(value == 0) {
						/*
						 * Si, ha confermato il prestito.
						 * Controllare che il libro non sia gia prestato
						 * Aprire una nuova finestra form dove verrà chiesto il codieUtente, recuperate le
						 * informazioni, stampate sul form e chiesta conferma.
						 * Se viene confermato, quel libro risulterà in prestito da quel moemnto a quell'utente
						 */
						System.out.println("Processing..");
					}
					else {
						/*
						 * Non ha confermato il prestito, nulla accade, si rimane su quella pagina
						 */
						System.out.println("Confirm aborted...");
					}
					
				}
			}
		} );
		JPanel p0 = new JPanel(new GridLayout(3,1));
		p0.add(new JLabel(""));
		p0.add(new JLabel(""));
		p0.add(new JLabel(""));
		
		
		
		JPanel p1 = new JPanel(new GridLayout(1,5));
		p1.add(new JLabel(""));
		p1.add(confirm);
		p1.add(new JLabel(""));
		p1.add(close);
		p1.add(new JLabel(""));
		p1.setBackground(Color.WHITE);
		
		p2.add(p0,BorderLayout.PAGE_START);
		p2.add(container, BorderLayout.CENTER);
		p2.add(p1,BorderLayout.PAGE_END);
		
		setContentPane(p2);
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setResizable(false);
		
	}
	
		
	
	public static void main(String[] args) {
		Object[][] data = {{"123456789","Test1","Test2","Test3","Test4",new JButton("TEST")}};
		new TableSearch(data);		
		
	}


}

class JTableButtonRenderer implements TableCellRenderer {
	   private TableCellRenderer defaultRenderer;
	   public JTableButtonRenderer(TableCellRenderer renderer) {
	      defaultRenderer = renderer;
	   }
	   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	      if(value instanceof Component)
	         return (Component)value;
	         return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	   }
}

class JTableButtonModel extends AbstractTableModel {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private Object[][] rows;
		private String[] columns;
	
		public JTableButtonModel(Object[][] data,String[] column) {
			this.rows=data;
			this.columns=column;
			
		}
	   
	   public String getColumnName(int column) {
	      return columns[column];
	   }
	   public int getRowCount() {
	      return rows.length;
	   }
	   public int getColumnCount() {
	      return columns.length;
	   }
	   public Object getValueAt(int row, int column) {
	      return rows[row][column];
	   }
	   public boolean isCellEditable(int row, int column) {
	      return false;
	   }
	   public Class<?> getColumnClass(int column) {
	      return getValueAt(0, column).getClass();
	   }
}
