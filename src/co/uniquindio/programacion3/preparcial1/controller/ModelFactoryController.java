package co.uniquindio.programacion3.preparcial1.controller;

import java.io.IOException;
import java.util.ArrayList;

import co.uniquindio.programacion3.preparcial1.modell.Colegio;
import co.uniquindio.programacion3.preparcial1.modell.Estudiante;
import co.uniquindio.programacion3.preparcial1.persistence.Persistencia;

public class ModelFactoryController {

	Colegio colegio;

	// ------------------------------ Singleton
	// ------------------------------------------------
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

	public ModelFactoryController() {

		// 1. inicializar datos y luego guardarlo en archivos
		// iniciarSalvarDatosPrueba();

		// 2. Cargar los datos de los archivos
		// cargarDatosDesdeArchivos();

		// 3. Guardar y Cargar el recurso serializable binario
		// guardarResourceBinario();
		// cargarResourceBinario();

		// 4. Guardar y Cargar el recurso serializable XML
		// guardarResourceXML();

		// Siempre se debe verificar si la raiz del recurso es null
		if (colegio == null) {
			System.out.println("Inicializa");
			inicializarDatos();
			// guardarResourceSerializable();
		}

	}

	private void inicializarDatos() {

		colegio = new Colegio();

		Estudiante estudiante = new Estudiante();
		estudiante.setNombre("Juan");
		estudiante.setCodigo("12345");
		estudiante.setNotas(2.0);
		estudiante.setNotas(5.0);
		estudiante.setNotas(4.5);

		colegio.getListaEstudiantes().add(estudiante);
		getListaEstudiante().add(estudiante);

		try {
			Persistencia.guardarEstudiantes(colegio.getListaEstudiantes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// --------------- GETTERS AND SETTERS---------------

	public Colegio getEmpresa() {
		return colegio;
	}

	public ArrayList<Estudiante> getListaEstudiante() {
		return listaEstudiantes;
	}

	public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}

	public void setEmpresa(Colegio colegio) {
		this.colegio = colegio;
	}

	// ---------------------ESTUDIANTE---------------------------------

	public Estudiante agregarEstudiante(String nombre, String codigo, double notas) {
		if (existeEstudiante(codigo)) {
			return null;
		} else {
			Estudiante nuevoEstudiante = new Estudiante();
			nuevoEstudiante.setNombre(nombre);
			nuevoEstudiante.setCodigo(codigo);
			nuevoEstudiante.setNotas(notas);
			getListaEstudiante().add(nuevoEstudiante);
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

}