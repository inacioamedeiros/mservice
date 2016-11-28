package br.edu.unirn.microservices.servicos;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.edu.unirn.microservices.dominio.Curso;
import br.edu.unirn.microservices.persistencia.CursoDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CursoService {
	
	private CursoDAO dao;

	public CursoService() throws ClassNotFoundException, SQLException {
		this.dao = new CursoDAO();
	}

	@HystrixCommand(fallbackMethod = "fallListar")
	public List<Curso> listar() throws SQLException {
		return this.dao.getList();
	}

	@HystrixCommand(fallbackMethod = "fallBuscar")
	public Curso buscar(int id) throws SQLException {
		return this.dao.getPorId(id);
	}

	@HystrixCommand(fallbackMethod = "fallSalvar")
	public void salvar(Curso curso) throws SQLException {
		this.dao.salvar(curso);
	}

	@HystrixCommand(fallbackMethod = "fallDeletar")
	public void deletar(int id) throws SQLException {
		this.dao.deletar(id);
	}
	
	public List<Curso> fallListar() {
		return new ArrayList<>();
	}

	public Curso fallBuscar(int id) {
		return null;
	}

	public void fallSalvar(Curso curso) {
	}

	public void fallDeletar(int id) {
	}
}
