package GUI;
import Dominio.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.fasterxml.jackson.databind.ObjectMapper;

import Dominio.Libro;

public class LoanTable extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] columnNames= {"ISBN", "CF","idOperatore","Data inizio prestito","Data Consegna"};
	
	public LoanTable(String cF) {
		super("Prestiti di: "+cF);
		

		/*
		 * Carico i prestiti ATTIVI di cf
		 */
		
		ArrayList<Prestito>  data =new ArrayList<Prestito> ();
		
		try {
			URL url  = new URL(String.format("http://2.224.243.66:8080/prestiti/attivi?cf=%s",cF));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			ObjectMapper om = new ObjectMapper();
			
			
			Prestito[] prest = om.readValue(read.readLine(),Prestito[].class);
			data = new ArrayList<Prestito>(Arrays.asList(prest));
			connection.disconnect();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("new table with: "+data.size());
		
	
		
		
		Font f = new Font("Default",Font.PLAIN,18);
		Font f2 = new Font("Default",Font.PLAIN,12);
		
		JPanel container = new JPanel(new BorderLayout());
		
		JButton confirm = new JButton("Chiudi Prestito");
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
		
		
		JTable table = new JTable(new JTableButtonModel2(data, columnNames));
		
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
				/*
				 * TODO prestito confirm
				 */
				
				
				String isbn_to_close = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				URL url;
				try {
					url = new URL(String.format("http://2.224.243.66:8080/prestito/chiudi?"
							+ "ISBN=%s",isbn_to_close));
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("POST");
					BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					System.out.println(read.readLine());
					connection.disconnect();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(rootPane, "Prestito chiuso");
				
				
			}
		});
		
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
				
		new LoanTable("ZNILRD99L20F257Q");
	}


}

class JTableButtonRenderer2 implements TableCellRenderer {
	   private TableCellRenderer defaultRenderer;
	   public JTableButtonRenderer2(TableCellRenderer renderer) {
	      defaultRenderer = renderer;
	   }
	   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	      if(value instanceof Component)
	         return (Component)value;
	         return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	   }
}

class JTableButtonModel2 extends AbstractTableModel {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private ArrayList<Prestito>  rows = new ArrayList<Prestito> ();
		private String[] columns;
	
		public JTableButtonModel2(ArrayList<Prestito>  data,String[] column) {
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
		   case 0 : return rows.get(row).getIsbn();
		   case 1 : return rows.get(row).getCf();
		   case 2 : return rows.get(row).getCodOp();
		   case 3 : return rows.get(row).getData_prestito();
		   case 4 : return rows.get(row).getData_consegna()	;
		   
		   }
		   return "Null";
	      
	   }
	   public boolean isCellEditable(int row, int column) {
	      return false;
	   }
	   public Class<? extends Object> getColumnClass(int column) {
	      return String.class;
	   }
}


