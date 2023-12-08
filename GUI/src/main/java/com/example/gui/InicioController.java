package com.example.gui;

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
    //public String user;
    @FXML
    protected void SignUp_ButtonClick(){
        Mensaje_Botones.setText("Abriendo página de registro...");
        abrirRegistro("Inicio.fxml");
    }
    @FXML
    protected void LogIn_ButtonClick(ActionEvent e) throws IOException {
        String correo = CorreoUser.getText();
        //String correo = "jacky@gmai";

        String contrasena = ContrasenaUser.getText();
        //String contrasena = "123";

        if (correo.isBlank() || contrasena.isBlank()) {
            Mensaje_Botones.setText("Ocupa llenar ambos campos...");
            System.out.println("hola1");

        }else{
            //Socket socket = new Socket("localhost", 1408); // ip ---------
            String query = "select * from usuarios where contraseña = "+contrasena+" and correo = "+correo+";\n";
            DatabaseSystem BD = new DatabaseSystem();

            Mensaje_Botones.setText("   Los datos son incorrectos...");

            if (BD.DatabaseSystemStr(query).contains(contrasena) || BD.DatabaseSystemStr(query).contains(correo)){
                System.out.println("coinciden con los datos");
                //System.out.println("Return recibido:"+BD.DatabaseSystemStr(query));

                Mensaje_Botones.setText(" Log-in exitoso !");

                Node source = (Node) e.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

                abrirMenuInicial("MenuInicial.fxml");
            }
        }
    }

    private void abrirMenuInicial(String rutaFXML) {
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

    private void abrirRegistro(String rutaFXML) {
        try {
            // Cargar el nuevo FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = fxmlLoader.load();

            // Crear una nueva ventana para la nueva interfaz
            Stage nuevaVentana = new Stage();
            nuevaVentana.setTitle("Registro de usuario");

            // Configurar la escena con el nuevo contenido
            Scene scene = new Scene(root);
            nuevaVentana.setScene(scene);
            nuevaVentana.setWidth(800);
            nuevaVentana.setHeight(700);

            nuevaVentana.setMinWidth(800);
            nuevaVentana.setMinHeight(700);

            encabezado encabezado = new encabezado();
            encabezado.moverVentana(nuevaVentana,scene);

            //scene.getStylesheets().add(getClass().getResource("estilos_MenuInicial.css").toExternalForm());

            // Mostrar la nueva ventana
            nuevaVentana.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}