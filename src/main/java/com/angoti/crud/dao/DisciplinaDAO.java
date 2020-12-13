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

@Repository
public class DisciplinaDAO {

	public void inserir(Disciplina disciplina) {
		Connection conexao = FabricaDeConexao.getConnection();
		PreparedStatement stmt;
		String sql = "insert into disciplina" + 
				"(nome,professor,periodo,codigo_sala_classroom) values (?,?,?,?)";

		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, disciplina.getNome());
			stmt.setString(2, disciplina.getProfessor());
			stmt.setInt(3, disciplina.getPeriodo());
			stmt.setString(4, disciplina.getCodigo());
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Disciplina> todos() {
		List<Disciplina> lista = new ArrayList<Disciplina>();
		Connection conexao = FabricaDeConexao.getConnection();
		String sql = "select * from disciplina;";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet retorno = stmt.executeQuery();

			while (retorno.next()) {
				int id = retorno.getInt("id");
				String nomeDisciplina = retorno.getString("nome");
				int periodo = retorno.getInt("periodo");
				String professor = retorno.getString("professor");
				String cod = retorno.getString("codigo_sala_classroom");
				Disciplina disciplina = new Disciplina(id, periodo, nomeDisciplina, professor, cod);
				lista.add(disciplina);
			}
			retorno.close();
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
		String sql = "update disciplina set nome=?,professor=?,periodo=?,codigo_sala_classroom=?" + " where id = ?";
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, disciplina.getNome());
			stmt.setString(2, disciplina.getProfessor());
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
		String sql = "select * from disciplina where id = ?;";
		Disciplina disciplina = null;

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet retorno = stmt.executeQuery();
			retorno.next();
			String nomeDisciplina = retorno.getString("nome");
			int periodo = retorno.getInt("periodo");
			String professor = retorno.getString("professor");
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
