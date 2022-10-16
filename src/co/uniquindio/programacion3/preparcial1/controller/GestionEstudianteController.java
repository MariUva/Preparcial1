package co.uniquindio.programacion3.preparcial1.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.programacion3.preparcial1.application.Aplicacion;
import co.uniquindio.programacion3.preparcial1.modell.Estudiante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestionEstudianteController {

	private Aplicacion aplicacion;
	private ModelFactoryController modelFactoryController;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableColumn<String, Estudiante> columNombre;

	@FXML
	private TableColumn<String, Estudiante> columNota1;

	@FXML
	private Button btnLimpiar;

	@FXML
	private Button btnActualizar;

	@FXML
	private TableColumn<String, Estudiante> columNota2;

	@FXML
	private TableColumn<String, Estudiante> columNota3;

	@FXML
	private TableView<Estudiante> tableviewEstudiantes;

	@FXML
	private TableColumn<String, Estudiante> columCodigo;

	@FXML
	private TextField txtCodigo;

	@FXML
	private Button btnAgregar;

	@FXML
	private TextField txtNota3;

	@FXML
	private TextField txtNota2;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtNota1;

	@FXML
	private Button btnEliminar;

	@FXML
	private TextField txtBuscarEstudiante;

	@FXML
	private Button btnBuscar;

	/*
	 * Metodo que permite limpiar los campos de texto en caso de tener texto
	 */
	@FXML
	void limpiarEstudiante(ActionEvent event) {

		txtNombre.clear();
		txtCodigo.clear();
		txtNota1.clear();
		txtNota2.clear();
		txtNota3.clear();
		txtBuscarEstudiante.clear();

		txtCodigo.setDisable(false);

	}

	/*
	 * Metodo que permite actualizar un estudiante
	 */
	@FXML
	private void actualizarEstudiante(ActionEvent event) {

		String nombre = txtNombre.getText();
		String codigo = txtCodigo.getText();
		String nota1 = txtNota1.getText();
		String nota2 = txtNota2.getText();
		String nota3 = txtNota3.getText();

		if (estudianteSeleccion != null) {

			if (datosValidos(nombre, codigo, nota1, nota2, nota3)) {
				// aplicacion.actualizarEstudiante(nombre, codigo, notas);

				estudianteSeleccion.setNombre(nombre);
				estudianteSeleccion.setCodigo(codigo);
				// estudianteSeleccion.setNotas(notas);

				double notasAux = notasADouble(nota1, nota2, nota3);

				tableviewEstudiantes.refresh();
				mostrarMensaje("Informaci�n", "Actualizar", "El estudiante ha sido actualizado.",
						AlertType.CONFIRMATION);

			}

		} else {
			mostrarMensaje("Advertencia", "Actualizar", "No se ha realizado la selecci�n de un estudiante.",
					AlertType.WARNING);

		}

	}

	/*
	 * Metodo que permite agregar un estudiante
	 */
	@FXML
	void agregarEstudiante(ActionEvent event) {

		String nombre = txtNombre.getText();
		String codigo = txtCodigo.getText();
		String nota1 = this.txtNota1.getText();

		String nota2 = this.txtNota2.getText();
		String nota3 = this.txtNota3.getText();

		try {
			if (datosValidos(nombre, codigo, nota1, nota2, nota3)) {
				crearEstudiante(nombre, codigo, nota1, nota2, nota3);
				actualizarTabla();
			}
		} catch (Exception ignored) {

		}
	}

	private double notasADouble(String nota1, String nota2, String nota3) {
		double notasAux = 0;
		try {
			notasAux = Double.parseDouble(nota1);
			notasAux = Double.parseDouble(nota2);
			notasAux = Double.parseDouble(nota3);

		} catch (Exception e) {
			mostrarMensaje("Advertencia", "Informaci�n del empleado es invalida",
					"Ingrese un valor numerico en el campo de notas", AlertType.WARNING);
		}
		return notasAux;

	}

	/*
	 * Metodo para crear un estudiante
	 */
	private void crearEstudiante(String nombre, String codigo, String nota1, String nota2, String nota3) {

		double notasAux = notasADouble(nota1, nota2, nota3);
		Estudiante estudiante = aplicacion.crearEstudiante(nombre, codigo, notasAux, notasAux, notasAux);

		// Notificar que el estudiante fue creado

		if (estudiante != null) {
			listadoEstudiantes.add(0, estudiante);
			listadoEstudiantes.add(estudiante);
			mostrarMensaje("Notificaci�n estudiante", "Estudiante guardado",
					"El estudiante " + estudiante.getNombre() + " ha sido guardado", AlertType.INFORMATION);

		} else {

			mostrarMensaje("Notificaci�n Estudiante", "Estudiante no guardado",
					"El estudiante " + nombre + " no ha sido guardado", AlertType.WARNING);

		}

	}

	/**
	 * Este metodo muestra un mensaje al usuario
	 *
	 * @param titulo
	 * @param header
	 * @param contenido
	 * @param alertType
	 */
	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alerta = new Alert(alertType);
		alerta.setTitle(titulo);
		alerta.setHeaderText(header);
		alerta.setContentText(contenido);
		alerta.showAndWait();

	}

	/*
	 * Este metodo permite buscar un estudiante
	 */
	@FXML
	void buscarEstudiante(ActionEvent event) {

	}

	/*
	 * Metodo que permite verificar si todos los campos han sido dilingeciados
	 */

	private boolean datosValidos(String nombre, String codigo, String nota1, String nota2, String nota3) {

		boolean flag = true;
		String notificacion = "";

		if (nombre == null || nombre.equals("")) {
			notificacion += "Nombre no tiene informaci�n\n";

		}
		if (codigo == null || codigo.equals("")) {
			notificacion += "Codigo no tiene informaci�n\n";

		}

		if (nota1.equals("")) {
			flag = false;
			notificacion += "La nota 1 no tiene informaci�n\n";

		}
		if (flag) {
			try {
				double notasAux1 = Double.parseDouble(nota1);
			} catch (Exception e) {
				notificacion += "Las nota 1 deben contener valores numericos";
			}
		}

		if (nota2.equals("")) {
			flag = false;
			notificacion += "La nota 2 no tiene informaci�n\n";
		}
		if (flag) {
			try {
				double notasAux2 = Double.parseDouble(nota2);
			} catch (Exception e) {
				notificacion += "La nota 2 deben contener valores numericos";
			}
		}

		if (nota3.equals("")) {
			flag = false;
			notificacion += "La nota 3 no tiene informaci�n\n";
		}
		if (flag) {
			try {
				double notasAux3 = Double.parseDouble(nota3);
			} catch (Exception e) {
				notificacion += "La nota 3 deben contener valores numericos";
			}
		}

		if (notificacion.equals("")) {
			return true;

		}

		mostrarMensaje("Advertencia", "Informaci�n del empleado invalida", notificacion, AlertType.WARNING);
		return false;
	}

	@FXML
	void eliminarEstudiante(ActionEvent event) {

		if (estudianteSeleccion != null) {
			if (aplicacion.eliminarEstudiantes(estudianteSeleccion.getCodigo())) {
				mostrarMensaje("Informaci�n", "Estudiante  eliminado", "El estudiante ha sido eliminado",
						AlertType.ERROR);

			} else {

				mostrarMensaje("Informaci�n", "Estudiante selecci�n",
						"No se ha realizado la selecci�n de un estudiante", AlertType.INFORMATION);

			}

			listadoEstudiantes.remove(estudianteSeleccion);

		} else {

			mostrarMensaje("Advertencia", "Estudiante selecci�n", "No se ha realizado la seleccion de un estudiante",
					AlertType.ERROR);

		}

	}

	// ---------------------TABLA-------------------------

	ObservableList<Estudiante> listadoEstudiantes = FXCollections.observableArrayList();

	private Estudiante estudianteSeleccion;

	private void mostrarInformacion() {

		if (estudianteSeleccion != null) {

			txtNombre.setText(estudianteSeleccion.getNombre());
			txtCodigo.setText(estudianteSeleccion.getCodigo());
			txtNota1.setText(estudianteSeleccion.getNota1() + "");
			txtNota2.setText(estudianteSeleccion.getNota2() + "");
			txtNota3.setText(estudianteSeleccion.getNota3() + "");

		}

	}

	public void actualizarTabla() {

		tableviewEstudiantes.getItems().clear();
		listadoEstudiantes.clear();

		listadoEstudiantes.addAll(modelFactoryController.getListaEstudiante());
		tableviewEstudiantes.getItems().addAll(listadoEstudiantes);
		tableviewEstudiantes.refresh();
	}

	// @FXML
	// void 96da38(ActionEvent event) {
	//
	// }

	@FXML
	void initialize() {

		this.columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		this.columNota1.setCellValueFactory(new PropertyValueFactory<>("nota1"));
		this.columNota2.setCellValueFactory(new PropertyValueFactory<>("nota2"));
		this.columNota3.setCellValueFactory(new PropertyValueFactory<>("nota3"));

		tableviewEstudiantes.getSelectionModel().selectedItemProperty()
				.addListener((obs, olSelection, newSelection) -> {

					if (newSelection != null) {
						estudianteSeleccion = newSelection;
						mostrarInformacion();
					}

				});

	}

	public void setAplicacion(Aplicacion aplicacion) {

		this.aplicacion = aplicacion;
		this.modelFactoryController = aplicacion.getModelFactoryController();

		tableviewEstudiantes.getItems().clear();
		tableviewEstudiantes.setItems(getEstudiantes());

	}

	private ObservableList<Estudiante> getEstudiantes() {
		listadoEstudiantes.addAll(modelFactoryController.getListaEstudiante());
		return listadoEstudiantes;
	}

	public void setAplicacion(ModelFactoryController modelFactoryControlleR) {

	}
}
