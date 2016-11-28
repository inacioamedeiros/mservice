package br.edu.unirn.microservices.servicos;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {
	
	@RequestMapping("/")
	public ModelAndView mostrar() {		
		ModelAndView mv = new ModelAndView("Principal");
		return mv;
	}
}
