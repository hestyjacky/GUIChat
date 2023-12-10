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

    @FXML
    private ListView<String> ListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try (Socket socket = new Socket("localhost", 1408); // ip ---------
             ServerSocket serverSocket = new ServerSocket(1409)){
            System.out.println("Existe conexion para Menu");

            String query = "select id from usuarios;";
            Server SV = new Server(serverSocket);

            String contenido = SV.SendResultsQuery(query);
            String[] contenidoSplit = contenido.split("\n");

            for (int i = 0; i<contenidoSplit.length; i++){
                System.out.println(contenidoSplit[i]);
            }

            ObservableList<String> items = FXCollections.observableArrayList(
                    contenidoSplit
            );
            ListView.setItems(items);

        }catch (IOException e){
            System.out.println("Error con conexion en menu");
        }



        /*
        List<Contact> contacts = new ArrayList<>(contacts());
        for (int i =0; i<contacts.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("contact_item.fxml"));

            try {
                HBox hBox = fxmlLoader.load();
                ContactItemController cic = fxmlLoader.getController();
                cic.setData(contacts.get(i));
                ContactLayout.getChildren().add(hBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
         */
    }
/*
 private List<Contact> contacts(){
        List<Contact> ls = new ArrayList<>();
        Contact contact = new Contact();

        contact.setUsername("Usr 1");
        contact.setCorreo("usr345@qwer.com");
        ls.add(contact);

        contact.setUsername("Usr 2");
        contact.setCorreo("5@qwer.com");
        ls.add(contact);

        contact.setUsername("Usr 3");
        contact.setCorreo("345@qwer.com");
        ls.add(contact);

        return ls;
    }
 */
}
