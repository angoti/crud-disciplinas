package com.angoti.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angoti.crud.dominio.Disciplina;
import com.angoti.crud.dominio.Professor;

@Repository
public class DisciplinaDAO {

	public void inserir(Disciplina disciplina) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "insert into disciplina" + "(nome,prof,periodo,codigo_sala_classroom) values (?,?,?,?)";

		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, disciplina.getNome());
			stmt.setInt(2, disciplina.getProfessor().getId());
			stmt.setInt(3, disciplina.getPeriodo());
			stmt.setString(4, disciplina.getCodigo());
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Disciplina> todos() {
		List<Disciplina> lista = new ArrayList<Disciplina>();
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select "
				+		"d.nome as disciplina_nome, "
				+ 		"d.id as disciplina_id, "
				+ 		"d.codigo_sala_classroom as disciplina_cod_class, "
				+ 		"d.prof as disciplina_professor_id, "
				+ 		"d.periodo as disciplina_periodo, "
				+ 		"p.nome as professor_nome, "
				+ 		"p.id as professor_id "
				+ "from professor p, disciplina d "
				+ "where d.prof=p.id;";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet retorno = stmt.executeQuery();

			while (retorno.next()) {
				int idDisciplina = retorno.getInt("disciplina_id");
				String nomeDisciplina = retorno.getString("disciplina_nome");
				int periodo = retorno.getInt("disciplina_periodo");
				String cod = retorno.getString("disciplina_cod_class");
				int idProfessor = retorno.getInt("professor_id");
				String nomeProfessor = retorno.getString("professor_nome");
				Disciplina disciplina = new Disciplina(idDisciplina, periodo, nomeDisciplina,
						new Professor(nomeProfessor, idProfessor), cod);
				lista.add(disciplina);
			}
			retorno.close();
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			System.out.println("------> " + e.getMessage());
			e.printStackTrace();
		}
		return lista;
	}

	public void excluir(int id) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "delete from disciplina where id = ?";

		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void atualizar(Disciplina disciplina) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "update disciplina set nome=?,prof=?,periodo=?,codigo_sala_classroom=?" + " where id = ?";

		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, disciplina.getNome());
			stmt.setInt(2, disciplina.getProfessor().getId());
			stmt.setInt(3, disciplina.getPeriodo());
			stmt.setString(4, disciplina.getCodigo());
			stmt.setInt(5, disciplina.getId());
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Disciplina buscaPorId(int id) {
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select * from disciplina,professor where disciplina.id = ? and professor.id=disciplina.prof;";
		Disciplina disciplina = null;

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet retorno = stmt.executeQuery();
			retorno.next();
			String nomeDisciplina = retorno.getString("nome");
			int periodo = retorno.getInt("periodo");
			Professor professor = new Professor(retorno.getString("professor.nome"), retorno.getInt("professor.id"));
			String cod = retorno.getString("codigo_sala_classroom");
			disciplina = new Disciplina(id, periodo, nomeDisciplina, professor, cod);
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return disciplina;
	}
}
