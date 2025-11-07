package temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDB {
	public static void main(String[] args) {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QlSach;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "123456";
        
        String query = "select tensach from sach";
        
        try(Connection conn = DriverManager.getConnection(url, user, password);
        		PreparedStatement pstm = conn.prepareStatement(query);
        		) {
        	ResultSet rs = pstm.executeQuery();
        	
        	while (rs.next()) {
        		String tensach = rs.getString("tensach");
        		System.out.println("Ten sach: " + tensach);
        	}
        	
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
