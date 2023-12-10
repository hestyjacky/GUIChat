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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    protected void SignUp_ButtonClick(ActionEvent event){
        Mensaje_Botones.setText("Abriendo página de registro...");
        try {
            RegistroApp Registro = new RegistroApp();
            Stage regScene = new Stage();
            Registro.start(regScene);
            Node source3 = (Node) event.getSource();
            Stage stage3 = (Stage) source3.getScene().getWindow();
            stage3.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    @FXML
    protected void LogIn_ButtonClick(ActionEvent event) {
        try (Socket socket = new Socket("localhost", 1408); // ip ---------
             ServerSocket serverSocket = new ServerSocket(1409)){
            System.out.println("Existe conexion para validar los datos");

            String correo = CorreoUser.getText();
            String contrasena = ContrasenaUser.getText();

            if (correo.isBlank() || contrasena.isBlank()) {
                Mensaje_Botones.setText("Ocupa llenar ambos campos...");

            }else{
                if (validarCorreo(correo)) {
                    String query = "select * from usuarios where contraseña = " + contrasena + " and correo = " + correo + ";\n";
                    Server SV = new Server(serverSocket);

                    if (SV.SendResultsQuery(query).contains(contrasena) || SV.SendResultsQuery(query).contains(correo)) {
                        System.out.println("coinciden con los datos, iniciando sesión");

                        Node source = (Node) event.getSource();
                        Stage stage = (Stage) source.getScene().getWindow();
                        stage.close();

                        try {
                            MenuInicialApp Menu = new MenuInicialApp();
                            Stage regScene = new Stage();
                            Menu.start(regScene);
                            Node source1 = (Node) event.getSource();
                            Stage stage2 = (Stage) source1.getScene().getWindow();
                            stage2.close();
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                }
            }
            Mensaje_Botones.setText("   Los datos son incorrectos...");
        }catch (IOException e){
            System.err.println("No establecio conexión");
        }
    }

    public static boolean validarCorreo(String correo) {
        // Definir la expresión regular para validar el formato del correo electrónico
        String patronCorreo = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

        // Compilar la expresión regular en un objeto Pattern
        Pattern pattern = Pattern.compile(patronCorreo);

        // Crear un objeto Matcher que comparará el patrón con la cadena de correo
        Matcher matcher = pattern.matcher(correo);

        // Verificar si el correo coincide con el patrón
        return matcher.matches();
    }
}