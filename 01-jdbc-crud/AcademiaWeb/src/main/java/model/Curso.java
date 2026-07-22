package model;

public class Curso {

	private Integer id;
	private String nombre;
	private String nivel;
	private Double precio;
	private Boolean activo;
	
	public Curso() {}
	
	public Curso(Integer id, String nombre, String nivel, Double precio, Boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.nivel = nivel;
		this.precio = precio;
		this.activo = activo;
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

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
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
	
	
}
