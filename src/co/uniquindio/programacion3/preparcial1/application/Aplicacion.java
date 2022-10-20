package co.uniquindio.programacion3.preparcial1.application;

import java.io.IOException;

import co.uniquindio.programacion3.preparcial1.controller.GestionEstudianteController;
import co.uniquindio.programacion3.preparcial1.controller.GestionProgramasController;
import co.uniquindio.programacion3.preparcial1.controller.GestionVentanaPrincipal;
import co.uniquindio.programacion3.preparcial1.controller.ModelFactoryController;
import co.uniquindio.programacion3.preparcial1.modell.Universidad;
import co.uniquindio.programacion3.preparcial1.modell.Estudiante;
import co.uniquindio.programacion3.preparcial1.modell.Programas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Aplicacion extends Application {

	private Stage primaryStage;
	private ModelFactoryController modelFactoryController;

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		this.modelFactoryController = new ModelFactoryController();

		mostrarVentanaPrincipal();

	}

	public void mostrarVentanaPrincipal() {

		this.primaryStage.setTitle("Gestion uniquindio");

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class
					.getResource("/co/uniquindio/programacion3/preparcial1/view/VentanaPrincipal.fxml"));

			AnchorPane anchorPane = (AnchorPane) loader.load();
			GestionVentanaPrincipal gestionVentanaPrincipal = loader.getController();
			gestionVentanaPrincipal.setAplicacion(this);

			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			this.primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gestionarEstudiante() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
					Aplicacion.class.getResource("/co/uniquindio/programacion3/preparcial1/view/EstudianteView.fxml"));

			AnchorPane anchorPane = (AnchorPane) loader.load();
			GestionEstudianteController gestionEstudianteController = loader.getController();
			gestionEstudianteController.setAplicacion(this);

			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			this.primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void gestionarProgramas() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
					Aplicacion.class.getResource("/co/uniquindio/programacion3/preparcial1/view/ProgramasView.fxml"));

			AnchorPane anchorPane = (AnchorPane) loader.load();
			GestionProgramasController gestionProgramasController = loader.getController();
			gestionProgramasController.setAplicacion(this);

			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			this.primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public ModelFactoryController getModelFactoryController() {
		return modelFactoryController;
	}

	public void setModelFactoryController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
	}

	public static void main(String[] args) {
		launch(args);
	}

	// ----------------------------ESTUDIANTES----------------------------------

	public Estudiante crearEstudiante(String nombre, String codigo, double nota1, double nota2, double nota3)
			throws IOException {
		Estudiante estudiantes = modelFactoryController.agregarEstudiante(nombre, codigo, nota1, nota2, nota3);
		return estudiantes;

	}

	public boolean eliminarEstudiantes(String codigo) {

		boolean eliminarEstudiante = modelFactoryController.eliminarEstudiante(codigo);
		return eliminarEstudiante;
	}

	public void actualizarEstudiante(String nombre, String codigo, double nota1, double nota2, double nota3) {

		modelFactoryController.actualizarEstudiante(nombre, codigo, nota1, nota2, nota3);

	}

	// ----------------------------PROGRAMAS----------------------------------

	public Programas crearProgramas(String nombre, String codigo, String modalidad) throws IOException {
		Programas programas = modelFactoryController.agregarProgramas(nombre, codigo, modalidad);
		return programas;

	}

	public boolean eliminarPrograma(String codigo) {

		boolean eliminarProgramas = modelFactoryController.eliminarPrograma(codigo);
		return eliminarProgramas;
	}

	public void actualizarPrograma(String nombre, String codigo, String modalidad) {

		modelFactoryController.actualizarPrograma(nombre, codigo, modalidad);

	}

}
