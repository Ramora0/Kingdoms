package com.kingdoms.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Network {
  private BufferedReader in;
  private PrintWriter out;

  public Server() {
    try {
      ServerSocket serverSocket = new ServerSocket(Network.port); // Server socket on port 12345
      System.out.println("Server waiting for client on port 12345...");

      Socket socket = serverSocket.accept(); // Wait for a connection from the client
      System.out.println("Client connected: " + socket.getInetAddress());

      // Input and output streams for communication
      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      this.out = new PrintWriter(socket.getOutputStream(), true);

      // Read data from the client
      String message = in.readLine();
      System.out.println("Received from client: " + message);

      // Send a response back to the client
      out.println("Hello from the server!");

      // Don't close the streams or socket here
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void sendMessages() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'sendMessages'");
  }
}