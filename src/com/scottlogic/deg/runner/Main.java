package com.scottlogic.deg.runner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataHelixRunner.fxml"));
        Parent root = (Parent)loader.load();
        DataHelixController controller = (DataHelixController)loader.getController();
        controller.setStageAndSetupListeners(primaryStage); // or what you want to do
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
