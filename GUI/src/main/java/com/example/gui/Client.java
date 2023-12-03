
package com.example.gui;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;
    private BufferedReader bfReader;
    private BufferedWriter bfWriter;
    
    public Client(Socket socket) {
        try{
            this.socket = socket;
            this.bfReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bfWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e){
            System.out.println("Error creating client");
            e.printStackTrace();
            closeEverything(socket, bfReader, bfWriter);
        }
    }

    public void sendMessagesToServer(String messageToServer){
        try{
            bfWriter.write(messageToServer);
            bfWriter.newLine();
            bfWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error sending message to the client");
            closeEverything(socket, bfReader,bfWriter);
        }
    }

    public void receiveMessageFromServer(VBox vBox){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    try{
                        String messageFromServer = bfReader.readLine();
                        ServidorController.addLabel(messageFromServer, vBox);
                    }catch (IOException e){
                        e.printStackTrace();
                        System.out.println("Error reciving message from the server");
                        closeEverything(socket, bfReader, bfWriter);
                        break;
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bfReader, BufferedWriter brWriter){
        try{
            if (bfReader != null){
                bfReader.close();
            }
            if (bfWriter != null){
                bfWriter.close();
            }
            if (socket != null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
