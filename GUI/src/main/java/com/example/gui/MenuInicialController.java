package com.example.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuInicialController
        extends encabezado
        implements Initializable{

    @FXML
    private VBox ContactLayout;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        return ls;
    }

     */
}
