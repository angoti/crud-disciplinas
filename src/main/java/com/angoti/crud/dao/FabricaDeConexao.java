package com.angoti.crud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao {

	private Connection conexao;
	private static volatile FabricaDeConexao instanciaUnica;

	private FabricaDeConexao() {
		try {
			// Configuração para usar o banco de dados H2 em memória
			conexao = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
			// Você pode ajustar o nome do banco de dados (testdb) e o usuário (sa) conforme
			// necessário.
		} catch (SQLException e) {
			System.out.println("---------------------> " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		synchronized (FabricaDeConexao.class) {
			if (instanciaUnica == null) {
				System.out.println(FabricaDeConexao.class.getName() + ": criando nova conexão de BD");
				instanciaUnica = new FabricaDeConexao();
			}
		}
		return instanciaUnica.conexao;
	}
}
