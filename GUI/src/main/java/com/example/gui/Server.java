package com.example.gui;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class Server{
    private ServerSocket serverSocket;
    private BufferedReader in;
    private PrintWriter out;
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Nueva conexión entrante");

                // Lee la consulta enviada por el cliente
                try (ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream())) {
                    Object receivedObject = inStream.readObject();
                    String query = receivedObject.toString();

                    System.out.println("Consulta recibida: " + query);
                    // Procesa la consulta y envía la respuesta al cliente
                    String result = SendResultsQuery(query);
                    // Envia la respuesta al cliente
                    try (ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream())) {
                        outStream.writeObject(result);
                        System.out.println("Respuesta enviada al cliente: " + result);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                // Cierra el socket del cliente después de manejar la consulta
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1408);
        Server server = new Server(serverSocket);
        server.startServer();
    }
    public String SendResultsQuery(String query){
        DatabaseSystem BD = new DatabaseSystem();
        return BD.DatabaseSystemStr(query);
    }
}