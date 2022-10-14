package co.uniquindio.programacion3.preparcial1.modell;

public class Estudiante {

	private String nombre;
	private String codigo;
	private double notas;

	// Metodo contructor
	public Estudiante(String nombre, String codigo, double notas) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.notas = notas;
	}

	//Metodo constructor vacio
	public Estudiante() {
		super();
	}


	// Metodo toString
	@Override
	public String toString() {
		return "Estudiante [nombre=" + nombre + ", codigo=" + codigo + ", notas=" + notas + "]";
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

	public double getNotas() {
		return notas;
	}

	public void setNotas(double notas) {
		this.notas = notas;
	}

}
