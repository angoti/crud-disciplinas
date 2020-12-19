package com.angoti.crud.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.angoti.crud.dao.DisciplinaDAO;
import com.angoti.crud.dominio.Disciplina;

import java.util.List;

@Controller
public class DisciplinaControle {
	
	DisciplinaDAO dao;

	public DisciplinaControle() {
		super();
		this.dao = new DisciplinaDAO();
	}


	@GetMapping("/disciplinas")
	public String disciplinasTabela(Model modelo) {
		List<Disciplina> lista = dao.todos();
		modelo.addAttribute("lista",lista);
		return "disciplinas"; 
	}

	@GetMapping("/disciplina-form")
	public String exibeForm(Model model) {
		model.addAttribute("disciplina", new Disciplina());
		return "disciplina-form";
	}
	
	@PostMapping("/disciplina-form")
	public String processaForm(Disciplina disciplina) {
		dao.inserir(disciplina);
		return "redirect:/disciplinas";
	}
}
