package com.example.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MenuInicialController extends encabezado implements Initializable {

    @FXML
    private ListView<String> ListView;
    @FXML
    private Button Contacto1, Contacto2, Contacto3;
    @FXML
    private Label Label_user, Label_email;
    private String UsuarioEnSesion, Correo;

    public void setDatos(String UsuarioEnSesion, String Correo) {
        this.UsuarioEnSesion = UsuarioEnSesion;
        this.Correo = Correo;
        actualizarInterfaz();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (Socket socket = new Socket("localhost", 1408);
             ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("\nExiste conexión para Menu\n");

            String query = "select id from usuarios;";
            // Enviar la consulta al servidor remoto
            outStream.writeObject(query);

            // Recibir la respuesta del servidor
            String contenido = (String) inStream.readObject();
            String[] contenidoSplit = contenido.split("\n");

            ObservableList<String> items = FXCollections.observableArrayList(contenidoSplit);
            ListView.setItems(items);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error con la conexión en el menú");
            e.printStackTrace();
        }
    }

    private void actualizarInterfaz() {
        Label_user.setText(UsuarioEnSesion);
        Label_email.setText(Correo);
    }

    // TODO - Colocar los 3 contactos más usados
    private void ContactosMasFrecuentes() {
        String query1 = "Select sender from chats;";
        String query2 = "Select receiver from chats;";
        // Puedes continuar con la lógica de obtener los contactos más frecuentes
    }
}
