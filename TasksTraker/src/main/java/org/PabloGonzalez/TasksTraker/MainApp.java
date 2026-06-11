package org.PabloGonzalez.TasksTraker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.PabloGonzalez.TasksTraker.database.DatabaseInitializer;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Inicializar BD UNA VEZ al arrancar app
        DatabaseInitializer.initialize();

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/views/main-view.fxml")
        );

        Scene scene = new Scene(loader.load(), 900, 600);

        stage.setTitle("Tasks Tracker");
        stage.setScene(scene);
        stage.setWidth(1100);
        stage.setHeight(650);
        stage.centerOnScreen();
        stage.show();
    }
}