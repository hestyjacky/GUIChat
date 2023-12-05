package com.example.gui;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    // Array de usuarios conectados
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>(); // broadcast the message with every client
    private Socket socket; // get comunication | conections
    private BufferedReader bufferedReader; // read data
    private BufferedWriter bufferedWriter; // to send data
    private String client_Username;
    public ClientHandler(Socket socket){
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // works with characters | send things
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // read things
            this.client_Username = bufferedReader.readLine(); // the client enter his username
            clientHandlers.add(this); // has joined the groupchat
            broadcastMessage("SERVER: "+client_Username.split("\\:")[0]+ " has entered the chat..."); // all messages are send to all the clients in the group
            //broadcastMessage(client_Username);
        }catch (IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClientHandler();
        try {
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if (socket != null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {// we are looking for receiving and sending at the same time
        String messageFromClient;
        while (socket.isConnected()){
            try {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            }catch (IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
                break;
            }
        }
    }

    //to send the message to each client except for the user
    public void broadcastMessage(String messageToSend){
        // for each client in the array
        for (ClientHandler clientHandler : clientHandlers){
            try {
                if (!clientHandler.client_Username.equals(client_Username)) {
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
            }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("SERVER: "+client_Username+ " has left the chat...");
    }
}
