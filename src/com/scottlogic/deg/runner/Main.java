package com.scottlogic.deg.runner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataHelixRunner.fxml"));
        Parent root = (Parent) loader.load();
        DataHelixController controller = (DataHelixController) loader.getController();
        controller.setStageAndSetupListeners(primaryStage); // or what you want to do
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });

        primaryStage.show();
    }
}
