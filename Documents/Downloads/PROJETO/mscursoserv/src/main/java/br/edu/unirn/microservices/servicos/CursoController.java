package br.edu.unirn.microservices.servicos;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.inject.internal.Errors;

import br.edu.unirn.microservices.dominio.Curso;

@Controller
@EnableCircuitBreaker
public class CursoController {
	
	@Autowired
	private CursoService service;

	@RequestMapping("/cursos")
	public ModelAndView listar() throws SQLException {
		ModelAndView mv = new ModelAndView("listarCursos");
		mv.addObject("cursos", service.listar());
		mv.addObject(new Curso());
		return mv;
	}
	
	@RequestMapping(value = "/cursos", method = RequestMethod.POST)
	public String salvar(@Validated Curso curso, Errors errors) throws SQLException {
		
		if ( errors.hasErrors() ) {
			return "redirect:/cursos";
		}
		
		this.service.salvar(curso);
		
		return "redirect:/cursos";
	}
	
	@RequestMapping("/excluircurso/{id}")
	public String excluir(@PathVariable int id) throws SQLException {
		
		this.service.deletar(id);
		
		return "redirect:/cursos";
	}
	
	@RequestMapping("/editarcurso/{curso}")
	public ModelAndView editar(@PathVariable Curso curso) {
		
		ModelAndView mv = new ModelAndView("editarCurso");
		
		mv.addObject(curso);
		
		return mv;
	}
	
	@RequestMapping("/alterarcurso")
	public String editaralterar(@Validated Curso curso) throws SQLException {
		
		Curso cursonew = curso;

		this.service.deletar(curso.getId());

		this.service.salvar(cursonew);

		return "redirect:/cursos";
	}
}
