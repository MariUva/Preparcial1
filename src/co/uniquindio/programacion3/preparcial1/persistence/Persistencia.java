package co.uniquindio.programacion3.preparcial1.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.uniquindio.programacion3.preparcial1.modell.Universidad;
import co.uniquindio.programacion3.preparcial1.modell.Estudiante;
import co.uniquindio.programacion3.preparcial1.modell.Programas;

public class Persistencia {

	public static final String RUTA_ARCHIVO_ESTUDIANTES = "src/resources/archivoEstudiantes.txt";
	public static final String RUTA_ARCHIVO_PROGRAMAS = "src/resources/archivoProgramas.txt";

	public static final String RUTA_ARCHIVO_LOG = "src/resources/univeridadLog.txt";

	public static final String RUTA_ARCHIVO_XML = "src/resources/listaPrograma.xml";

	public static final String RUTA_ARCHIVO_BINARIO = "src/resources/binario.dat";

	public static final String RUTA_PROPERTIES = "src/resources";

	public static void cargarDatosArchivos(Universidad universidad) throws FileNotFoundException, IOException {

		// cargar archivo de estudiantes
		ArrayList<Estudiante> estudiantesCargados = cargarEstudiante();

		if (estudiantesCargados.size() > 0)
			universidad.getListaEstudiantes().addAll(estudiantesCargados);

		// Ccargar archivo de programas
		ArrayList<Programas> programasCargados = cargarProgramas();

		if (programasCargados.size() > 0)
			universidad.getListaProgramas().addAll(programasCargados);

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
			contenido += estudiante.getNombre() + "," + estudiante.getCodigo() + "," + estudiante.getNota1() + ","
					+ estudiante.getNota2() + "," + estudiante.getNota3() + "\n";

		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ESTUDIANTES, contenido, false);
	}

	public static void guardarProgramas(ArrayList<Programas> listaProgramas) throws IOException {

		// TODO Auto-generated method stub
		String contenido = "";

		for (Programas programa : listaProgramas) {
			contenido += programa.getNombre() + "," + programa.getCodigo() + "," + programa.getModalidad() + "\n";

		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PROGRAMAS, contenido, false);
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
			estudiante.setNota1(Double.parseDouble(linea.split(",")[2]));
			estudiante.setNota2(Double.parseDouble(linea.split(",")[3]));
			estudiante.setNota3(Double.parseDouble(linea.split(",")[4]));
			estudiantes.add(estudiante);

		}
		return estudiantes;
	}

	private static ArrayList<Programas> cargarProgramas() throws IOException {

		ArrayList<Programas> programas = new ArrayList<Programas>();

		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PROGRAMAS);
		String linea = "";

		for (int i = 0; i < contenido.size(); i++) {

			linea = contenido.get(i);

			Programas programa = new Programas();

			programa.setNombre(linea.split(",")[0]);
			programa.setCodigo(linea.split(",")[1]);
			programa.setModalidad(linea.split(",")[2]);
			programas.add(programa);

		}
		return programas;
	}

	public static void guardarRegistroLog(String mensajeLog, int nivel, String accion) {

		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
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

			contenido += estudianteAux.getNombre() + "," + estudianteAux.getCodigo() + "," + estudianteAux.getNota1()
					+ "," + estudianteAux.getNota2() + "," + estudianteAux.getNota3() + "\n";
		}
		ArchivoUtil.guardarArchivo(ruta, contenido, true);
	}

	// ------------------------------------SERIALIZACIÓN y XML----------------

	public static Universidad cargarRecursoBinario() {

		Universidad universidad = null;

		try {
			universidad = (Universidad) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_BINARIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return universidad;
	}

	public static void guardarRecursoBinario(Universidad universidad) {

		try {
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_BINARIO, universidad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Universidad cargarRecursoXML() {

		Universidad universidad = null;

		try {
			universidad = (Universidad) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_XML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return universidad;

	}

	public static void guardarRecursoXML(Universidad universidad) {

		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_XML, universidad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ---------------------------------------------------------------------

//	public static String obtenerNombreProperties() {
//
//		String aux = "";
//
//		Properties properties = new Properties();
//
//		try {
//			properties.load(new FileInputStream(new File(RUTA_PROPERTIES)));
//
//			properties.setProperty("modalidad", "distancia-presencial");
//
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return properties.getProperty("modalidad");
//	}

}