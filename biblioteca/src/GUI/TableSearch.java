package GUI;
import java.awt.event.ActionEvent;
import Dominio.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;


public class TableSearch extends JFrame{

	
	private static final long serialVersionUID = 1L;
	
	String[] columnNames= {"ISBN", "Titolo","Genere","Pagine","Autore","Disponibile"};
	
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
	

	
	
	
	
	public TableSearch(ArrayList<Libro> data) {
		super("Risultati");
		
		setUndecorated(true);
		
		Font f = new Font("Default",Font.PLAIN,18);
		Font f2 = new Font("Default",Font.PLAIN,12);
		
		JPanel container = new JPanel(new BorderLayout());
		
		JButton confirm = new JButton("Prenota");
		confirm.setBackground(new java.awt.Color(253, 185, 19));
		confirm.setForeground(new java.awt.Color(69, 85, 96));
		confirm.setFont(f);
		confirm.setFocusPainted(false);
		confirm.setBorder(BorderFactory.createLineBorder(new java.awt.Color(69, 85, 96), 1));
		
		JButton close = new JButton("Esci");
		close.setBackground(new java.awt.Color(253, 185, 19));
		close.setForeground(new java.awt.Color(69, 85, 96));
		close.setFont(f);
		close.setFocusPainted(false);
		close.setBorder(BorderFactory.createLineBorder(new java.awt.Color(69, 85, 96), 1));
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
					
					Font f = new Font("Default",Font.PLAIN,15);
					Font ft = new Font("Default",Font.PLAIN,15);
					
					JPanel prenota = new JPanel(new GridLayout(3,3,0,50));
					
					prenota.setBackground(Color.white); 
					prenota.setFont(f);
					
					JButton orange = new JButton();
					orange.setBackground(new java.awt.Color(253, 185, 19));
					orange.setFocusPainted(false);
					orange.setBorderPainted(false);
					JButton orange1 = new JButton();
					orange1.setBackground(new java.awt.Color(253, 185, 19));
					orange1.setFocusPainted(false);
					orange1.setBorderPainted(false);
					JButton orange2 = new JButton();
					orange2.setBackground(new java.awt.Color(253, 185, 19));
					orange2.setFocusPainted(false);
					orange2.setBorderPainted(false);
					
					JButton esci = new JButton("Esci");
					esci.setBackground(new java.awt.Color(253, 185, 19));
					esci.setForeground(new java.awt.Color(69, 85, 96));
					esci.setFont(f);
					esci.setFocusPainted(false);
					esci.setBorderPainted(false);
					
					prenota.add(orange);
					prenota.add(orange1);
					prenota.add(orange2);
					
					prenota.add(new JLabel(""));
					JLabel testo = new JLabel("Selezionare una riga!");
					testo.setFont(ft);
					testo.setForeground(new java.awt.Color(69, 85, 96));
					prenota.add(testo);
					prenota.add(new JLabel(""));
					
					prenota.add(new JLabel(""));
					prenota.add(new JLabel(""));
					prenota.add(esci);
					
					
					
					JDialog jDialog = new JDialog();
					jDialog.setContentPane(prenota);
					
			
					jDialog.setResizable(false);
					jDialog.setSize(550,200); //JDialog size
					jDialog.setLocationRelativeTo(null);
					jDialog.setUndecorated(true);
			        jDialog.setVisible(true);
			        
			        esci.addActionListener(new ActionListener() {
					    public void actionPerformed(ActionEvent e)
					    {
					        jDialog.dispose();
					    }
					});
					
				}
				else {
					
					Font f = new Font("Default",Font.PLAIN,15);
					Font ft = new Font("Default",Font.PLAIN,16);
					
					JPanel prenota = new JPanel(new GridLayout(3,3,0,80));
					
					prenota.setBackground(Color.white); 
					prenota.setFont(f);
					
					JButton orange = new JButton();
					orange.setBackground(new java.awt.Color(253, 185, 19));
					orange.setFocusPainted(false);
					orange.setBorderPainted(false);
					JButton orange1 = new JButton();
					orange1.setBackground(new java.awt.Color(253, 185, 19));
					orange1.setFocusPainted(false);
					orange1.setBorderPainted(false);
					JButton orange2 = new JButton();
					orange2.setBackground(new java.awt.Color(253, 185, 19));
					orange2.setFocusPainted(false);
					orange2.setBorderPainted(false);
					
					JButton no = new JButton("No");
					no.setBackground(new java.awt.Color(253, 185, 19));
					no.setForeground(new java.awt.Color(69, 85, 96));
					no.setFont(f);
					no.setFocusPainted(false);
					no.setBorderPainted(false);
					
					JButton si = new JButton("Si");
					si.setBackground(new java.awt.Color(253, 185, 19));
					si.setForeground(new java.awt.Color(69, 85, 96));
					si.setFont(f);
					si.setFocusPainted(false);
					si.setBorderPainted(false);
					
					prenota.add(orange);
					prenota.add(orange1);
					prenota.add(orange2);
					
					prenota.add(new JLabel(""));
					JLabel testo = new JLabel("Vuoi prestare \""+table.getModel().getValueAt(table.getSelectedRow(), 1).toString()+"\"?");
					testo.setFont(ft);
					testo.setForeground(new java.awt.Color(69, 85, 96));
					prenota.add(testo);
					prenota.add(new JLabel(""));
					
					prenota.add(si);
					prenota.add(new JLabel(""));
					prenota.add(no);
					
					JDialog jDialog = new JDialog();
					jDialog.setContentPane(prenota);
					
			
					jDialog.setResizable(false);
					jDialog.setSize(900,300); //JDialog size
					jDialog.setLocationRelativeTo(null);
					jDialog.setUndecorated(true);
			        jDialog.setVisible(true);
			        
			        si.addActionListener(new ActionListener() {
					    public void actionPerformed(ActionEvent e)
					    {
					    	/*                                                                                        
					    	 * Si, ha confermato il prestito.                                                         
					    	 * Controllare che il libro non sia gia prestato                                          
					    	 * Aprire una nuova finestra form dove verrà chiesto il codieUtente, recuperate le        
					    	 * informazioni, stampate sul form e chiesta conferma.                                    
					    	 * Se viene confermato, quel libro risulterà in prestito da quel moemnto a quell'utente   
					    	 */      
					    	
					    	System.out.println("Processing..");    
					    }
					});
			        
			        no.addActionListener(new ActionListener() {
					    public void actionPerformed(ActionEvent e)
					    {
					    
							// Non ha confermato il prestito, nulla accade, si rimane su quella pagina
								
					    	System.out.println("Confirm aborted...");
					        jDialog.dispose();
					    }
					});
					
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
		ArrayList<Libro> data=null;
		data.add(new Libro());
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
	
		private ArrayList<Libro> rows = new ArrayList<Libro>();
		private String[] columns;
	
		public JTableButtonModel(ArrayList<Libro> data,String[] column) {
			this.rows=data;
			this.columns=column;
			
		}
	   
	   public String getColumnName(int column) {
	      return columns[column];
	   }
	   public int getRowCount() {
	      return rows.size();
	   }
	   public int getColumnCount() {
	      return columns.length;
	   }
	   public Object getValueAt(int row, int column) {
		   switch(column) {
		   case 0 : return rows.get(row).getISBN();
		   case 1 : return rows.get(row).getNome();
		   case 2 : return rows.get(row).getGenere();
		   case 3 : return rows.get(row).getPagine();
		   case 4 : return rows.get(row).getAutore(); 
		   
		   }
		   return "Null";
	      
	   }
	   public boolean isCellEditable(int row, int column) {
	      return false;
	   }
	   public Class<?> getColumnClass(int column) {
	      return getValueAt(0, column).getClass();
	   }
}
