package com.angoti.crud.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.angoti.crud.dao.DisciplinaDAO;
import com.angoti.crud.dominio.Disciplina;

import java.util.List;

@Controller
public class DisciplinaControle {

	@Autowired
	DisciplinaDAO dao;

	@GetMapping("/disciplinas")
	public String disciplinasTabela(Model modelo) {
		List<Disciplina> lista = dao.todos();
		modelo.addAttribute("lista", lista);
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

	@GetMapping("/editar-disciplina")
	public String exibeFormEdit(Model model, @RequestParam(name = "id", required = false) Integer cod){
		Disciplina disciplina = dao.buscaPorId(cod);
		model.addAttribute("disciplina", disciplina);
		return "disciplina-form";
	}

	@PostMapping("/editar-disciplina")
	public String processaoFormEdit(Disciplina disciplina) {
		dao.atualizar(disciplina);
		return "redirect:/disciplinas";
	}
	
	@GetMapping("/excluir-disciplina")
	public String excluir(@RequestParam(name = "id", required = true) Integer cod) {
		dao.excluir(cod);
		return "redirect:/disciplinas";
	}
}
