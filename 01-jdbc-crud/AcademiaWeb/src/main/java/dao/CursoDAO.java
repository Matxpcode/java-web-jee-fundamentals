package dao;

import java.util.List;

import model.Curso;

public interface CursoDAO {

	public List<Curso>listarCursos();
	public Integer insertarCursos(Curso curso);
	public Curso buscarPorId(Integer id);
	public Integer actualizarCursos(Curso curso);
	public Integer desactivarCursos(Integer id);
}
