package co.uniquindio.programacion3.preparcial1.application;

import co.uniquindio.programacion3.preparcial1.controller.GestionEstudianteController;
import co.uniquindio.programacion3.preparcial1.controller.ModelFactoryController;
import co.uniquindio.programacion3.preparcial1.modell.Colegio;
import co.uniquindio.programacion3.preparcial1.modell.Estudiante;
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

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class
					.getResource("/co/uniquindio/programacion3/preparcial1/view/EstudianteView.fxml"));

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

	public Estudiante crearEstudiante(String nombre, String codigo, double notas) {
		Estudiante estudiantes = modelFactoryController.agregarEstudiante(nombre, codigo, notas);
		return estudiantes;

	}

	public boolean eliminarEstudiantes(String codigo) {

		boolean eliminarEstudiante = modelFactoryController.eliminarEstudiante(codigo);
		return eliminarEstudiante;
	}


//	public void actualizarEstudiante(String nombre, String codigo, String notas	){
//
//		modelFactoryController.actualizarEstudiante(nombre, codigo, notas);
//
//	}

//	public void buscarEstudiante(String codigo){
//
//		boolean buscarEstudiante = modelFactoryController.buscarEstudianre(codigo);
//		return buscarEstudiante;
//
//
//	}



}
