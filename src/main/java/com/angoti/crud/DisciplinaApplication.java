package com.angoti.crud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DisciplinaApplication implements CommandLineRunner {

	private final JdbcTemplate jdbcTemplate;

	public DisciplinaApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(DisciplinaApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS professor (" +
				"id INT AUTO_INCREMENT PRIMARY KEY," +
				"nome VARCHAR(45) DEFAULT NULL" +
				")");

		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS disciplina (" +
				"id INT AUTO_INCREMENT PRIMARY KEY," +
				"nome VARCHAR(45) DEFAULT NULL," +
				"periodo INT DEFAULT NULL," +
				"codigo_sala_classroom VARCHAR(45) DEFAULT NULL," +
				"prof INT NOT NULL," +
				"CONSTRAINT disciplina_ibfk_1 FOREIGN KEY (prof) REFERENCES professor (id)" +
				")");
	}

}
