package com.angoti.crud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//implementa o design pattern Factory
public class FabricaDeConexao {

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/crud0?serverTimezone=UTC", "root", "");
		} catch (SQLException e) {
			System.out.println("---------------> " + e.getMessage());
			return null;
		}
	}
}
