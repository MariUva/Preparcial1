package co.uniquindio.programacion3.preparcial1.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import co.uniquindio.programacion3.preparcial1.application.Aplicacion;
import co.uniquindio.programacion3.preparcial1.modell.Programas;
import co.uniquindio.programacion3.preparcial1.persistence.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestionProgramasController {

	private Aplicacion aplicacion;
	private ModelFactoryController modelFactoryController;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableColumn<String, Programas> columNombre;

	@FXML
	private Button btnLimpiar;

	@FXML
	private TableView<Programas> tableviewProgramas;

	@FXML
	private TableColumn<String, Programas> columCodigo;

	@FXML
	private TextField txtCodigo;

	@FXML
	private Button btnAgregar;

	@FXML
	private TextField txtBuscarPrograma;

	@FXML
	private Button btnAtras;

	@FXML
	private ComboBox<String> cmbModalidad;

	@FXML
	private TableColumn<String, Programas> columModalidad;

	@FXML
	private TextField txtNombre;

	@FXML
	private Button btnEliminar;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnActualizar;

	/*
	 * Metodo que permite limpiar los campos de texto en caso de tener texto
	 */
	@FXML
	void limpiarProgramas(ActionEvent event) {

		txtNombre.clear();
		txtCodigo.clear();
		cmbModalidad.setValue("");

		txtCodigo.setDisable(false);

	}

	/*
	 * Metodo que permite actualizar un programa
	 */
	@FXML
	void actualizarPrograma(ActionEvent event) {

		String nombre = txtNombre.getText();
		String codigo = txtCodigo.getText();
		String modalidad = cmbModalidad.getValue();

		if (programasSeleccion != null) {

			if (datosValidos(nombre, codigo, modalidad)) {

				programasSeleccion.setNombre(nombre);
				programasSeleccion.setCodigo(codigo);
				programasSeleccion.setModalidad(modalidad);

				tableviewProgramas.refresh();
				mostrarMensaje("Información", "Actualizar", "El programa ha sido actualizado.", AlertType.CONFIRMATION);

			}

		} else {
			mostrarMensaje("Advertencia", "Actualizar", "No se ha seleccionado un programa.", AlertType.WARNING);

		}

	}

	/*
	 * Metodo que permite agregar un programa
	 */
	@FXML
	void agregarProgramas(ActionEvent event) {

		String nombre = txtNombre.getText();
		String codigo = txtCodigo.getText();
		String modalidad = cmbModalidad.getValue();

		try {
			if (datosValidos(nombre, codigo, modalidad)) {
				crearPrograma(nombre, codigo, modalidad);
				actualizarTabla();
			}
		} catch (Exception ignored) {

		}

	}

	/*
	 * Metodo para crear un programa
	 */
	private void crearPrograma(String nombre, String codigo, String modalidad) throws IOException {

		Programas programa = aplicacion.crearProgramas(nombre, codigo, modalidad);
		// Programas programa = aplicacion.crearProgramas(nombre, codigo,
		// Persistencia.obtenerNombreProperties());

		// Notificar que el programa fue creado
		if (programa != null) {
			listadoProgramas.add(0, programa);
			listadoProgramas.add(programa);
			mostrarMensaje("Notificación programa", "Programa guardado",
					"El programa" + programa.getNombre() + " ha sido guardado", AlertType.INFORMATION);

			Persistencia.guardarRegistroLog("Se creo un programa", 1, "Acción crear");

		} else {

			mostrarMensaje("Notificación programa", "Programa no guardado",
					"El programa " + nombre + " no ha sido guardado", AlertType.WARNING);

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
	 * Este metodo permite buscar un programa por su codigo
	 */
	@FXML
	void buscarPrograma(ActionEvent event) {
		String buscarCodigo = this.txtBuscarPrograma.getText();

		if (buscarCodigo.isEmpty()) {

			this.tableviewProgramas.setItems(listadoProgramas);

		} else {
			this.listaBuscarProgramas.clear();
			for (Programas programas : this.listadoProgramas) {
				if (programas.getCodigo().contains(buscarCodigo)) {
					this.listaBuscarProgramas.add(programas);

				}

			}
			this.tableviewProgramas.setItems(listaBuscarProgramas);
		}

	}

	/*
	 * Metodo que permite verificar si todos los campos de texto han sido
	 * dilingeciados
	 */
	private boolean datosValidos(String nombre, String codigo, String modalidad) {

		String notificacion = "";

		if (nombre == null || nombre.equals("")) {
			notificacion += "Nombre no tiene información\n";

		}
		if (codigo == null || codigo.equals("")) {
			notificacion += "Codigo no tiene información\n";

		}
		if (modalidad == null || modalidad.equals("")) {
			notificacion += "Modalidad no tiene información\n";

		}

		if (notificacion.equals("")) {
			return true;

		}

		mostrarMensaje("Advertencia", "Información del empleado invalida", notificacion, AlertType.WARNING);
		return false;
	}

	/*
	 * Metodo para eliminar un programa
	 */
	@FXML
	void eliminarProgramas(ActionEvent event) {

		if (programasSeleccion != null) {
			if (aplicacion.eliminarPrograma(programasSeleccion.getCodigo())) {
				mostrarMensaje("Información", "Programa eliminado", "El programa ha sido eliminado", AlertType.ERROR);

			} else {
				mostrarMensaje("Información", "Programa selección", "No se ha seleccionado un programa",
						AlertType.INFORMATION);

			}

			listadoProgramas.remove(programasSeleccion);

		} else {

			mostrarMensaje("Advertencia", "Programa selección", "No se ha realizado la seleccion de un programa",
					AlertType.ERROR);

		}

	}

	/*
	 * Metodo para regresar a la ventana principal
	 */
	@FXML
	void mostrarVentanaPrincipal(ActionEvent event) {
		aplicacion.mostrarVentanaPrincipal();
	}

	// ---------------------TABLA-------------------------

	ObservableList<Programas> listadoProgramas = FXCollections.observableArrayList();
	ObservableList<Programas> listaBuscarProgramas = FXCollections.observableArrayList();

	private Programas programasSeleccion;

	private void mostrarInformacion() {

		if (programasSeleccion != null) {

			txtNombre.setText(programasSeleccion.getNombre());
			txtCodigo.setText(programasSeleccion.getCodigo());
			cmbModalidad.setValue(programasSeleccion.getModalidad());

			txtCodigo.setDisable(true);
		}

	}

	public void actualizarTabla() {

		tableviewProgramas.getItems().clear();
		listadoProgramas.clear();
		listadoProgramas.addAll(modelFactoryController.getListaProgramas());
		// tableviewProgramas.getItems().addAll(listadoProgramas);
		tableviewProgramas.refresh();
	}

	@FXML
	void initialize() {

		this.columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		this.columModalidad.setCellValueFactory(new PropertyValueFactory<>("modalidad"));

		tableviewProgramas.getSelectionModel().selectedItemProperty().addListener((obs, olSelection, newSelection) -> {

			if (newSelection != null) {
				programasSeleccion = newSelection;
				mostrarInformacion();
			}

		});

		cmbModalidad.getItems().addAll(cargarPropiedades());
	}

	private String[] cargarPropiedades(){
	 String[] propiedades = new String[2];
	Properties properties = new Properties();
	InputStream entrada;
	try{
		entrada = new FileInputStream("src/resources/modalidad_dis_pre.properties");
			properties. load (entrada);
		propiedades[0] = properties.getProperty ("modalidad1");
		propiedades[1] = properties.getProperty ("modalidad2");
	}catch (Exception e) {
			throw new RuntimeException(e);

		}
		return propiedades;
	}

		public void setAplicacion(Aplicacion aplicacion) {

			this.aplicacion = aplicacion;
			this.modelFactoryController = aplicacion.getModelFactoryController();

			tableviewProgramas.getItems().clear();
			tableviewProgramas.setItems(getProgramas());

		}

		private ObservableList<Programas> getProgramas() {
			listadoProgramas.addAll(modelFactoryController.getListaProgramas());
			return listadoProgramas;
		}

		public void setAplicacion(ModelFactoryController modelFactoryControlleR) {

		}

	}
