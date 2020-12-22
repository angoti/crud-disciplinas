package com.angoti.crud.controle;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.angoti.crud.dao.DisciplinaDAO;
import com.angoti.crud.dominio.Disciplina;

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
    modelo.addAttribute("lista", lista);
    modelo.addAttribute("disciplina", new Disciplina());
    return "disciplinas";
  }

  @PostMapping("/disciplina-grava")
  public String processaForm(Disciplina disciplina) {
    if (disciplina.getId() == null) {
      dao.inserir(disciplina);
    } else {
      dao.atualizar(disciplina);
    }
    return "redirect:/disciplinas";
  }

  @GetMapping("/excluir-disciplina")
  public String excluir(@RequestParam(name = "id", required = true) Integer cod) {
    dao.excluir(cod);
    return "redirect:/disciplinas";
  }

}
