package br.com.airamcruz.projeto.integrador.util.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import br.com.airamcruz.projeto.integrador.util.PropertiesUtil;

public class DatabaseFactory {
		
	public static Connection getConnection() {
		
		Connection conn = null;
		
		Properties prop = PropertiesUtil.loadProperties("sql");
		
		String url = "jdbc:mysql://localhost:3306/" + prop.getProperty("db.name") + "?useSSL=false";
		String user = prop.getProperty("db.user");
		String password = prop.getProperty("db.password");

		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel realizar a conexão com o bando de dados!");
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnection(Connection con) {
		Connection conn = con;
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
