package com.example.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MenuInicialApp extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws IOException { // main
        FXMLLoader fxmlLoader = new FXMLLoader(InicioApp.class.getResource("MenuInicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);

        scene.getStylesheets().add(getClass().getResource("estilos_MenuInicial.css").toExternalForm());

        stage.setTitle("Menú");
        stage.setScene(scene);
        stage.onCloseRequestProperty().setValue(event -> System.out.println("\nEnd - Chats!"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}