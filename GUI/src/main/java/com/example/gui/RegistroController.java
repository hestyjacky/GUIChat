package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroController {

    @FXML
    private Label Mensaje_Botones;
    @FXML
    private TextField RegistroUser;
    @FXML
    private TextField CorreoUser;
    @FXML
    private TextField ContrasenaUser;
    @FXML
    private Button Bonton_Minimizar;
    @FXML
    private Button Boton_Maximizar;
    @FXML
    public Text textUser;

    @FXML
    private void Salir_encabezado(ActionEvent event) {
        // Lógica para la acción "Guardar"
        System.out.println("Salir...");
        System.out.println("Gracias por su preferencia!");
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void Minimizar_encabezado() {
        // Obtener la referencia de la ventana actual
        Stage stage = (Stage) Bonton_Minimizar.getScene().getWindow();

        // Minimizar la ventana
        stage.setIconified(true);
    }
    @FXML
    private void MaximizarVentana() {
        // Obtener la referencia de la ventana actual
        Stage stage = (Stage) Boton_Maximizar.getScene().getWindow();

        // Verificar si la ventana está maximizada y actuar en consecuencia
        if (stage.isMaximized()) {
            stage.setMaximized(false); // Desmaximizar la ventana
        } else {
            stage.setMaximized(true);  // Maximizar la ventana
        }
    }

    @FXML
    protected void Register_ButtonClick(ActionEvent event) {
        String userRegistro = RegistroUser.getText();
        String correo = CorreoUser.getText();
        String contrasena = ContrasenaUser.getText();

        if (correo.isBlank() || contrasena.isBlank() || userRegistro.isBlank()) {
            String user = "Ocupa llenar todos campos...";
            Mensaje_Botones.setText(user);
        }else { // todas las casillas estan llenas
            if (validarCorreo(correo)) { // si contiene la estructura correcta...
                String query = "select correo from usuarios where correo = "+correo+";";
                String query2 = "select id from usuarios where id = "+userRegistro+";";
                DatabaseSystem BD = new DatabaseSystem();

                if (!BD.DatabaseSystemStr(query).contains(correo) && !BD.DatabaseSystemStr(query2).contains(userRegistro)){
                    String query3 = "insert into usuarios (id, correo, contraseña) values ("+userRegistro+", "+correo+", "+contrasena+");";

                    BD.DatabaseSystemStr(query3);

                    try {
                        InicioApp Inicio = new InicioApp();
                        Stage regScene = new Stage();
                        Inicio.start(regScene);
                        Node source = (Node) event.getSource();
                        Stage stage = (Stage) source.getScene().getWindow();
                        stage.close();
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }else{
                    String user = "    Utiliza otros datos...";
                    Mensaje_Botones.setText(user);
                }
            } else {
                String user = "     Correo no valido...";
                Mensaje_Botones.setText(user);
            }
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

    @FXML
    protected void SignUp_ButtonClick(){
        Mensaje_Botones.setText("Abriendo página de registro...");
    }
}
