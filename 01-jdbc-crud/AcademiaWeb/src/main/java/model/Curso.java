package model;

public class Curso {

	private Integer id;
	private String nombre;
	private Character nivel;
	private Double precio;
	private Boolean activo;
	
	//Foreign Key (conexion)(composicion de objetos)
	private Profesor profesor;
	
	public Curso() {}

	public Curso(Integer id, String nombre, Character nivel, Double precio, Boolean activo, Profesor profesor) {
		this.id = id;
		this.nombre = nombre;
		this.nivel = nivel;
		this.precio = precio;
		this.activo = activo;
		this.profesor = profesor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Character getNivel() {
		return nivel;
	}

	public void setNivel(Character nivel) {
		this.nivel = nivel;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
}
