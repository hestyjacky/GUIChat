package com.example.gui;

import com.example.gui.encabezado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class InicioController extends encabezado {
    @FXML
    private Label Mensaje_Botones;
    @FXML
    private TextField CorreoUser;
    @FXML
    private TextField ContrasenaUser;
    @FXML
    public Text textUser;
    @FXML
    protected void SignUp_ButtonClick(){
        Mensaje_Botones.setText("Abriendo página de registro...");
    }
    @FXML
    protected void LogIn_ButtonClick(ActionEvent e) {
        String correo = CorreoUser.getText();
        String contrasena = ContrasenaUser.getText();

        if (correo.isBlank() || contrasena.isBlank()){
            String user = "Ocupa llenar ambos campos...";
            Mensaje_Botones.setText(user);
        }else{
            String user = " Log-in exitoso !";
            Mensaje_Botones.setText(user);

            Node source = (Node) e.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

            abrirNuevaInterfaz("MenuInicial.fxml");
        }
    }

    private void abrirNuevaInterfaz(String rutaFXML) {
        try {
            // Cargar el nuevo FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = fxmlLoader.load();

            // Crear una nueva ventana para la nueva interfaz
            Stage nuevaVentana = new Stage();
            nuevaVentana.setTitle("Menú");

            // Configurar la escena con el nuevo contenido
            Scene scene = new Scene(root);
            nuevaVentana.setScene(scene);
            nuevaVentana.setWidth(800);
            nuevaVentana.setHeight(700);

            nuevaVentana.setMinWidth(800);
            nuevaVentana.setMinHeight(700);

            encabezado encabezado = new encabezado();
            encabezado.moverVentana(nuevaVentana,scene);

            scene.getStylesheets().add(getClass().getResource("estilos_MenuInicial.css").toExternalForm());


            // Mostrar la nueva ventana
            nuevaVentana.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}