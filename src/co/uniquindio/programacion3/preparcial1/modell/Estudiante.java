package co.uniquindio.programacion3.preparcial1.modell;

public class Estudiante {

	private String nombre;
	private String codigo;
	private double nota1;
	private double nota2;
	private double nota3;

	// Metodo contructor
	public Estudiante(String nombre, String codigo, double nota1, double nota2, double nota3) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.nota3 = nota3;
	}

	// Metodo constructor vacio
	public Estudiante() {
		super();
	}

	// Metodo toString
	@Override
	public String toString() {
		return "Estudiante [nombre=" + nombre + ", codigo=" + codigo + ", nota1=" + nota1 + ", nota2=" + nota2
				+ ", nota3=" + nota3 + "]";
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

	public double getNota1() {
		return nota1;
	}

	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}

	public double getNota2() {
		return nota2;
	}

	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}

	public double getNota3() {
		return nota3;
	}

	public void setNota3(double nota3) {
		this.nota3 = nota3;
	}

}
