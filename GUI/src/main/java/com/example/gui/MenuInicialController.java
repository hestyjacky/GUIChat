package com.example.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class MenuInicialController
        extends encabezado
        implements Initializable{
    //TODO: -buscar un contacto y mostrar en una lista desplegable?
    // - colocar los 3 contactos más usados
    // - almacenar chats
    // - hacer el zoombido
    // - mandar fotos y videos

    @FXML
    private ListView<String> ListView;

    @FXML
    private Label Label_user, Label_email;
    private String UsuarioEnSesion, Correo;

    public void setDatos(String UsuarioEnSesion, String Correo){
        this.UsuarioEnSesion = UsuarioEnSesion;
        this.Correo = Correo;
        actualizarInterfaz();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (Socket socket = new Socket("localhost", 1408); // ip ---------
             ServerSocket serverSocket = new ServerSocket(1410)) {
            System.out.println("\nExiste conexion para Menu\n");

            String query = "select id from usuarios;";
            Server SV = new Server(serverSocket);

            String contenido = SV.SendResultsQuery(query);
            String[] contenidoSplit = contenido.split("\n");

            /*
            for (int i = 0; i<contenidoSplit.length; i++){
                System.out.println(contenidoSplit[i]);
            }
             */

            ObservableList<String> items = FXCollections.observableArrayList(
                    contenidoSplit
            );
            ListView.setItems(items);
        } catch (IOException e) {
            System.out.println("Error con conexion en menu");
        }
    }

    private void actualizarInterfaz() {
        Label_user.setText(UsuarioEnSesion);
        Label_email.setText(Correo);
    }
}
