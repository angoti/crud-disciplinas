package com.angoti.crud.dominio;

public class Professor {
	private String nome;
	private Integer id;

	public Professor(String nome, Integer id) {
		super();
		this.nome = nome;
		this.id = id;
	}

	public Professor() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
