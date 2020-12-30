package com.angoti.crud.controle;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.angoti.crud.dao.DisciplinaDAO;
import com.angoti.crud.dao.ProfessorDAO;
import com.angoti.crud.dominio.Disciplina;
import com.angoti.crud.dominio.Professor;

@Controller
public class DisciplinaControle {

	DisciplinaDAO disciplinaDAO;
	ProfessorDAO professorDAO;

	public DisciplinaControle() {
		super();
		disciplinaDAO = new DisciplinaDAO();
		professorDAO = new ProfessorDAO();
	}

	@GetMapping("/disciplinas")
	public String disciplinasTabela(Model modelo) {
		List<Disciplina> lista = disciplinaDAO.todos();
		modelo.addAttribute("lista", lista);
		return "disciplinas";
	}

	@GetMapping("/disciplina-form")
	public String exibeForm(Model model) {
		model.addAttribute("disciplina", new Disciplina());
		model.addAttribute("listaDeProfessores",professorDAO.todos());
		return "disciplina-form";
	}

	@PostMapping("/disciplina-form")
	public String processaForm(Disciplina disciplina) {
		disciplinaDAO.inserir(disciplina);
		return "redirect:/disciplinas";
	}

	@GetMapping("/excluir-disciplina")
	public String excluirDisciplina(@RequestParam(name="id") int id) {
		disciplinaDAO.excluir(id);
		return "redirect:/disciplinas";
	}

}
