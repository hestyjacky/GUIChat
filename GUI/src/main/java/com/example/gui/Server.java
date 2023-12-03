package com.example.gui;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;
    Socket socket;
    private BufferedReader bf_reader;
    private BufferedWriter bf_writer;

    public Server(int serverSocketTxt) {
        try {
            System.out.println("hol");
            serverSocket = new ServerSocket(serverSocketTxt);
            System.out.println(serverSocket);
            socket = serverSocket.accept();
            System.out.println("holi2");
            this.bf_reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bf_writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e){
            System.out.println("Error creating server");
            e.printStackTrace();
        }
    }

    public void sendMessagesToClient(String messageToClient){
        try{
            bf_writer.write(messageToClient);
            bf_writer.newLine();
            bf_writer.flush();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error sending message to the client");
            closeEverything(socket, bf_reader,bf_writer);
        }
    }

    public void receiveMessageFromClient(VBox vBox){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    try{
                        String messageFromClient = bf_reader.readLine();
                        ServidorController.addLabel(messageFromClient, vBox);
                    }catch (IOException e){
                        e.printStackTrace();
                        System.out.println("Error reciving message from the client");
                        closeEverything(socket, bf_reader, bf_writer);
                        break;
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bf_reader, BufferedWriter bf_writer){
        try {
            if (bf_reader != null){
                bf_reader.close();
            }
            if (bf_writer != null){
                bf_writer.close();
            }
            if (socket != null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
