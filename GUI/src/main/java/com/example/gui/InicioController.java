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

public class InicioController extends encabezado {
    @FXML
    private Label Mensaje_Botones;
    @FXML
    private TextField CorreoUser;
    @FXML
    private TextField ContrasenaUser;
    @FXML
    public Text textUser;
    // TODO: conexion al servidor para trabajar con sockets
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

            System.out.println("Establecio conexion");

            String correo = CorreoUser.getText();

            String contrasena = ContrasenaUser.getText();

            if (correo.isBlank() || contrasena.isBlank()) {
                Mensaje_Botones.setText("Ocupa llenar ambos campos...");
                System.out.println("hola1");

            }else{
                String query = "select * from usuarios where contraseña = "+contrasena+" and correo = "+correo+";\n";
                //Server SV = new Server(serverSocket);


                DatabaseSystem BD = new DatabaseSystem();

                if (BD.DatabaseSystemStr(query).contains(contrasena) || BD.DatabaseSystemStr(query).contains(correo)) {
                    System.out.println("coinciden con los datos");
                    //System.out.println("Return recibido:"+BD.DatabaseSystemStr(query));

                    Mensaje_Botones.setText(" Log-in exitoso !");

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
            Mensaje_Botones.setText("   Los datos son incorrectos...");
        }catch (IOException e){
            System.err.println("No establecio conexión");
        }

    }
}