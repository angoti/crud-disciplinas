package com.angoti.crud.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.angoti.crud.dominio.Professor;

@Repository
public class ProfessorDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private class ProfessorRowMapper implements RowMapper<Professor> {

		@Override
		public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {
			Professor professor = new Professor();
			professor.setNome(rs.getString("nome"));
			professor.setId(rs.getInt("id"));
			return professor;
		}
	}

	public List<Professor> todos() {
		return jdbcTemplate.query("select * from professor", new ProfessorRowMapper());
	}

	public void inserir(Professor professor) {
		String sql = "insert into professor(nome) values (?);";
		Object[] params = new Object[] { professor.getNome() };
		jdbcTemplate.update(sql, params);
	}

	public Professor buscaPorId(Integer cod) {
		return jdbcTemplate.queryForObject("select * from professor where id=" + cod, new ProfessorRowMapper());
	}

	public void atualizar(Professor professor) {
		String sql = "update professor set nome=? where id = ?;";
		Object[] params = new Object[] { professor.getNome(), professor.getId() };
		jdbcTemplate.update(sql, params);
	}

	public void excluir(Integer cod) {
		String sql = "delete from professor where id = ?;";
		Object[] params = new Object[] { cod };
		jdbcTemplate.update(sql, params);
	}
}
