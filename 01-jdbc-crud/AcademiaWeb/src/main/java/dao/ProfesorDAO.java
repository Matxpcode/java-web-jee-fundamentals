package dao;

import java.util.List;

import model.Profesor;

public interface ProfesorDAO {

	//Metodo para listar profesores
	public List<Profesor> findAll();
	
	//Metodo para insertar profesores
	public Integer insert(Profesor profesor);

	//Metodo para buscar profesores por id
	public Profesor findById(Integer id);
	
	//Metodo para actualizar profesor
	public Integer update(Profesor profesor);
	
	//Metodo para eliminar profesor
	public Integer delete(Integer id);
}
