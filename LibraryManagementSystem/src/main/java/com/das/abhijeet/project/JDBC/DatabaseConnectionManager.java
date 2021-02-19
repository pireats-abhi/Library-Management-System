package com.das.abhijeet.project.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
	private final String url;
	private final Properties properties;
	private final String host = "localhost";
	private final String databaseName = "library_management_system";
	private final String username = "root";
	private final String password = "password";
	
	public DatabaseConnectionManager() {
		this.url = "jdbc:mysql://" + host + "/" + databaseName;
		this.properties = new Properties();
		this.properties.setProperty("user", username);
		this.properties.setProperty("password", password);
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.url, this.properties);
	}
	
}
