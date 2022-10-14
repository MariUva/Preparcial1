package co.uniquindio.programacion3.preparcial1.modell;

import java.util.ArrayList;

public class Colegio {

	//Atributos de la clase
	ArrayList<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();

	// Metodo constructor

	public Colegio(ArrayList<Estudiante> listaEstudiantes) {
		super();
		this.listaEstudiantes = listaEstudiantes;
	}

	// Metodo contructor vacio
	public Colegio() {
		super();
	}

	// Metodo toString
	@Override
	public String toString() {
		return "Colegio [listaEstudiantes=" + listaEstudiantes + "]";
	}

	//Metodos getters and setters
	public ArrayList<Estudiante> getListaEstudiantes() {
		return listaEstudiantes;
	}

	public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}

}
