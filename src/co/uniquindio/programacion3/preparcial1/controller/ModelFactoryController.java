package co.uniquindio.programacion3.preparcial1.controller;

import java.io.IOException;
import java.util.ArrayList;

import co.uniquindio.programacion3.preparcial1.modell.Universidad;
import co.uniquindio.programacion3.preparcial1.modell.Estudiante;
import co.uniquindio.programacion3.preparcial1.modell.Programas;
import co.uniquindio.programacion3.preparcial1.persistence.Persistencia;

public class ModelFactoryController {

	Universidad universidad = new Universidad();

	// ------------------------------
	// Singleton----------------------------------

	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aquí al ser
		// protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// Método para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
	ArrayList<Programas> listaProgramas = new ArrayList<>();

	public ModelFactoryController() {

		iniciarSalvarDatosPrueba();
		cargarDatosDesdeArchivos();

		// guardarResourceBinario();
		// cargarResourceBinario();

		guardarResourceXML();
		cargarResourceXML();

		// Siempre se debe verificar si la raiz del recurso es null
		if (universidad == null) {
			System.out.println("Es null");
			inicializarDatos();

			// guardarResourceSerializable();
			guardarResourceXML();

		}

	}

	private void iniciarSalvarDatosPrueba() {

		inicializarDatos();

		try {

			Persistencia.guardarEstudiantes(getUniversidad().getListaEstudiantes());
			Persistencia.guardarProgramas(getUniversidad().getListaProgramas());

			Persistencia.cargarDatosArchivos(getUniversidad());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cargarDatosDesdeArchivos() {

		universidad = new Universidad();

		try {
			Persistencia.cargarDatosArchivos(getUniversidad());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cargarResourceBinario() {

		universidad = Persistencia.cargarRecursoBinario();

	}

	public void guardarResourceBinario() {

		Persistencia.guardarRecursoBinario(universidad);
	}

	public void cargarResourceXML() {

		universidad = Persistencia.cargarRecursoXML();
	}

	public void guardarResourceXML() {

		Persistencia.guardarRecursoXML(universidad);
	}

	private void inicializarDatos() {

		universidad = new Universidad();

		Estudiante estudiante = new Estudiante();
		estudiante.setNombre("Juan");
		estudiante.setCodigo("12345");
		estudiante.setNota1(2.0);
		estudiante.setNota2(5.0);
		estudiante.setNota3(4.5);

		universidad.getListaEstudiantes().add(estudiante);
		getListaEstudiantes().add(estudiante);

		estudiante = new Estudiante();
		estudiante.setNombre("Sara");
		estudiante.setCodigo("6789");
		estudiante.setNota1(3.0);
		estudiante.setNota2(4.0);
		estudiante.setNota3(2.5);

		universidad.getListaEstudiantes().add(estudiante);
		getListaEstudiantes().add(estudiante);

		Programas programa = new Programas();
		programa.setNombre("Ana");
		programa.setModalidad("Presencial");
		programa.setCodigo("12345");

		universidad.getListaProgramas().add(programa);
		getListaProgramas().add(programa);

		System.out.println("Universidad inicializada" + universidad);

		try {

			Persistencia.guardarEstudiantes(universidad.getListaEstudiantes());
			Persistencia.guardarProgramas(universidad.getListaProgramas());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// --------------- GETTERS AND SETTERS---------------

	public Universidad getUniversidad() {
		return universidad;
	}

	public void setUniversidad(Universidad universidad) {
		this.universidad = universidad;
	}

	public ArrayList<Estudiante> getListaEstudiantes() {
		return listaEstudiantes;
	}

	public ArrayList<Estudiante> getListaEstudiante() {
		return listaEstudiantes;
	}

	public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}

	public void setEmpresa(Universidad universidad) {
		this.universidad = universidad;
	}

	public ArrayList<Programas> getListaProgramas() {
		return listaProgramas;
	}

	public void setListaProgramas(ArrayList<Programas> listaProgramas) {
		this.listaProgramas = listaProgramas;
	}

	// ---------------------ESTUDIANTE---------------------------------

	public Estudiante agregarEstudiante(String nombre, String codigo, double nota1, double nota2, double nota3)
			throws IOException {
		if (existeEstudiante(codigo)) {
			return null;
		} else {
			Estudiante nuevoEstudiante = new Estudiante();
			nuevoEstudiante.setNombre(nombre);
			nuevoEstudiante.setCodigo(codigo);
			nuevoEstudiante.setNota1(nota1);
			nuevoEstudiante.setNota2(nota2);
			nuevoEstudiante.setNota3(nota3);
			getListaEstudiante().add(nuevoEstudiante);

			Persistencia.guardarEstudiantes(getListaEstudiante());

			return nuevoEstudiante;

		}

	}

	private boolean existeEstudiante(String codigo) {

		boolean existe = false;
		for (Estudiante estudiante : listaEstudiantes) {
			if (estudiante.getCodigo().equals(codigo)) {
				existe = true;
				return existe;
			}

		}
		return existe;

	}

	public Boolean eliminarEstudiante(String codigo) {
		Boolean flagEliminado = false;
		Estudiante estudiante = obtenerEstudiante(codigo);

		if (estudiante != null) {
			getListaEstudiante().remove(estudiante);
			flagEliminado = true;
		}
		return flagEliminado;
	}

	public Estudiante obtenerEstudiante(String codigoEstudiante) {

		Estudiante estudianteEncontrado = null;

		for (Estudiante estudiante : listaEstudiantes) {
			if (estudiante.getCodigo().equals(codigoEstudiante)) {
				estudianteEncontrado = estudiante;
				break;

			}
		}
		return estudianteEncontrado;

	}

	public void actualizarEstudiante(String nombre, String codigo, double nota1, double nota2, double nota3) {

		Estudiante estudiante = obtenerEstudiante(codigo);

		if (estudiante != null) {
			estudiante.setNombre(nombre);
			estudiante.setCodigo(codigo);
			estudiante.setNota1(nota1);
			estudiante.setNota2(nota2);
			estudiante.setNota3(nota3);

		}

	}

	// ---------------------PROGRAMAS---------------------------------

	public Programas agregarProgramas(String nombre, String codigo, String modalidad) throws IOException {
		if (existePrograma(codigo)) {
			return null;
		} else {
			Programas nuevoPrograma = new Programas();

			nuevoPrograma.setNombre(nombre);
			nuevoPrograma.setCodigo(codigo);
			nuevoPrograma.setModalidad(modalidad);

			getListaProgramas().add(nuevoPrograma);

			Persistencia.guardarProgramas(getListaProgramas());

			return nuevoPrograma;
		}

	}

	private boolean existePrograma(String codigo) {

		boolean existe = false;
		for (Programas programas : listaProgramas) {
			if (programas.getCodigo().equals(codigo)) {
				existe = true;
				return existe;
			}

		}
		return existe;

	}

	public Boolean eliminarPrograma(String codigo) {
		Boolean flagEliminado = false;
		Programas programa = obtenerPrograma(codigo);

		if (programa != null) {
			getListaProgramas().remove(programa);
			flagEliminado = true;
		}
		return flagEliminado;
	}

	public Programas obtenerPrograma(String codigoPrograma) {

		Programas programaEncontrado = null;

		for (Programas programas : listaProgramas) {
			if (programas.getCodigo().equals(codigoPrograma)) {
				programaEncontrado = programas;
				break;

			}
		}
		return programaEncontrado;

	}

	public void actualizarPrograma(String nombre, String codigo, String modalidad) {

		Programas programa = obtenerPrograma(codigo);

		if (programa != null) {
			programa.setNombre(nombre);
			programa.setCodigo(codigo);
			programa.setModalidad(modalidad);

		}

	}

}