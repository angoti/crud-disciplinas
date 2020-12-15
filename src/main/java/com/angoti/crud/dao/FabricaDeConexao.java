package com.angoti.crud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao {

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://us-cdbr-east-02.cleardb.com/heroku_29233e2d8f9b041?reconnect=true","b65f349e12df59","ccf37ce4");
//			return DriverManager.getConnection("mysql://b65f349e12df59:ccf37ce4@us-cdbr-east-02.cleardb.com/heroku_29233e2d8f9b041?reconnect=true");
//			return DriverManager.getConnection("jdbc:mysql://localhost/sistema4?serverTimezone=UTC", "root", "");
		} catch (SQLException e) {
			System.out.println("---------------------> " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
