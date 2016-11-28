package br.edu.unirn.microservices.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unirn.microservices.dominio.Curso;

public class CursoDAO {

	private Connection conn;

	@Override
	protected void finalize() throws Throwable {
		this.conn.close();
		super.finalize();
	}

	public CursoDAO() throws ClassNotFoundException, SQLException {
		this.conn = new ConnectionFactory().getConnection();
	}

	public List<Curso> getList() throws SQLException {
		String sql = "select * from curso";
		PreparedStatement st = conn.prepareStatement(sql);
		List<Curso> cursos = new ArrayList<>();
		try {
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Curso c = new Curso();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setDuracao(rs.getString("duracao"));
				cursos.add(c);
			}
			rs.close();
			st.execute();
		} finally {
			st.close();
		}
		return cursos;
	}

	public Curso getPorId(int id) throws SQLException {
		String sql = "select * from curso where id=?";
		PreparedStatement st = conn.prepareStatement(sql);

		Curso c = null;
		try {
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				c = new Curso();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setDuracao(rs.getString("duracao"));
				continue;
			}
		} finally {
			st.close();
		}
		return c;
	}

	public void salvar(Curso Curso) throws SQLException {
		String sql = "INSERT INTO Curso (nome,duracao) VALUES (?,?)";
		PreparedStatement st = conn.prepareStatement(sql);
		try {
			st.setString(1, Curso.getNome());
			st.setString(2, Curso.getDuracao());
			st.execute();
			st.close();
		} finally {
			st.close();
		}
	}

	public void deletar(int id) throws SQLException {
		String sql = "DELETE FROM Curso WHERE id = ?";
		PreparedStatement st = conn.prepareStatement(sql);
		try {
			st.setInt(1, id);
			st.execute();
			st.close();
		} finally {
			st.close();
		}
	}

	/*
	 * public void alterCurso(Curso Curso) throws SQLException { String
	 * sql="UPDATE Curso SET nome=?,crm=? WHERE cpf=?"; PreparedStatement st =
	 * conn.prepareStatement(sql); try { st.setString(1,Curso.getNome());
	 * st.setString(2,Curso.getCrm()); st.setString(3,Curso.getCpf());
	 * st.execute(); } finally { st.close(); } }
	 * 
	 * public Curso getPorCpf(Curso Curso) throws SQLException { String
	 * sql="SELECT * FROM Curso WHERE cpf=?"; PreparedStatement st =
	 * conn.prepareStatement(sql);
	 * 
	 * Curso med = null; try { st.setString(1,Curso.getCpf()); ResultSet rs =
	 * st.executeQuery(); while(rs.next()) { med = new Curso();
	 * med.setCpf(rs.getString("cpf")); med.setNome(rs.getString("nome"));
	 * med.setCrm(rs.getString("crm")); continue; } } finally { st.close(); }
	 * return med; }
	 */
}