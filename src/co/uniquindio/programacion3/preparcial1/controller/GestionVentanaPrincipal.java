package co.uniquindio.programacion3.preparcial1.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.programacion3.preparcial1.application.Aplicacion;
import co.uniquindio.programacion3.preparcial1.persistence.Persistencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GestionVentanaPrincipal {

	private Aplicacion aplicacion;
	private ModelFactoryController modelFactoryController;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnEstudiantes;

	@FXML
	private Button btnProgramas;

	// @FXML
	// void 96da38(ActionEvent event) {
	//
	// }

	@FXML
	void irEstudiantes(ActionEvent event) {

		aplicacion.gestionarEstudiante();
		Persistencia.guardarRegistroLog("Se ingreso a estudiantes" , 1 , "Acción ingresar");

	}

	@FXML
	void irProgramas(ActionEvent event) {

		aplicacion.gestionarProgramas();
		Persistencia.guardarRegistroLog("Se ingreso a programas" , 1 , "Acción ingresar");

	}

	@FXML
	void initialize() {

	}

	public void setAplicacion(Aplicacion aplicacion) {

		this.aplicacion = aplicacion;
		this.modelFactoryController = aplicacion.getModelFactoryController();

	}

	public void setAplicacion(ModelFactoryController modelFactoryControlleR) {

	}

}
