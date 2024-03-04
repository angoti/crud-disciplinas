package com.angoti.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DisciplinaApplication {

	@Autowired
	private static JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DisciplinaApplication.class, args);

		// Execute os comandos SQL para criar as tabelas
		jdbcTemplate.execute("CREATE TABLE professor (" +
				"id INT AUTO_INCREMENT PRIMARY KEY," +
				"nome VARCHAR(45) DEFAULT NULL" +
				") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");

		jdbcTemplate.execute("CREATE TABLE disciplina (" +
				"id INT AUTO_INCREMENT PRIMARY KEY," +
				"nome VARCHAR(45) DEFAULT NULL," +
				"periodo INT DEFAULT NULL," +
				"codigo_sala_classroom VARCHAR(45) DEFAULT NULL," +
				"prof INT NOT NULL," +
				"KEY disciplina_ibfk_1 (prof)," +
				"CONSTRAINT disciplina_ibfk_1 FOREIGN KEY (prof) REFERENCES professor (id)" +
				") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");

	}

}
