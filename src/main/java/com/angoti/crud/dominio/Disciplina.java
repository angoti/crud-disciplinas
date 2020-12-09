package com.angoti.crud.dominio;

public class Disciplina {
	public Integer id, periodo;
	public String nome, professor, codigo;

	public Disciplina() {
		super();
	}

	public Disciplina(Integer periodo, String nome, String professor, String codigo) {
		super();
		this.periodo = periodo;
		this.nome = nome;
		this.professor = professor;
		this.codigo = codigo;
	}

	public Disciplina(Integer id, Integer periodo, String nome, String professor, String codigo) {
		super();
		this.id = id;
		this.periodo = periodo;
		this.nome = nome;
		this.professor = professor;
		this.codigo = codigo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
