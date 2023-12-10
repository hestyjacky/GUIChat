package com.example.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ContactItemController implements Initializable {
    @FXML
    private Label user_label;
    @FXML
    private Label correo_label;
    public void setData(Contact contact){
        user_label.setText(contact.getUsername());
        correo_label.setText(contact.getCorreo());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
