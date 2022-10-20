package co.uniquindio.programacion3.preparcial1.modell;

public class Programas {

	private String nombre;
	private String codigo;
	private String modalidad;

	// Metodo constructor
	public Programas(String nombre, String codigo, String modalidad) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.modalidad = modalidad;
	}

	// Metodo constructor vacio
	public Programas() {
		super();
	}

	// Metodo toString
	@Override
	public String toString() {
		return "Programas [nombre=" + nombre + ", codigo=" + codigo + ", modalidad=" + modalidad + "]";
	}

	// Metodos getters and setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

}
