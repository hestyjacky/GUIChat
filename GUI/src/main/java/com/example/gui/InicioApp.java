package com.example.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioApp extends Application {
    @Override
    public void start(Stage stage) throws IOException { // main
        FXMLLoader fxmlLoader = new FXMLLoader(InicioApp.class.getResource("Inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 668, 408);

        /*
        stage.setMinWidth(668);
        stage.setMinHeight(408);

        stage.setMaxWidth(668);
        stage.setMaxHeight(408);

         */

        scene.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());


        stage.setTitle("Inicio de sesión");
        stage.setScene(scene);
        stage.onCloseRequestProperty().setValue(event -> System.out.println("\nEnd - Inicio!"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}