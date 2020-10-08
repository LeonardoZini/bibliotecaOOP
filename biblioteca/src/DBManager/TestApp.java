package DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestApp {
		public static void main(String[] args) {
		 	DBManager db;
			try {
				db = new DBManager(DBManager.JDBCDriverMySQL, DBManager.JDBCURLMySQL, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = db.executeQuery("SELECT * FROM Libro");
				rs.next();
				System.out.println(rs.getString(1) + " " + rs.getString("titolo"));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
	}
}
