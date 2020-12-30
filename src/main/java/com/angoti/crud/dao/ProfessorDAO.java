package com.angoti.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.angoti.crud.dominio.Professor;

@Repository
public class ProfessorDAO {
	private Connection conexao;

	public ProfessorDAO() {
		super();
		conexao = FabricaDeConexao.getConnection();
	}

	public List<Professor> todos() {
		List<Professor> listaDeProfessores = new ArrayList<Professor>();
		String sql = "select * from professor";

		try {
			PreparedStatement consulta = conexao.prepareStatement(sql);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				listaDeProfessores.add(mapRow(resultado));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaDeProfessores;
	}

	public Professor mapRow(ResultSet rs) throws SQLException {
		Professor professor = new Professor();
		professor.setNome(rs.getString("nome"));
		professor.setId(rs.getInt("id"));
		return professor;
	}
}
