package pl.jcode.organizer;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
	public static EntityManager entityManager = entityManagerFactory.createEntityManager();

	public static void main(String[] args) {
		launch(args);
		entityManager.close();
		entityManagerFactory.close();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/FXML/mainWindow.fxml"));
		GridPane gridPane = loader.load();

		Scene scene = new Scene(gridPane);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Organizer");
		primaryStage.show();
	}

}
