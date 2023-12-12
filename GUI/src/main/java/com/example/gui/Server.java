package com.example.gui;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class Server {
    private ServerSocket serverSocket;
    private static HashSet<ObjectOutputStream> outputStreams = new HashSet<>();

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Nueva conexión entrante");

                ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                outputStreams.add(outStream);

                // Inicia un hilo para manejar la conexión del cliente
                new ClientHandler(socket, outStream).start();
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

    public static String SendResultsQuery(String query) {
        DatabaseSystem BD = new DatabaseSystem();
        return BD.DatabaseSystemStr(query);
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private ObjectInputStream inStream;
        private ObjectOutputStream outStream;

        public ClientHandler(Socket socket, ObjectOutputStream outStream) {
            this.socket = socket;
            this.outStream = outStream;
        }

        public void run() {
            try {
                inStream = new ObjectInputStream(socket.getInputStream());

                while (true) {
                    // Lee la consulta enviada por el cliente
                    Object queryObject = inStream.readObject();

                    if (queryObject instanceof String) {
                        String query = (String) queryObject;
                        System.out.println("Consulta recibida: " + query);

                        // Procesa la consulta y envía la respuesta a todos los clientes
                        String result = SendResultsQuery(query);

                        // Envia la respuesta a todos los clientes
                        for (ObjectOutputStream clientStream : outputStreams) {
                            clientStream.writeObject(result);
                            clientStream.flush();
                        }
                    } else {
                        System.err.println("Tipo de objeto no esperado: " + queryObject.getClass());
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                // Elimina la conexión del cliente cuando se cierra
                outputStreams.remove(outStream);
                try {
                    if (inStream != null) {
                        inStream.close();
                    }
                    if (outStream != null) {
                        outStream.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
