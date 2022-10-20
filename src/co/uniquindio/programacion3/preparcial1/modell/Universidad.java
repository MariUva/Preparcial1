package co.uniquindio.programacion3.preparcial1.modell;

import java.util.ArrayList;

public class Universidad {

	// Atributos de la clase
	ArrayList<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();
	ArrayList<Programas> listaProgramas = new ArrayList<Programas>();

	// Metodo constructor

	public Universidad(ArrayList<Estudiante> listaEstudiantes, ArrayList<Programas> listaProgramas) {
		super();
		this.listaEstudiantes = listaEstudiantes;
		this.listaProgramas = listaProgramas;
	}

	// Metodo contructor vacio
	public Universidad() {
		super();
	}

	// Metodo toString
	@Override
	public String toString() {
		return "Universidad [listaEstudiantes=" + listaEstudiantes + ", listaProgramas=" + listaProgramas + "]";
	}

	// Metodos getters and setters
	public ArrayList<Estudiante> getListaEstudiantes() {
		return listaEstudiantes;
	}

	public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}

	public ArrayList<Programas> getListaProgramas() {
		return listaProgramas;
	}

	public void setListaProgramas(ArrayList<Programas> listaProgramas) {
		this.listaProgramas = listaProgramas;
	}

}
