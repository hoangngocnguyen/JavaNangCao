package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class KetNoi {
	public Connection cn;
	public void ketnoi() throws Exception{
	   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	   cn= DriverManager.getConnection("jdbc:sqlserver://HOANG:1433;databaseName=QlSach;encrypt=true;trustServerCertificate=true;user=sa; password=123456");
	   System.out.println("Da ket noi");
	}
}
