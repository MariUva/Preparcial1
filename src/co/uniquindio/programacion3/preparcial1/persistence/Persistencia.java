
package co.uniquindio.programacion3.preparcial1.persistence;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.ArrayList;

import co.uniquindio.programacion3.preparcial1.modell.Colegio;
import co.uniquindio.programacion3.preparcial1.modell.Estudiante;

public class Persistencia {

	public static final String RUTA_ARCHIVO_ESTUDIANTES = "src/resources/archivoEstudiantes.txt";

	public static void cargarDatosArchivos(Colegio colegio) throws FileNotFoundException, IOException {

		// cargar archivo de estudiantes
		ArrayList<Estudiante> estudiantesCargados = cargarEstudiante();

		if (estudiantesCargados.size() > 0)
			colegio.getListaEstudiantes().addAll(estudiantesCargados);

	}

	/**
	 * Guarda en un archivo de texto todos la información de las personas
	 * almacenadas en el ArrayList
	 *
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */

	public static void guardarEstudiantes(ArrayList<Estudiante> listaEstudiantes) throws IOException {

		// TODO Auto-generated method stub
		String contenido = "";

		for (Estudiante estudiante : listaEstudiantes) {
			contenido += estudiante.getNombre() + "," + estudiante.getCodigo() + "," + estudiante.getNotas() + "\n";

		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ESTUDIANTES, contenido, false);
	}


	// ----------------------LOADS------------------------

	/**
	 *
	 * @param tipoPersona
	 * @param ruta
	 * @return un Arraylist de personas con los datos obtenidos del archivo de
	 *         texto indicado
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static ArrayList<Estudiante> cargarEstudiante() throws IOException {

		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ESTUDIANTES);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {

			linea = contenido.get(i);

			Estudiante estudiante = new Estudiante();
			estudiante.setNombre(linea.split(",")[0]);
			estudiante.setCodigo(linea.split(",")[1]);
			estudiante.setNotas(Double.parseDouble(linea.split(",")[2]));
			estudiantes.add(estudiante);

		}
		return estudiantes;
	}


	// ----------------------SAVES------------------------

	/**
	 * Guarda en un archivo de texto todos la información de las personas
	 * almacenadas en el ArrayList
	 *
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */

	public static void guardarObjetos(ArrayList<Estudiante> listaEstudiantes, String ruta) throws IOException {
		String contenido = "";

		for (Estudiante estudianteAux : listaEstudiantes) {

			contenido += estudianteAux.getNombre() + "," + estudianteAux.getCodigo() + "," + estudianteAux.getNotas()
					+ "\n";
		}
		ArchivoUtil.guardarArchivo(ruta, contenido, true);
	}

}