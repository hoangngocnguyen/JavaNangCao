package HoangModal;

import java.sql.Connection;
import java.sql.DriverManager;

public class Hoang_KetNoi {
	public Connection cn;
	public void ketnoi() throws Exception{
	   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	   cn= DriverManager.getConnection("jdbc:sqlserver://HOANG:1433;databaseName=NguyenNgocHuyHoang;encrypt=true;trustServerCertificate=true;user=sa; password=123456");
	   System.out.println("Da ket noi");
	}
}
